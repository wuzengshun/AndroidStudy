package com.example.device;

import com.example.device.adapter.ShootingAdapter;
import com.example.device.widget.CameraView;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;

import android.hardware.Camera;

import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class PhotographActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "PhotographActivity";
    private FrameLayout fl_content;
    private ImageView iv_photo;
    private GridView gv_shooting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photograph);

        fl_content = (FrameLayout) findViewById(R.id.fl_content);
        iv_photo = (ImageView) findViewById(R.id.iv_photo);
        gv_shooting = (GridView) findViewById(R.id.gv_shooting);

        findViewById(R.id.btn_catch_behind).setOnClickListener(this);
        findViewById(R.id.btn_catch_front).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_catch_behind) {
            Camera mCamera = Camera.open();
            if (mCamera != null) {
                mCamera.release();
                Intent intent = new Intent(this, TakePictureActivity.class);
                intent.putExtra("type", CameraView.CAMERA_BEHIND);
                startActivityForResult(intent, 1);
            } else {
                Toast.makeText(this, "当前设备不支持后置摄像头", Toast.LENGTH_SHORT).show();
            }
        } else if (v.getId() == R.id.btn_catch_front) {
            Log.d(TAG, "getNumberOfCameras="+Camera.getNumberOfCameras());
            Camera mCamera = Camera.open(CameraView.CAMERA_FRONT);
            if (mCamera != null) {
                mCamera.release();
                Intent intent = new Intent(this, TakePictureActivity.class);
                intent.putExtra("type", CameraView.CAMERA_FRONT);
                startActivityForResult(intent, 1);
            } else {
                Toast.makeText(this, "当前设备不支持前置摄像头", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null) {
            String photo_path = "";
            int cam_type = data.getIntExtra("type", 0);
            if (cam_type == 0) {
                photo_path = data.getStringExtra("path");
            } else if (cam_type == 1) {
                ArrayList<String> photo_paths = data.getStringArrayListExtra("path_list");
                for (String path : photo_paths) {
                    photo_path += path + "\n";
                }
            }
            Toast.makeText(this, "照片路径：\n"+photo_path, Toast.LENGTH_SHORT).show();
        }
    }
}
