package com.ayhalo.ecampus.ui.fragment;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ayhalo.ecampus.Global;
import com.ayhalo.ecampus.R;
import com.ayhalo.ecampus.databases.bean.Result;
import com.ayhalo.ecampus.network.CallServer;
import com.ayhalo.ecampus.network.HttpListener;
import com.ayhalo.ecampus.network.ResultToGson;
import com.ayhalo.ecampus.ui.activity.MainActivity;
import com.ayhalo.ecampus.ui.base.BaseFragment;
import com.ayhalo.ecampus.utils.SharePrefrenceUtils;
import com.ayhalo.ecampus.utils.ToastUtils;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

/**
 * com.ayhalo.ecampus.ui.fragment
 * Created by wuyh on 2018/4/20.
 */

public class RegisterFragment extends BaseFragment implements View.OnClickListener, HttpListener<String> {

    private EditText editText_tel;
    private EditText editText_xueid;
    private EditText editText_username;
    private EditText editText_belong;
    private EditText editText_passwd;
    private EditText editText_re_passwd;
    private Button btn_register;
    private String telephone;
    private String password;
    private String xueid;
    private String username;
    private String belong;
    private String re_password;
    private Request<String> request;

    public RegisterFragment() {
    }

    public static RegisterFragment newInstance() {
        RegisterFragment fragment = new RegisterFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_register;
    }

    @Override
    protected void initViews() {
        editText_tel = findView(R.id.tel_number);
        editText_xueid = findView(R.id.xueid);
        editText_username = findView(R.id.username);
        editText_belong = findView(R.id.belong);
        editText_passwd = findView(R.id.password);
        editText_re_passwd = findView(R.id.re_password);
        btn_register = findView(R.id.btn_register);
        btn_register.setOnClickListener(this);
    }

    @Override
    protected void lazyFetchData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_register:
                register();
                break;
        }
    }

    private void register() {
        telephone = editText_tel.getText().toString().trim();
        password = editText_passwd.getText().toString().trim();
        xueid = editText_xueid.getText().toString().trim();
        username = editText_username.getText().toString().trim();
        belong = editText_belong.getText().toString().trim();
        re_password = editText_re_passwd.getText().toString().trim();

        if (TextUtils.isEmpty(telephone) || TextUtils.isEmpty(password) || TextUtils.isEmpty(xueid)
                || TextUtils.isEmpty(username) || TextUtils.isEmpty(belong) || TextUtils.isEmpty(re_password)) {
            ToastUtils.showShort("输入信息不能为空");

        } else if (!re_password.equals(password)) {
            ToastUtils.showShort("两次密码不同");
        } else {
            String url = Global.HOST + "/api/register";
            request = NoHttp.createStringRequest(url, RequestMethod.POST);
            request.add("telephone", telephone);
            request.add("password", password);
            request.add("xueid", xueid);
            request.add("belong", belong);
            request.add("username", username);
            CallServer.getInstance().request(Global.REGISTERWHAT, request,
                    getActivity(), this, false, false);
        }
    }

    @Override
    public void onSucceed(int what, Response<String> response) {
        switch (what){
            case Global.REGISTERWHAT:
                Result result = ResultToGson.handleResultToGson(response.get());
                if (result != null) {
                    if (result.getError().equals("false")) {
                        SharePrefrenceUtils.put(getContext(), "islogin", true);
                        SharePrefrenceUtils.put(getContext(), "telephone", telephone);
                        SharePrefrenceUtils.put(getContext(), "password", password);
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                        ToastUtils.showShort("注册成功");
                    } else if (result.getError().equals("true")) {
                        ToastUtils.showShort("注册失败，请检查信息");
                    }
                }
                break;
        }
    }

    @Override
    public void onFailed(int what, Response<String> response) {

    }
}
