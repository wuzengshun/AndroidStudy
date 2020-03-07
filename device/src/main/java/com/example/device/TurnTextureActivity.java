package com.example.device;

import com.example.device.widget.TurnTextureView;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;

public class TurnTextureActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    private TurnTextureView ttv_circle;
    private CheckBox ck_control;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turn_texture);

        ttv_circle = (TurnTextureView) findViewById(R.id.ttv_circle);
        ttv_circle.setSurfaceTextureListener(ttv_circle);

        ck_control = (CheckBox) findViewById(R.id.ck_control);
        ck_control.setOnCheckedChangeListener(this);

        initSpinner();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (buttonView.getId() == R.id.ck_control) {
            if (isChecked == true) {
                ck_control.setText("停止");
                ttv_circle.start();
            } else {
                ck_control.setText("转动");
                ttv_circle.stop();
            }
        }
    }

    private void initSpinner() {
        ArrayAdapter<String> starAdapter = new ArrayAdapter<String>(this,
                R.layout.item_select, alphaArray);
        starAdapter.setDropDownViewResource(R.layout.item_select);

        Spinner sp_alpha = (Spinner) findViewById(R.id.sp_alpha);
        sp_alpha.setPrompt("请选择透明度");
        sp_alpha.setAdapter(starAdapter);
        sp_alpha.setSelection(1);
        sp_alpha.setOnItemSelectedListener(new MySelectedListener());
    }

    private String[] alphaArray = {"0.0", "0.2", "0.4", "0.6", "0.8", "1.0"};
    class MySelectedListener implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            ttv_circle.setAlpha(Float.parseFloat(alphaArray[arg2]));
        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }
}
