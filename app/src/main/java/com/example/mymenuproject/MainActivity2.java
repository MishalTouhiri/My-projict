package com.example.mymenuproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import android.widget.TextView;



public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        TextView textView = findViewById(R.id.tX1);
        TextView textView2 = findViewById(R.id.tX2);
        TextView textView3 = findViewById(R.id.tX3);


        textView.setText("معلومات عن المقهى");
        textView2.setText("عنوان المقهى: العنوان");
        textView3.setText("رقم الاتصال: 123456789");
    }
}

