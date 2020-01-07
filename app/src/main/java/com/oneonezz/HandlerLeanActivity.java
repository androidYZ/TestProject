package com.oneonezz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class HandlerLeanActivity extends AppCompatActivity {
    ThreadLocal<Boolean> threadLocal = new ThreadLocal<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_lean);
        threadLocal.set(true);


        System.out.println("NiYouXi->onCreate: "+threadLocal.get());
        

    }
}
