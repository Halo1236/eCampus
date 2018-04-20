package com.ayhalo.ecampus.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.ImageView;

import com.ayhalo.ecampus.R;
import com.ayhalo.ecampus.ui.base.BaseActivity;
import com.ayhalo.ecampus.ui.fragment.LoginFragment;
import com.ayhalo.ecampus.ui.fragment.RegisterFragment;
import com.bumptech.glide.Glide;

/**
 * com.ayhalo.ecampus.ui.activity
 * Created by wuyh on 2018/4/20.
 */

public class LoginActivity extends BaseActivity {

    private ImageView imageView;

    @Override
    protected int getLayoutId() {
        return R.layout.artivity_login_container;
    }

    @Override
    protected int getMenuId() {
        return 0;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        imageView = findView(R.id.login_logo);
        Glide.with(this)
                .load(R.drawable.login_logo)
                .into(imageView);
    }

    @Override
    protected void loadData() {
        replaceFragment(LoginFragment.newInstance());
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.login_container, fragment);
        transaction.commit();
    }

}
