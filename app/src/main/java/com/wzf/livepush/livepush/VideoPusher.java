package com.wzf.livepush.livepush;

import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;

import java.io.IOException;

/**
 * @Description:
 * @author: wangzhenfei
 * @date: 2017-09-22 17:07
 */

public class VideoPusher implements Pusher, Camera.PreviewCallback {
    private SurfaceHolder mHolder;
    private Camera mCamera;
    private byte[] buffers;
    private int VIDEO_WIGHT = 320;
    private int VIDEO_HEIGHT = 480;
    private int cameraId = android.hardware.Camera.CameraInfo.CAMERA_FACING_BACK;
    public VideoPusher(SurfaceHolder holder) {
        this.mHolder = holder;
        init();
    }

    private void init() {
        mHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                startPreview();
            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                stopPreview();
            }
        });
    }

    private void startPreview() {
        try {
            mCamera = Camera.open(cameraId);
            mCamera.setPreviewDisplay(mHolder);
            //获取预览图像数据
            buffers = new byte[VIDEO_WIGHT * VIDEO_HEIGHT * 4];
            mCamera.addCallbackBuffer(buffers);
            //获取视频预览的数据
            mCamera.setPreviewCallbackWithBuffer(this);
            mCamera.startPreview();
            mCamera.setDisplayOrientation(90);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void stopPreview() {
        if(mCamera != null){
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        }
    }


    /**
     * 切换摄像头
     */
    public void switchCamera(){
        if(cameraId == Camera.CameraInfo.CAMERA_FACING_BACK) {
            cameraId = Camera.CameraInfo.CAMERA_FACING_FRONT;
        }else {
            cameraId = Camera.CameraInfo.CAMERA_FACING_BACK;
        }
        stopPreview();
        startPreview();
    }

    @Override
    public void pushStar() {

    }

    @Override
    public void pushStop() {

    }

    @Override
    public void onPreviewFrame(byte[] bytes, Camera camera) {
        Log.i("VideoPusher", "onPreviewFrame size: " + bytes.length);
        if(mCamera != null){
            mCamera.addCallbackBuffer(buffers);
        }
    }
}
