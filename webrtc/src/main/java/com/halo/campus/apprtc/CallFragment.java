/*
 *  Copyright 2015 The WebRTC Project Authors. All rights reserved.
 *
 *  Use of this source code is governed by a BSD-style license
 *  that can be found in the LICENSE file in the root of the source
 *  tree. An additional intellectual property rights grant can be found
 *  in the file PATENTS.  All contributing project authors may
 *  be found in the AUTHORS file in the root of the source tree.
 */

package com.halo.campus.apprtc;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.ImageButton;

import com.halo.campus.R;

import org.webrtc.RendererCommon.ScalingType;

/**
 * Fragment for call control.
 */
public class CallFragment extends Fragment implements CallTimer {

    private View controlView;
    private ImageButton disconnectButton;
    private ImageButton cameraSwitchButton;
    private ImageButton telSwitchButton;
    //private TextView timer;
    //private ImageButton videoScalingButton;
    private Chronometer chronometer;
    private ImageButton toggleMuteButton;
    private OnCallEvents callEvents;
    private ScalingType scalingType;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        controlView = inflater.inflate(R.layout.fragment_call, container, false);

        // Create UI controls.
        disconnectButton = (ImageButton) controlView.findViewById(R.id.button_call_disconnect);
        cameraSwitchButton = (ImageButton) controlView.findViewById(R.id.button_call_switch_camera);
        telSwitchButton = (ImageButton) controlView.findViewById(R.id.button_call_switch_speaker);
        //videoScalingButton = (ImageButton) controlView.findViewById(R.id.button_call_scaling_mode);
        toggleMuteButton = (ImageButton) controlView.findViewById(R.id.button_call_toggle_mic);
        //timer = (TextView) controlView.findViewById(R.id.call_time);
        chronometer = (Chronometer) controlView.findViewById(R.id.call_timer);

        // Add buttons click events.
        disconnectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callEvents.onCallHangUp();
            }
        });

        cameraSwitchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callEvents.onCameraSwitch();
            }
        });

        telSwitchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callEvents.onTelSwitch();
            }
        });


//        videoScalingButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (scalingType == ScalingType.SCALE_ASPECT_FILL) {
//                    videoScalingButton.setBackgroundResource(R.drawable.ic_action_full_screen);
//                    scalingType = ScalingType.SCALE_ASPECT_FIT;
//                } else {
//                    videoScalingButton.setBackgroundResource(R.drawable.ic_action_return_from_full_screen);
//                    scalingType = ScalingType.SCALE_ASPECT_FILL;
//                }
//                callEvents.onVideoScalingSwitch(scalingType);
//            }
//        });
//        scalingType = ScalingType.SCALE_ASPECT_FILL;


        toggleMuteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean enabled = callEvents.onToggleMic();
                toggleMuteButton.setBackgroundResource(enabled ? R.drawable.mic_open : R.drawable.mic_close);
            }
        });

        return controlView;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        callEvents.onCallHangUp();
        chronometer.stop();
    }


    @Override
    public void onStart() {
        super.onStart();
        if (!ConfigurationParams.videoCallEnabled) {
            cameraSwitchButton.setVisibility(View.INVISIBLE);
        }
    }

    // TODO(sakal): Replace with onAttach(Context) once we only support API level 23+.
    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        callEvents = (OnCallEvents) activity;
    }

    @Override
    public void OnCallStartTimer() {
        chronometer.setBase(SystemClock.elapsedRealtime());
        int hour = (int) ((SystemClock.elapsedRealtime() - chronometer.getBase()) / 1000 / 60);
        chronometer.setFormat(""+String.valueOf(hour)+":%s");
        chronometer.start();
    }
}
