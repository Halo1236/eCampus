package com.ayhalo.ecampus.ui.base;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.MenuRes;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.ayhalo.ecampus.utils.StatusBarUtils;

/**
 * com.ayhalo.ecampus.ui.base
 * Created by wuyh on 2018/3/1.
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected abstract
    @LayoutRes
    int getLayoutId();

    protected abstract
    @MenuRes
    int getMenuId();

    protected abstract void initViews(Bundle savedInstanceState);

    protected abstract void loadData();

//    static {
//        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
//    }

//    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initTheme();
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
//        initToolBar();
        initViews(savedInstanceState);
    }

    @Override
    protected void onStart() {
        loadData();
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

//    private void initToolBar() {
//        toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//    }

    protected void setDisplayHomeAsUpEnabled(boolean enable) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(enable);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (getMenuId() != 0) {
            getMenuInflater().inflate(getMenuId(), menu);
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void initTheme() {
    }

    protected void hideSystemUI() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    protected void showSystemUI() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
    }

    protected <T extends View> T findView(@IdRes int id) {
        return (T) findViewById(id);
    }

}
