package com.oneonezz.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.oneonezz.R;

public class InflaterActivity extends AppCompatActivity {

    private LinearLayout clRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inflater);
        clRoot = (LinearLayout) findViewById(R.id.cl_root);

        LayoutInflater.from(this).inflate(R.layout.inflater_test, clRoot);
//
//        View view = LayoutInflater.from(this).inflate(R.layout.inflater_test, clRoot, false);
//        clRoot.addView(view);
        View view2 = LayoutInflater.from(this).inflate(R.layout.inflater_test, null, true);
        clRoot.addView(view2);

    }
}
