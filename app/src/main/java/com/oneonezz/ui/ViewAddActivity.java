package com.oneonezz.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.oneonezz.R;

public class ViewAddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_add);
        ViewGroup viewGroup = findViewById(R.id.ct_rrot);
        LayoutInflater.from(this).inflate(R.layout.activity_main, viewGroup, true);
    }
}
