package com.example.device;

import com.example.device.widget.TurnView;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;


public class TurnViewActivity extends AppCompatActivity implements OnCheckedChangeListener {
    private TurnView tv_circle;
    private CheckBox ck_control;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turn_view);

        tv_circle = (TurnView) findViewById(R.id.tv_circle);
        ck_control = (CheckBox) findViewById(R.id.ck_control);
        ck_control.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (buttonView.getId() == R.id.ck_control) {
            if (isChecked == true) {
                ck_control.setText("停止");
                tv_circle.start();
            } else {
                ck_control.setText("转动");
                tv_circle.stop();
            }
        }
    }
}
