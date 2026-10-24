package com.example.mymenuproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

/** Receipt shown after the customer confirms an order. */
public class OrderActivity extends AppCompatActivity {

    public static final String EXTRA_LINES = "lines";
    public static final String EXTRA_LINE_TOTALS = "lineTotals";
    public static final String EXTRA_TOTAL = "total";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        Intent intent = getIntent();
        String customerName = intent.getStringExtra(MenuActivity.EXTRA_CUSTOMER_NAME);
        ArrayList<String> lines = intent.getStringArrayListExtra(EXTRA_LINES);
        ArrayList<Integer> lineTotals = intent.getIntegerArrayListExtra(EXTRA_LINE_TOTALS);
        int total = intent.getIntExtra(EXTRA_TOTAL, 0);

        ((TextView) findViewById(R.id.orderSubtitle))
                .setText(getString(R.string.order_subtitle, customerName == null ? "" : customerName));
        ((TextView) findViewById(R.id.orderNumber))
                .setText(getString(R.string.order_number, 1000 + (int) (System.currentTimeMillis() % 9000)));
        ((TextView) findViewById(R.id.orderTotal))
                .setText(getString(R.string.price_format, total));

        LinearLayout orderLines = findViewById(R.id.orderLines);
        LayoutInflater inflater = getLayoutInflater();
        if (lines != null && lineTotals != null) {
            for (int i = 0; i < lines.size() && i < lineTotals.size(); i++) {
                View row = inflater.inflate(R.layout.item_order_line, orderLines, false);
                ((TextView) row.findViewById(R.id.lineName)).setText(lines.get(i));
                ((TextView) row.findViewById(R.id.linePrice))
                        .setText(getString(R.string.price_format, lineTotals.get(i)));
                orderLines.addView(row);
            }
        }

        findViewById(R.id.newOrderButton).setOnClickListener(v -> {
            // Back to the welcome screen, clearing the menu from the back stack.
            Intent home = new Intent(this, MainActivity.class);
            home.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(home);
            finish();
        });
    }
}
