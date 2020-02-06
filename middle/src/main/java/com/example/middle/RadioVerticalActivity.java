package com.example.middle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class RadioVerticalActivity extends AppCompatActivity implements OnCheckedChangeListener {
    private final static String TAG = "RadioVerticalActivity";
    private TextView tv_marry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_vertical);

        tv_marry = (TextView) findViewById(R.id.tv_marry);
        RadioGroup rg_marry = (RadioGroup) findViewById(R.id.rg_marry);
        rg_marry.setOnCheckedChangeListener(this);
    }

    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (checkedId == R.id.rb_married) {
            tv_marry.setText("哇哦，祝你早生贵子");
        } else if (checkedId == R.id.rb_unmarried) {
            tv_marry.setText("哇哦，你的前途不可限量");
        }
    }
}
