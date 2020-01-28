package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView zs_text = (TextView) findViewById(R.id.zs_text);
        zs_text.setText("你好，世界！");
        zs_text.setTextColor(Color.RED);
        zs_text.setTextSize(30);
    }
}
