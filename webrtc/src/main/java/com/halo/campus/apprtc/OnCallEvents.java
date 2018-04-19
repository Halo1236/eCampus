package com.halo.campus.apprtc;

import org.webrtc.RendererCommon;

/**
 * Created by Halo on 2017/12/21.
 */

public interface OnCallEvents {

    void onCallHangUp();

    void onCameraSwitch();

    void onTelSwitch();

    void onVideoScalingSwitch(RendererCommon.ScalingType scalingType);

    void onCaptureFormatChange(int width, int height, int framerate);

    boolean onToggleMic();
}