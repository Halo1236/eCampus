package com.halo.campus.apprtc;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.TextView;

import com.halo.campus.R;


public class ConnectFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "ConnectFragment";
    private View controlView;
    private TextView callName;
    private ImageButton disConnect;
    private ImageButton connect;
    private Chronometer chronometer;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        controlView = inflater.inflate(R.layout.fragment_connect, container, false);
        callName = (TextView)controlView.findViewById(R.id.call_from_name);
        disConnect = (ImageButton)controlView.findViewById(R.id.button_connect_disconnect);
        connect = (ImageButton)controlView.findViewById(R.id.button_connect_connect);
        chronometer = (Chronometer)controlView.findViewById(R.id.connect_timer);
        disConnect.setOnClickListener(this);
        connect.setOnClickListener(this);
        return controlView;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_connect_connect:

                break;
            case R.id.button_connect_disconnect:
                break;
        }
    }
}
