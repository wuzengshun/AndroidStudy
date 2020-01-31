package com.example.junior;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MarqueeActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView zs_marquee;
    private boolean bPause = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marquee);
        zs_marquee = (TextView) findViewById(R.id.zs_marquee);
        zs_marquee.setOnClickListener(this);
    }

    public void onClick(View v) {
        if (v.getId() == R.id.zs_marquee) {
            bPause = !bPause;
            if (bPause) {
                zs_marquee.setFocusable(false);
                zs_marquee.setFocusableInTouchMode(false);
            } else {
                zs_marquee.setFocusable(true);
                zs_marquee.setFocusableInTouchMode(true);
            }
        }
    }
}
