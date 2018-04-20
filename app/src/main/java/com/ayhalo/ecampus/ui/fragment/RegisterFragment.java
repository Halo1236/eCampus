package com.ayhalo.ecampus.ui.fragment;

import com.ayhalo.ecampus.R;
import com.ayhalo.ecampus.ui.base.BaseFragment;

/**
 * com.ayhalo.ecampus.ui.fragment
 * Created by wuyh on 2018/4/20.
 */

public class RegisterFragment extends BaseFragment {

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

    }

    @Override
    protected void lazyFetchData() {

    }
}
