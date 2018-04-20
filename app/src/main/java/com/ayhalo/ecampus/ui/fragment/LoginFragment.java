package com.ayhalo.ecampus.ui.fragment;


import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.telecom.Call;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ayhalo.ecampus.Global;
import com.ayhalo.ecampus.R;
import com.ayhalo.ecampus.network.CallServer;
import com.ayhalo.ecampus.network.HttpListener;
import com.ayhalo.ecampus.ui.base.BaseFragment;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;


public class LoginFragment extends BaseFragment implements View.OnClickListener, HttpListener<String> {

    private EditText editText_tel;
    private EditText editText_passwd;
    private TextView toregister;
    private Button login;
    private Request<String> request;

    public LoginFragment() {
    }

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    protected void initViews() {
        login = findView(R.id.btn_login);
        editText_tel = findView(R.id.tel_number);
        editText_passwd = findView(R.id.password);
        toregister = findView(R.id.no_regsiter);
        toregister.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    @Override
    protected void lazyFetchData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.no_regsiter:
                replaceFragment(RegisterFragment.newInstance());
                break;
            case R.id.btn_login:
                login();
                break;
        }
    }

    private void login() {
        String telephone = editText_tel.getText().toString().trim();
        String password = editText_passwd.getText().toString().trim();
        String url = Global.HOST + "/api/login";
        request = NoHttp.createStringRequest(url, RequestMethod.POST);
        request.add("telephone",telephone);
        request.add("password",password);
        CallServer.getInstance().request(Global.LOGINWHAT, request,
                getActivity(), this, false, false);
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.login_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onSucceed(int what, Response<String> response) {

    }

    @Override
    public void onFailed(int what, Response<String> response) {

    }
}
