package com.example.mymenuproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;



public class MenuActivity extends AppCompatActivity {

    private TextView menuTitleTextView;
    private LinearLayout menuItemsLayout;
    private CheckBox coffeeCheckBox, latteCheckBox, espressoCheckBox, icedCoffeeCheckBox, croissantCheckBox, toastCheckBox;
    private Button confirmOrderButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        menuTitleTextView = findViewById(R.id.menuTitleTextView);
        menuItemsLayout = findViewById(R.id.menuItemsLayout);
        coffeeCheckBox = findViewById(R.id.coffeeCheckBox);
        latteCheckBox = findViewById(R.id.latteCheckBox);
        espressoCheckBox = findViewById(R.id.espressoCheckBox);
        icedCoffeeCheckBox = findViewById(R.id.icedCoffeeCheckBox);
        croissantCheckBox = findViewById(R.id.croissantCheckBox);
        toastCheckBox = findViewById(R.id.boiledEggCheckBox);
        confirmOrderButton = findViewById(R.id.confirmOrderButton);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String customerName = extras.getString("customerName");
            menuTitleTextView.setText("مرحبًا " + customerName + "، اختر طلبك:");
        }

        confirmOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder orderSummary = new StringBuilder("تم استلام الطلب:\n");

                if (coffeeCheckBox.isChecked()) {
                    orderSummary.append("- قهوة\n");
                }
                if (latteCheckBox.isChecked()) {
                    orderSummary.append("- لاتيه\n");
                }
                if (espressoCheckBox.isChecked()) {
                    orderSummary.append("- إسبريسو\n");
                }
                if (icedCoffeeCheckBox.isChecked()) {
                    orderSummary.append("- قهوة مثلجة\n");
                }
                if (croissantCheckBox.isChecked()) {
                    orderSummary.append("- كرواسون\n");
                }
                if (toastCheckBox.isChecked()) {
                    orderSummary.append("توست\n");
                }

                if (orderSummary.toString().equals("تم استلام الطلب:\n")) {
                    Toast.makeText(MenuActivity.this, "الرجاء اختيار عنصر واحد على الأقل", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MenuActivity.this, orderSummary.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
