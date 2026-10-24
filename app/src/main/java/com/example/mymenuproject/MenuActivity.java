package com.example.mymenuproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {

    public static final String EXTRA_CUSTOMER_NAME = "customerName";
    private static final String STATE_QUANTITIES = "quantities";

    private final Product[] products = concat(Product.DRINKS, Product.BREAKFAST);
    private int[] quantities = new int[products.length];
    private final TextView[] quantityViews = new TextView[products.length];
    private final MaterialButton[] decreaseButtons = new MaterialButton[products.length];

    private TextView itemsCountTextView;
    private TextView totalTextView;
    private MaterialButton confirmOrderButton;
    private String customerName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        if (savedInstanceState != null) {
            int[] saved = savedInstanceState.getIntArray(STATE_QUANTITIES);
            if (saved != null && saved.length == products.length) quantities = saved;
        }

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());

        String name = getIntent().getStringExtra(EXTRA_CUSTOMER_NAME);
        if (name != null) customerName = name;
        TextView menuTitleTextView = findViewById(R.id.menuTitleTextView);
        menuTitleTextView.setText(getString(R.string.menu_greeting, customerName));

        LinearLayout menuItemsLayout = findViewById(R.id.menuItemsLayout);
        LayoutInflater inflater = getLayoutInflater();
        addSection(inflater, menuItemsLayout, R.string.section_drinks, 0, Product.DRINKS.length);
        addSection(inflater, menuItemsLayout, R.string.section_breakfast,
                Product.DRINKS.length, products.length);

        itemsCountTextView = findViewById(R.id.itemsCountTextView);
        totalTextView = findViewById(R.id.totalTextView);
        confirmOrderButton = findViewById(R.id.confirmOrderButton);
        confirmOrderButton.setOnClickListener(this::confirmOrder);

        updateSummary();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putIntArray(STATE_QUANTITIES, quantities);
    }

    private void addSection(LayoutInflater inflater, LinearLayout parent, int title, int from, int to) {
        TextView header = (TextView) inflater.inflate(R.layout.item_menu_section, parent, false);
        header.setText(title);
        parent.addView(header);

        for (int i = from; i < to; i++) {
            Product product = products[i];
            View row = inflater.inflate(R.layout.item_menu, parent, false);

            ((ImageView) row.findViewById(R.id.itemImage)).setImageResource(product.image);
            ((TextView) row.findViewById(R.id.itemName)).setText(product.name);
            ((TextView) row.findViewById(R.id.itemDescription)).setText(product.description);
            ((TextView) row.findViewById(R.id.itemPrice))
                    .setText(getString(R.string.price_format, product.price));

            final int index = i;
            quantityViews[i] = row.findViewById(R.id.itemQuantity);
            decreaseButtons[i] = row.findViewById(R.id.decreaseButton);
            decreaseButtons[i].setOnClickListener(v -> changeQuantity(index, -1));
            row.findViewById(R.id.increaseButton).setOnClickListener(v -> changeQuantity(index, 1));
            row.setOnClickListener(v -> changeQuantity(index, 1));

            parent.addView(row);
        }
    }

    private void changeQuantity(int index, int delta) {
        quantities[index] = Math.max(0, Math.min(20, quantities[index] + delta));
        updateSummary();
    }

    private void updateSummary() {
        int count = 0;
        int total = 0;
        for (int i = 0; i < products.length; i++) {
            count += quantities[i];
            total += quantities[i] * products[i].price;
            quantityViews[i].setText(String.valueOf(quantities[i]));
            decreaseButtons[i].setEnabled(quantities[i] > 0);
        }
        itemsCountTextView.setText(getString(R.string.items_count, count));
        totalTextView.setText(getString(R.string.price_format, total));
        confirmOrderButton.setEnabled(count > 0);
    }

    private void confirmOrder(View button) {
        ArrayList<String> lines = new ArrayList<>();
        ArrayList<Integer> lineTotals = new ArrayList<>();
        int total = 0;
        for (int i = 0; i < products.length; i++) {
            if (quantities[i] == 0) continue;
            int lineTotal = quantities[i] * products[i].price;
            lines.add(getString(R.string.order_line, quantities[i], getString(products[i].name)));
            lineTotals.add(lineTotal);
            total += lineTotal;
        }
        if (lines.isEmpty()) {
            Snackbar.make(button, R.string.empty_order, Snackbar.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(this, OrderActivity.class);
        intent.putExtra(EXTRA_CUSTOMER_NAME, customerName);
        intent.putStringArrayListExtra(OrderActivity.EXTRA_LINES, lines);
        intent.putIntegerArrayListExtra(OrderActivity.EXTRA_LINE_TOTALS, lineTotals);
        intent.putExtra(OrderActivity.EXTRA_TOTAL, total);
        startActivity(intent);
    }

    private static Product[] concat(Product[] a, Product[] b) {
        Product[] all = new Product[a.length + b.length];
        System.arraycopy(a, 0, all, 0, a.length);
        System.arraycopy(b, 0, all, a.length, b.length);
        return all;
    }
}
