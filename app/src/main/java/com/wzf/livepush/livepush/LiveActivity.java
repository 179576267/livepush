package com.wzf.livepush.livepush;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceView;
import android.view.View;

import com.werb.permissionschecker.PermissionChecker;
import com.wzf.livepush.R;

/**
 * @Description:
 * @author: wangzhenfei
 * @date: 2017-09-22 16:58
 */

public class LiveActivity extends AppCompatActivity{
    private  LivePusher livePusher;
    String[] PERMISSIONS = new String[]{
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    PermissionChecker permissionChecker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live);
        permissionChecker = new PermissionChecker(this); // initialize，must need
        // check if lack Permissions
        if (permissionChecker.isLackPermissions(PERMISSIONS)) {
            permissionChecker.requestPermissions();
        } else {
            init();
        }

    }

    private void init() {
        SurfaceView surfaceView = (SurfaceView) findViewById(R.id.surface);
        livePusher = new LivePusher(surfaceView.getHolder());
    }

    /**
     * 开始直播
     * @param v
     */
    public void startLive(View v){

    }


    /**
     * 切换摄像头
     * @param v
     */
    public void switchCamera(View v){
        livePusher.switchCamera();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PermissionChecker.PERMISSION_REQUEST_CODE:
                if (permissionChecker.hasAllPermissionsGranted(grantResults)) {
                    init();
                } else {
                    // show dialog when refuse the Permissions
                    permissionChecker.showDialog();
                }
                break;
        }
    }
}
