package com.example.mymenuproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private EditText customerNameEditText;
    private Button goToMenuButton;
    private Button goToInfoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        customerNameEditText = findViewById(R.id.customerNameEditText);
        goToMenuButton = findViewById(R.id.goToMenuButton);
        goToInfoButton = findViewById(R.id.goToInfoButton);

        goToMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String customerName = customerNameEditText.getText().toString();
                if (!customerName.isEmpty()) {
                    Intent menuIntent = new Intent(MainActivity.this, MenuActivity.class);
                    menuIntent.putExtra("customerName", customerName);
                    startActivity(menuIntent);
                } else {
                    Toast.makeText(MainActivity.this, "الرجاء إدخال اسم العميل", Toast.LENGTH_SHORT).show();
                }
            }
        });

        goToInfoButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent infoIntent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(infoIntent);
            }
        });
    }
}
