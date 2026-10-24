package com.example.mymenuproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;

/** Cafe information: address, hours, contact and the team. */
public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());
        findViewById(R.id.backToMenuButton).setOnClickListener(v -> finish());

        LinearLayout container = findViewById(R.id.infoContainer);
        LayoutInflater inflater = getLayoutInflater();
        addRow(inflater, container, R.drawable.ic_place, R.string.info_address_label, R.string.info_address);
        addRow(inflater, container, R.drawable.ic_schedule, R.string.info_hours_label, R.string.info_hours);
        addRow(inflater, container, R.drawable.ic_phone, R.string.info_phone_label, R.string.info_phone);
        addRow(inflater, container, R.drawable.ic_group, R.string.info_team_label, R.string.info_team);
    }

    private void addRow(LayoutInflater inflater, LinearLayout parent, int icon, int label, int value) {
        View row = inflater.inflate(R.layout.item_info_row, parent, false);
        ((ImageView) row.findViewById(R.id.infoIcon)).setImageResource(icon);
        ((TextView) row.findViewById(R.id.infoLabel)).setText(label);
        ((TextView) row.findViewById(R.id.infoValue)).setText(value);
        parent.addView(row);
    }
}
