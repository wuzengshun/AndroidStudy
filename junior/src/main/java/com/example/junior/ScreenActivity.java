package com.example.junior;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.junior.util.DisplayUtil;

public class ScreenActivity extends AppCompatActivity {

    private TextView zs_screen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen);

        zs_screen = (TextView) findViewById(R.id.zs_screen);
        showScreenInfo();
    }

    private void showScreenInfo() {
        int width = DisplayUtil.getScreenWidth(this);
        int height = DisplayUtil.getScreenHeight(this);
        float density = DisplayUtil.getScreenDensity(this);
        String info = String.format("当前屏幕的宽度是%dpx，高度是%dpx，像素密度是%f",
                    width, height, density);
        zs_screen.setText(info);
    }
}
