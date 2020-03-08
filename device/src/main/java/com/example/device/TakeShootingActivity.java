package com.example.device;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.hardware.camera2.CameraCharacteristics;
import android.os.Bundle;
import android.view.View;

import com.example.device.widget.Camera2View;

public class TakeShootingActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "TakeShootingActivity";
    private Camera2View camera2_view;
    private int mTakeType = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_take_shooting);

        int camera_type = getIntent().getIntExtra("type", CameraCharacteristics.LENS_FACING_FRONT);
        camera2_view = (Camera2View)findViewById(R.id.camera2_view);

        camera2_view.open(camera_type);
        findViewById(R.id.btn_shutter).setOnClickListener(this);
        findViewById(R.id.btn_shooting).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_shutter) {
            mTakeType = 0;
            camera2_view.takePicture();
        } else if (v.getId() == R.id.btn_shooting) {
            mTakeType = 1;
            camera2_view.startShooting(7000);
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        String photo_path = camera2_view.getPhotoPath();
        bundle.putInt("type", mTakeType);
        if (photo_path==null && mTakeType==0) {
            bundle.putString("is_null", "yes");
        } else {
            bundle.putString("is_null", "no");
            if (mTakeType == 0) {
                bundle.putString("path", photo_path);
            } else if (mTakeType == 1) {
                bundle.putStringArrayList("path_list", camera2_view.getShootingList());
            }
        }
        intent.putExtras(bundle);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}
