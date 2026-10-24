package com.example.mymenuproject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.robolectric.Shadows.shadowOf;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.annotation.GraphicsMode;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Walks through the whole order flow. Run with -Pcafe.screenshots=<dir> to
 * also save a PNG of every screen.
 */
@RunWith(RobolectricTestRunner.class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = 34, qualifiers = "ar-w411dp-h891dp-port-xxhdpi")
public class CafeFlowTest {

    @Test
    public void welcomeAsksForNameBeforeOpeningMenu() {
        MainActivity activity = Robolectric.buildActivity(MainActivity.class).setup().get();
        snapshot(activity, "1-welcome");

        activity.findViewById(R.id.goToMenuButton).performClick();

        TextInputLayout nameLayout = activity.findViewById(R.id.customerNameLayout);
        assertNotNull(nameLayout.getError());
        assertNull(shadowOf(activity).getNextStartedActivity());

        ((TextView) activity.findViewById(R.id.customerNameEditText)).setText("مشعل");
        assertNull(nameLayout.getError());
        activity.findViewById(R.id.goToMenuButton).performClick();

        Intent next = shadowOf(activity).getNextStartedActivity();
        assertEquals(MenuActivity.class.getName(), next.getComponent().getClassName());
        assertEquals("مشعل", next.getStringExtra(MenuActivity.EXTRA_CUSTOMER_NAME));
    }

    @Test
    public void menuTotalsQuantitiesAndOpensReceipt() {
        Intent intent = new Intent()
                .setClassName("com.example.mymenuproject", MenuActivity.class.getName())
                .putExtra(MenuActivity.EXTRA_CUSTOMER_NAME, "مشعل");
        MenuActivity activity = Robolectric.buildActivity(MenuActivity.class, intent).setup().get();

        View confirm = activity.findViewById(R.id.confirmOrderButton);
        assertFalse(confirm.isEnabled());

        ViewGroup items = activity.findViewById(R.id.menuItemsLayout);
        // Children: greeting, drinks header, 4 drinks, breakfast header, 2 breakfast items.
        tapIncrease(items.getChildAt(3), 2);  // latte 2 × 14
        tapIncrease(items.getChildAt(4), 1);  // espresso 10
        tapIncrease(items.getChildAt(7), 1);  // croissant 9
        items.getChildAt(4).findViewById(R.id.decreaseButton).performClick();
        tapIncrease(items.getChildAt(4), 1);

        TextView total = activity.findViewById(R.id.totalTextView);
        assertEquals(activity.getString(R.string.price_format, 47), total.getText().toString());
        assertTrue(confirm.isEnabled());
        snapshot(activity, "2-menu");

        confirm.performClick();
        Intent receipt = shadowOf(activity).getNextStartedActivity();
        assertEquals(OrderActivity.class.getName(), receipt.getComponent().getClassName());
        assertEquals(47, receipt.getIntExtra(OrderActivity.EXTRA_TOTAL, 0));
        ArrayList<String> lines = receipt.getStringArrayListExtra(OrderActivity.EXTRA_LINES);
        assertEquals(3, lines.size());

        OrderActivity order = Robolectric.buildActivity(OrderActivity.class, receipt).setup().get();
        ViewGroup orderLines = order.findViewById(R.id.orderLines);
        assertEquals(3, orderLines.getChildCount());
        snapshot(order, "3-receipt");
    }

    @Test
    public void infoScreenListsCafeDetails() {
        MainActivity2 activity = Robolectric.buildActivity(MainActivity2.class).setup().get();
        ViewGroup container = activity.findViewById(R.id.infoContainer);
        // Photo, about text and four detail rows.
        assertEquals(6, container.getChildCount());
        snapshot(activity, "4-info");

        activity.findViewById(R.id.backToMenuButton).performClick();
        assertTrue(activity.isFinishing());
    }

    private static void tapIncrease(View row, int times) {
        for (int i = 0; i < times; i++) row.findViewById(R.id.increaseButton).performClick();
    }

    private static void snapshot(Activity activity, String name) {
        String dir = System.getProperty("cafe.screenshots");
        if (dir == null || dir.isEmpty()) return;
        shadowOf(Looper.getMainLooper()).idle();
        View root = activity.getWindow().getDecorView();
        root.measure(
                View.MeasureSpec.makeMeasureSpec(root.getWidth(), View.MeasureSpec.EXACTLY),
                View.MeasureSpec.makeMeasureSpec(root.getHeight(), View.MeasureSpec.EXACTLY));
        root.layout(0, 0, root.getWidth(), root.getHeight());
        Bitmap bitmap = Bitmap.createBitmap(root.getWidth(), root.getHeight(), Bitmap.Config.ARGB_8888);
        root.draw(new Canvas(bitmap));
        File file = new File(dir, name + ".png");
        file.getParentFile().mkdirs();
        try (FileOutputStream out = new FileOutputStream(file)) {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
