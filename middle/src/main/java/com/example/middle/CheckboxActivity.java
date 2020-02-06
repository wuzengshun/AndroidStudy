package com.example.middle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class CheckboxActivity extends AppCompatActivity implements OnCheckedChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkbox);

        CheckBox ck_system = (CheckBox) findViewById(R.id.ck_system);
        CheckBox ck_custom = (CheckBox) findViewById(R.id.ck_custom);
        ck_system.setOnCheckedChangeListener(this);
        ck_custom.setOnCheckedChangeListener(this);
    }

    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        String desc = String.format("您%s了这个CheckBox", isChecked?"勾选":"取消勾选");
        buttonView.setText(desc);
    }
}
