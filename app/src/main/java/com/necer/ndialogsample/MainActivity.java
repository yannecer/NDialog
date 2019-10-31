package com.necer.ndialogsample;

import android.content.Context;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    private int screenWith;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        screenWith = windowManager.getDefaultDisplay().getWidth();


        findViewById(R.id.bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CustomDialog(MainActivity.this)
                        .show();
            }
        });
    }



}
