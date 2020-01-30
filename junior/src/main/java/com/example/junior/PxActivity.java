package com.example.junior;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import com.example.junior.util.Utils;

public class PxActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_px);

        int dip_10 = Utils.dip2px(this, 10L);
        TextView tv_padding = (TextView) findViewById(R.id.zs_padding);
        tv_padding.setPadding(dip_10, dip_10, dip_10, dip_10);
    }
}
