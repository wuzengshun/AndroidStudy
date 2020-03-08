package com.example.device;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class ShootingActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "ShootingActivity";
    private FrameLayout fl_content;
    private ImageView iv_photo;
    private GridView gv_shooting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_shooting);

        fl_content = (FrameLayout) findViewById(R.id.fl_content);
        iv_photo = (ImageView) findViewById(R.id.iv_photo);
        gv_shooting = (GridView) findViewById(R.id.gv_shooting);

        findViewById(R.id.btn_catch_behind).setOnClickListener(this);
        findViewById(R.id.btn_catch_front).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        CameraManager cm = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        String[] ids;
        try {
            ids = cm.getCameraIdList();
        } catch (CameraAccessException e) {
            e.printStackTrace();
            return;
        }

        if (v.getId() == R.id.btn_catch_behind) {
            if (checkCamera(ids, CameraCharacteristics.LENS_FACING_FRONT+"") == true) {
                Intent intent = new Intent(this, TakeShootingActivity.class);
                intent.putExtra("type", CameraCharacteristics.LENS_FACING_FRONT);
                startActivityForResult(intent, 1);
            } else {
                Toast.makeText(this, "当前设备不支持后置摄像头", Toast.LENGTH_SHORT).show();
            }
        } else if (v.getId() == R.id.btn_catch_front) {
            if (checkCamera(ids, CameraCharacteristics.LENS_FACING_BACK+"") == true) {
                Intent intent = new Intent(this, TakeShootingActivity.class);
                intent.putExtra("type", CameraCharacteristics.LENS_FACING_BACK);
                startActivityForResult(intent, 1);
            } else {
                Toast.makeText(this, "当前设备不支持前置摄像头", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean checkCamera(String[] ids, String type) {
        boolean result = false;
        String cmLists = "";
        for (int i=0; i<ids.length; i++) {
            cmLists = cmLists + ids[i] + " ";
            if (ids[i].equals(type) == true) {
                result = true;
            }
        }
        Log.d(TAG, "Camera List:"+cmLists + ","+"check camera:"+type);
        return result;
    }
}
