package com.ayhalo.ecampus.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.ayhalo.ecampus.R;
import com.ayhalo.ecampus.ui.base.BaseActivity;
import com.ayhalo.ecampus.utils.SharePrefrenceUtils;
import com.bumptech.glide.Glide;

/**
 * com.ayhalo.ecampus.ui.activity
 * Created by wuyh on 2018/4/20.
 */

public class SplashActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected int getMenuId() {
        return 0;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        ImageView imageView = findView(R.id.boot_iv);
        Glide.with(this)
                .load(R.drawable.login_logo)
                .into(imageView);
    }

    @Override
    protected void loadData() {
        Boolean login = (Boolean) SharePrefrenceUtils.get(this, "islogin", false);
        new Handler().postDelayed(() -> {
            if (login) {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);
    }

    @Override
    public void onBackPressed() {
    }
}
