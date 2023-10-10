package com.example.vedroid1;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;


public class main extends Activity {

    private int i = 0, j = 0, k = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helloact);

        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        String text1 = "Левая ";
        String text2 = "Правая ";


        TextView mainTextLeft = findViewById(R.id.text1);
        TextView mainTextRight = findViewById(R.id.text2);

        mainTextLeft.setGravity(Gravity.RIGHT);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = i + 1;
                mainTextLeft.setText(text1 + i);
                mainTextRight.setText(text2 + j);
                if(k%2 == 0 ) {
                    mainTextLeft.setGravity(Gravity.RIGHT);
                    mainTextRight.setGravity(Gravity.LEFT);
                    int a = mainTextLeft.getGravity();
                    int b = mainTextRight.getGravity();
                    Log.i("AppLogger", "Left " + a);
                    Log.i("AppLogger", "Right " + b);
                }
                else {
                    mainTextLeft.setGravity(Gravity.RIGHT);
                    mainTextRight.setGravity(Gravity.LEFT);
                    int a = mainTextLeft.getGravity();
                    int b = mainTextRight.getGravity();
                    Log.i("AppLogger", "Left " + a);
                    Log.i("AppLogger", "Right " + b);
                }
                k = k + 1;
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                j = j + 1;
                mainTextLeft.setText(text1 + i);
                mainTextRight.setText(text2 + j);
                if(k%2 == 0 ) {
                    mainTextLeft.setGravity(Gravity.RIGHT);
                    mainTextRight.setGravity(Gravity.LEFT);
                    int a = mainTextLeft.getGravity();
                    int b = mainTextRight.getGravity();
                    Log.i("AppLogger", "Left " + a);
                    Log.i("AppLogger", "Right " + b);
                }
                else {
                    mainTextLeft.setGravity(Gravity.RIGHT);
                    mainTextRight.setGravity(Gravity.LEFT);
                    int a = mainTextLeft.getGravity();
                    int b = mainTextRight.getGravity();
                    Log.i("AppLogger", "Left " + a);
                    Log.i("AppLogger", "Right " + b);
                }
                k = k + 1;
            }
        });
    }

}
