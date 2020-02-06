package com.example.middle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import android.widget.TextView;

public class SwitchDefaultActivity extends AppCompatActivity implements OnCheckedChangeListener  {
    private Switch sw_status;
    private TextView tv_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_default);

        sw_status = (Switch) findViewById(R.id.sw_status);
        tv_result = (TextView) findViewById(R.id.tv_result);
        sw_status.setOnCheckedChangeListener(this);
        refreshResult();
    }

    private void refreshResult() {
        String result = String.format("Switch按钮的状态是%s", (sw_status.isChecked())?"开":"关");
        tv_result.setText(result);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        refreshResult();
    }
}
