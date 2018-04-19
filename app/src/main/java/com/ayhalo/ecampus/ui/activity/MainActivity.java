package com.ayhalo.ecampus.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.ayhalo.ecampus.R;
import com.ayhalo.ecampus.ui.adapter.ViewPagerAdapter;
import com.ayhalo.ecampus.ui.base.BaseActivity;
import com.ayhalo.ecampus.ui.fragment.NewsFragment;
import com.ayhalo.ecampus.ui.fragment.NoticeFragment;
import com.ayhalo.ecampus.ui.viewpager.TabLayoutView;
import com.ayhalo.ecampus.utils.DoubleClickExit;
import com.ayhalo.ecampus.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    public static final String TAG = "MainActivity";
    private ViewPager viewPager;
    private TabLayoutView tabLayoutView;
    private int[] imgs = {R.drawable.ic_news, R.drawable.ic_notice
            , R.drawable.ic_instant_messaging, R.drawable.ic_mine};
    private int[] imgsselect = {R.drawable.ic_news_select, R.drawable.ic_notice_select
            , R.drawable.ic_instant_messaging_select, R.drawable.ic_mine_select};
    private String[] tabtxts = {"新闻", "通知", "通讯录", "我的"};

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected int getMenuId() {
        return 0;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        viewPager = findView(R.id.main_viewpager);
        tabLayoutView = findView(R.id.main_tabview);
        initFragments();
        tabLayoutView.setDataSource(tabtxts, imgs, imgsselect, 0);
        tabLayoutView.setImageStyle(16, 16);
        tabLayoutView.setTextStyle(10, R.color.tab_background_textColor, R.color.tab_background_textColor_select);
        tabLayoutView.initDatas();
        tabLayoutView.setOnItemOnclickListener(new TabLayoutView.OnItemOnclickListener() {
            @Override
            public void onItemClick(int index) {
                viewPager.setCurrentItem(index, false);
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                viewPager.setCurrentItem(position, false);
                tabLayoutView.setSelectStyle(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @Override
    protected void loadData() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void initFragments() {
        List<Fragment> fragmentPages = new ArrayList<>();
        fragmentPages.add(NewsFragment.newInstance());
        fragmentPages.add(NoticeFragment.newInstance());
        fragmentPages.add(NewsFragment.newInstance());
        fragmentPages.add(NewsFragment.newInstance());
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), fragmentPages);
        viewPager.setAdapter(viewPagerAdapter);
    }

    @Override
    public void onBackPressed() {
        if (!DoubleClickExit.check()) {
            ToastUtils.showShort("再按一次退出App!");
        } else {
            super.onBackPressed();
            System.exit(0);
        }
    }

}
