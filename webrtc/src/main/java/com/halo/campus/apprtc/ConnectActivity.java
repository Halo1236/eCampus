/*
 *  Copyright 2014 The WebRTC Project Authors. All rights reserved.
 *
 *  Use of this source code is governed by a BSD-style license
 *  that can be found in the LICENSE file in the root of the source
 *  tree. An additional intellectual property rights grant can be found
 *  in the file PATENTS.  All contributing project authors may
 *  be found in the AUTHORS file in the root of the source tree.
 */

package com.halo.campus.apprtc;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;

import com.halo.campus.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Random;

/**
 * Handles the initial setup where the user selects which room to join.
 */
public class ConnectActivity extends Activity {
    private static final String TAG = "ConnectActivity";

    private EditText roomEditText;
    private SharedPreferences sharedPref;

    private String keyprefRoom;
    private String keyprefRoomList;
    private ArrayList<String> roomList;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get setting keys.
        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        keyprefRoom = getString(R.string.pref_room_key);
        keyprefRoomList = getString(R.string.pref_room_list_key);

        setContentView(R.layout.activity_connect);

        roomEditText = (EditText) findViewById(R.id.room_edittext);

        roomEditText.requestFocus();

        ImageButton connectButton = (ImageButton) findViewById(R.id.connect_button);
        connectButton.setOnClickListener(connectListener);
        checkMediaDecoder();
//        FragmentTransaction ft = getFragmentManager().beginTransaction();
//        ft.add(R.id.connect_container, new ConnectFragment());
//        ft.commit();
    }


    private void checkMediaDecoder() {
        int numCodecs = MediaCodecList.getCodecCount();
        MediaCodecInfo mediaCodecInfo = null;
        for (int i = 0; i < numCodecs && mediaCodecInfo == null; i++) {
            MediaCodecInfo info = MediaCodecList.getCodecInfoAt(i);
            Log.d(TAG, "checkMediaDecoder: "+info.getName());
            if (info.isEncoder()) {
                continue;
            }
            String[] types = info.getSupportedTypes();
            boolean found = false;
            for (int j = 0; j < types.length && !found; j++) {
                Log.d(TAG, "checkMediaDecoder: " + types[j]);
            }
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        String room = roomEditText.getText().toString();
        String roomListJson = new JSONArray(roomList).toString();
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(keyprefRoom, room);
        editor.putString(keyprefRoomList, roomListJson);
        editor.commit();
    }

    @Override
    public void onResume() {
        super.onResume();
        String room = sharedPref.getString(keyprefRoom, "");
        roomEditText.setText(room);
        roomList = new ArrayList<>();
        String roomListJson = sharedPref.getString(keyprefRoomList, null);
        if (roomListJson != null) {
            try {
                JSONArray jsonArray = new JSONArray(roomListJson);
                for (int i = 0; i < jsonArray.length(); i++) {
                    roomList.add(jsonArray.get(i).toString());
                }
            } catch (JSONException e) {
                Log.e(TAG, "Failed to load room list: " + e.toString());
            }
        }
    }


    private void connectToRoom(String roomId) {

        // roomId is random for loopback.
        boolean loopback = ConfigurationParams.loopback;
        if (loopback) {
            roomId = Integer.toString((new Random()).nextInt(100000000));
        }

        String roomUrl = ConfigurationParams.roomurl;

        // Video call enabled flag.
        boolean videoCallEnabled = ConfigurationParams.videoCallEnabled;

        // Use screencapture option.
        boolean useScreencapture = ConfigurationParams.useScreencapture;

        // Use Camera2 option.
        boolean useCamera2 = ConfigurationParams.useCamera2;

        // Get default codecs.
        String videoCodec = ConfigurationParams.videoCodec;
        String audioCodec = ConfigurationParams.audioCodec;

        // Check HW codec flag.
        boolean hwCodec = ConfigurationParams.videoCodecHwAcceleration;

        // Check Capture to texture.
        boolean captureToTexture = ConfigurationParams.captureToTexture;

        // Check FlexFEC.
        boolean flexfecEnabled = ConfigurationParams.videoFlexfecEnabled;

        // Check Disable Audio Processing flag.
        boolean noAudioProcessing = ConfigurationParams.noAudioProcessing;

        boolean aecDump = ConfigurationParams.aecDump;

        // Check OpenSL ES enabled flag.
        boolean useOpenSLES = ConfigurationParams.useOpenSLES;

        // Check Disable built-in AEC flag.
        boolean disableBuiltInAEC = ConfigurationParams.disableBuiltInAEC;

        // Check Disable built-in AGC flag.
        boolean disableBuiltInAGC = ConfigurationParams.disableBuiltInAGC;

        // Check Disable built-in NS flag.
        boolean disableBuiltInNS = ConfigurationParams.disableBuiltInNS;

        // Check Enable level control.
        boolean enableLevelControl = ConfigurationParams.enableLevelControl;

        // Check Disable gain control
        boolean disableWebRtcAGCAndHPF = ConfigurationParams.disableWebRtcAGCAndHPF;

        // Get video resolution from settings.
        int videoWidth = ConfigurationParams.videoWidth;
        int videoHeight = ConfigurationParams.videoHeight;

        // Get camera fps from settings.
        int cameraFps = ConfigurationParams.videoFps;

        // Get video and audio start bitrate.
        int videoStartBitrate = ConfigurationParams.videoMaxBitrate;
        int audioStartBitrate = ConfigurationParams.audioStartBitrate;
        boolean tracing = ConfigurationParams.tracing;

        // Get datachannel options
        boolean dataChannelEnabled = ConfigurationParams.dataChannelEnabled;
        boolean ordered = ConfigurationParams.ordered;
        boolean negotiated = ConfigurationParams.negotiated;
        int maxRetrMs = ConfigurationParams.maxRetransmitTimeMs;
        int maxRetr = ConfigurationParams.maxRetransmits;
        int id = ConfigurationParams.idmsg;
        String protocol = ConfigurationParams.protocol;

        // Start AppRTCMobile activity.
        Log.d(TAG, "Connecting to room " + roomId + " at URL " + roomUrl);
        Uri uri = Uri.parse(roomUrl);
        Intent intent = new Intent(this, CallActivity.class);
        intent.setData(uri);
        intent.putExtra(CallActivity.EXTRA_ROOMID, roomId);
        intent.putExtra(CallActivity.EXTRA_LOOPBACK, loopback);
        intent.putExtra(CallActivity.EXTRA_VIDEO_CALL, videoCallEnabled);
        intent.putExtra(CallActivity.EXTRA_SCREENCAPTURE, useScreencapture);
        intent.putExtra(CallActivity.EXTRA_CAMERA2, useCamera2);
        intent.putExtra(CallActivity.EXTRA_VIDEO_WIDTH, videoWidth);
        intent.putExtra(CallActivity.EXTRA_VIDEO_HEIGHT, videoHeight);
        intent.putExtra(CallActivity.EXTRA_VIDEO_FPS, cameraFps);
        intent.putExtra(CallActivity.EXTRA_VIDEO_BITRATE, videoStartBitrate);
        intent.putExtra(CallActivity.EXTRA_VIDEOCODEC, videoCodec);
        intent.putExtra(CallActivity.EXTRA_HWCODEC_ENABLED, hwCodec);
        intent.putExtra(CallActivity.EXTRA_CAPTURETOTEXTURE_ENABLED, captureToTexture);
        intent.putExtra(CallActivity.EXTRA_FLEXFEC_ENABLED, flexfecEnabled);
        intent.putExtra(CallActivity.EXTRA_NOAUDIOPROCESSING_ENABLED, noAudioProcessing);
        intent.putExtra(CallActivity.EXTRA_AECDUMP_ENABLED, aecDump);
        intent.putExtra(CallActivity.EXTRA_OPENSLES_ENABLED, useOpenSLES);
        intent.putExtra(CallActivity.EXTRA_DISABLE_BUILT_IN_AEC, disableBuiltInAEC);
        intent.putExtra(CallActivity.EXTRA_DISABLE_BUILT_IN_AGC, disableBuiltInAGC);
        intent.putExtra(CallActivity.EXTRA_DISABLE_BUILT_IN_NS, disableBuiltInNS);
        intent.putExtra(CallActivity.EXTRA_ENABLE_LEVEL_CONTROL, enableLevelControl);
        intent.putExtra(CallActivity.EXTRA_DISABLE_WEBRTC_AGC_AND_HPF, disableWebRtcAGCAndHPF);
        intent.putExtra(CallActivity.EXTRA_AUDIO_BITRATE, audioStartBitrate);
        intent.putExtra(CallActivity.EXTRA_AUDIOCODEC, audioCodec);
        intent.putExtra(CallActivity.EXTRA_TRACING, tracing);


        intent.putExtra(CallActivity.EXTRA_DATA_CHANNEL_ENABLED, dataChannelEnabled);

        if (dataChannelEnabled) {
            intent.putExtra(CallActivity.EXTRA_ORDERED, ordered);
            intent.putExtra(CallActivity.EXTRA_MAX_RETRANSMITS_MS, maxRetrMs);
            intent.putExtra(CallActivity.EXTRA_MAX_RETRANSMITS, maxRetr);
            intent.putExtra(CallActivity.EXTRA_PROTOCOL, protocol);
            intent.putExtra(CallActivity.EXTRA_NEGOTIATED, negotiated);
            intent.putExtra(CallActivity.EXTRA_ID, id);
        }

        startActivity(intent);
    }


    private final OnClickListener connectListener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            connectToRoom(roomEditText.getText().toString());
        }
    };
}
