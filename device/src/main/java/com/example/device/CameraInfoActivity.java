package com.example.device;

import com.example.device.bean.CameraInfo;
import com.example.device.adapter.CameraAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

public class CameraInfoActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {
    private final static String TAG = "CameraInfoActivity";
    private ListView lv_camera;
    private String mDesc = "";

    public static final int RC_PERMISSIONS = 1001;
    public static final int RC_SETTINGS_SCREEN = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_info);

        lv_camera = (ListView) findViewById(R.id.lv_camera);

        requestStoreAndCameraPermission();
    }

    private void checkCamera() {
        ArrayList<CameraInfo> cameraList = new ArrayList<CameraInfo>();
        int cameraCount = Camera.getNumberOfCameras();
        mDesc = String.format("%s摄像头个数=%d", mDesc, cameraCount);
        for (int i=0; i<cameraCount; i++) {
            CameraInfo info = new CameraInfo();
            Camera camera = Camera.open(i);
            Parameters params = camera.getParameters();
            info.camera_type = (i==0) ? "前置":"后置";
            info.flash_mode = params.getFlashMode();
            info.focus_mode = params.getFocusMode();
            info.scene_mode = params.getSceneMode();
            info.color_effect = params.getColorEffect();
            info.white_balance = params.getWhiteBalance();
            info.max_zoom = params.getMaxZoom();
            info.zoom = params.getZoom();
            info.resolutionList = params.getSupportedPreviewSizes();
            camera.release();
            cameraList.add(info);
        }
        CameraAdapter adapter = new CameraAdapter(this, cameraList);
        lv_camera.setAdapter(adapter);
    }

    @AfterPermissionGranted(RC_PERMISSIONS)
    private void requestStoreAndCameraPermission() {
        String[] permissions = {Manifest.permission.CAMERA};
        if (EasyPermissions.hasPermissions(this, permissions)) {
            //权限获取成功
            Toast.makeText(this, "已经获取机权限", Toast.LENGTH_LONG).show();
            checkCamera();
        }else {
            //没有权限，调用方法申请权限
            EasyPermissions.requestPermissions(this, "程序运行需要相机权限", RC_PERMISSIONS, permissions);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // 调用EasyPermissions的onRequestPermissionsResult方法，参数和系统方法保持一致，然后就不要关心具体的权限申请代码了
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        // 此处表示权限申请已经成功，可以使用该权限完成app的相应的操作了
        Toast.makeText(this, "允许获取机权限", Toast.LENGTH_LONG).show();
        checkCamera();
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        // 此处表示权限申请被用户拒绝了，此处可以通过弹框等方式展示申请该权限的原因，以使用户允许使用该权限
        Toast.makeText(this, "拒绝获取机权限", Toast.LENGTH_LONG).show();
        /**
         * 若是在权限弹窗中，用户勾选了'NEVER ASK AGAIN.'或者'不在提示'，且拒绝权限。
         * 这时候，需要跳转到设置界面去，让用户手动开启。
         */
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            //用户勾选了“不再询问”，引导用户去设置页面打开权限
            new AppSettingsDialog.Builder(this)
                    .setTitle("权限申请")
                    .setRationale("应用程序运行缺少必要的权限，请前往设置页面打开")
                    .setPositiveButton("去设置")
                    .setNegativeButton("取消")
                    .setRequestCode(RC_SETTINGS_SCREEN)
                    .build()
                    .show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SETTINGS_SCREEN) {
            String[] permissions = {Manifest.permission.CAMERA};
            if (EasyPermissions.hasPermissions(this, permissions)) {
                //权限获取成功
                Toast.makeText(this, "返回APP，已设置操作相机权限", Toast.LENGTH_LONG).show();
            }else {
                //没有权限，调用方法申请权限
                Toast.makeText(this, "返回APP，未设置操作相机权限", Toast.LENGTH_LONG).show();
            }
        }
    }
}
