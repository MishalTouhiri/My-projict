package com.example.mymenuproject;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.inputmethod.EditorInfo;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    private TextInputLayout customerNameLayout;
    private TextInputEditText customerNameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        customerNameLayout = findViewById(R.id.customerNameLayout);
        customerNameEditText = findViewById(R.id.customerNameEditText);
        MaterialButton goToMenuButton = findViewById(R.id.goToMenuButton);
        MaterialButton goToInfoButton = findViewById(R.id.goToInfoButton);

        goToMenuButton.setOnClickListener(v -> openMenu());
        goToInfoButton.setOnClickListener(v ->
                startActivity(new Intent(this, MainActivity2.class)));

        customerNameEditText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_GO) {
                openMenu();
                return true;
            }
            return false;
        });
        customerNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                customerNameLayout.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void openMenu() {
        Editable text = customerNameEditText.getText();
        String customerName = text == null ? "" : text.toString().trim();
        if (customerName.isEmpty()) {
            customerNameLayout.setError(getString(R.string.customer_name_error));
            return;
        }
        Intent menuIntent = new Intent(this, MenuActivity.class);
        menuIntent.putExtra(MenuActivity.EXTRA_CUSTOMER_NAME, customerName);
        startActivity(menuIntent);
    }
}
