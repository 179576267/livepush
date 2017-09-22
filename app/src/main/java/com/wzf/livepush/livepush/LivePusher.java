package com.wzf.livepush.livepush;

import android.view.SurfaceHolder;

/**
 * @Description:
 * @author: wangzhenfei
 * @date: 2017-09-22 17:07
 */

public class LivePusher {
    private VideoPusher videoPusher;
    private AudioPusher audioPusher;

    public LivePusher(SurfaceHolder holder) {
        prepare(holder);
    }

    private void prepare(SurfaceHolder holder) {
        videoPusher = new VideoPusher(holder);
        audioPusher = new AudioPusher();
    }

    public void switchCamera(){
        videoPusher.switchCamera();
    }
}
