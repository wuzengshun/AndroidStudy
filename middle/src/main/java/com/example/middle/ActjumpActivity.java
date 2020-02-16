package com.example.middle;

import com.example.middle.util.DateUtil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class ActjumpActivity extends AppCompatActivity implements OnClickListener {
    private final static String TAG = "ActJumpActivity";
    private TextView tv_life;
    private String mStr = "";

    private void refreshLife(String desc) {
        Log.d(TAG, desc);
        mStr = String.format("%s%s %s %s\n", mStr, DateUtil.getNowTimeDetail(), TAG, desc);
        tv_life.setText(mStr);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_jump);

        findViewById(R.id.btn_act_next).setOnClickListener(this);
        tv_life = (TextView) findViewById(R.id.tv_life);
        refreshLife("onCreate");
    }

    protected void onStart() {
        refreshLife("onStart");
        super.onStart();
    }

    protected void onStop() {
        refreshLife("onStop");
        super.onStop();
    }

    protected void onResume() {
        refreshLife("onResume");
        super.onResume();
    }

    protected void onPause() {
        refreshLife("onPause");
        super.onPause();
    }

    protected void onRestart() {
        refreshLife("onRestart");
        super.onRestart();
    }

    protected void onDestroy() {
        refreshLife("onDestroy");
        super.onDestroy();
    }

    public void onClick(View v) {
        if (v.getId() == R.id.btn_act_next) {
            Intent intent = new Intent(this, ActNextActivity.class);
            Log.d(TAG, "onClick --> go to ActNextActivity");
            startActivityForResult(intent, 0);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String nextLife = data.getStringExtra("life");
        refreshLife("\n"+nextLife);
        refreshLife("onActivityResult");
        super.onActivityResult(requestCode, resultCode, data);
    }

}
