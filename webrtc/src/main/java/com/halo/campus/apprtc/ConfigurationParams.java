package com.halo.campus.apprtc;

/**
 * Created by halo on 17-12-14.
 */

public class ConfigurationParams {

    public static final String roomurl = "http://192.168.2.117:8080";

    public static final boolean videoCallEnabled = true;

    //是否共享屏幕
    public static final boolean useScreencapture = false;

    //是否使扬声器用
    public static final String useSpeakerphone = "auto";

    public static final boolean useCamera2 = true;

    public static final boolean captureToTexture = true;

    public static final boolean loopback = false;

    public static final boolean tracing = false;

    public static final int videoWidth = 640;

    public static final int videoHeight = 480;

    public static final int videoFps = 15;

    public static final int videoMaxBitrate = 1700;

    public static final String videoCodec = "H264";

    public static final boolean videoCodecHwAcceleration = true;

    public static final boolean videoFlexfecEnabled = false;

    public static final int audioStartBitrate = 32;

    public static final String audioCodec = "OPUS";

    public static final boolean noAudioProcessing = true;

    public static final boolean aecDump = false;

    public static final boolean useOpenSLES = true;

    public static final boolean disableBuiltInAEC = false;

    public static final boolean disableBuiltInAGC = false;

    public static final boolean disableBuiltInNS = false;

    public static final boolean enableLevelControl = false;

    public static final boolean disableWebRtcAGCAndHPF = false;


    //是否启用数据通道
    public static final boolean dataChannelEnabled = false;
    public static final boolean ordered = true;         //数据通道是否保证按序传输数据
    public static final int maxRetransmitTimeMs = -1;   //在信息失败前的最大重传时间
    public static final int maxRetransmits = -1;        //在信息失败前的最大重传次数
    public static final String protocol = "";           //允许使用一个自协议
    public static final boolean negotiated = false;     //如果设为true，将一处对方的数据通道的自动设置
    public static final int idmsg = 1;                  //为数据通道提供一个自己定义的ID

}
