package com.example.device;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.example.device.widget.TurnSurfaceView;

public class TurnSurfaceActivity extends AppCompatActivity implements OnCheckedChangeListener {

    private TurnSurfaceView tfv_circle;
    private CheckBox ck_control;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turn_surface);

        tfv_circle = (TurnSurfaceView) findViewById(R.id.tfv_circle);
        ck_control = (CheckBox) findViewById(R.id.ck_control);
        ck_control.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (buttonView.getId() == R.id.ck_control) {
            if (isChecked == true) {
                ck_control.setText("停止");
                tfv_circle.start();
            } else {
                ck_control.setText("转动");
                tfv_circle.stop();
            }
        }
    }
}
