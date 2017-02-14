package com.chenqi.musicdemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.chenqi.musicdemo.R;
import com.chenqi.musicdemo.adapter.MyMainViewPagerAdapter;
import com.chenqi.musicdemo.common.BaseActivity;
import com.chenqi.musicdemo.fragment.MusicLocalMFragment2;
import com.chenqi.musicdemo.fragment.MusicLocalMFragment3;
import com.chenqi.musicdemo.fragment.MusicLocalMFragment4;
import com.chenqi.musicdemo.fragment.MusicLocalMFragment5;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 本地音乐列表播放界面
 */
public class LocalMusicLAcivity extends BaseActivity implements View.OnClickListener {
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.tabs)
    TabLayout tabs;
    @InjectView(R.id.viewpager)
    ViewPager viewpager;
    @InjectView(R.id.main_content)
    CoordinatorLayout mainContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_local_main);
        ButterKnife.inject(this);
        initView();
    }

    private void initView(){

        toolbar.setTitle("返回");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);// 显示左边的箭头
        toolbar.setNavigationOnClickListener(this);

        MyMainViewPagerAdapter viewPagerAdapter = new MyMainViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(MusicLocalMFragment2.newInstance(), "单曲");// 添加Fragment
        viewPagerAdapter.addFragment(MusicLocalMFragment3.newInstance(), "歌手");// 添加Fragment
        viewPagerAdapter.addFragment(MusicLocalMFragment4.newInstance(), "专辑");// 添加Fragment
        viewPagerAdapter.addFragment(MusicLocalMFragment5.newInstance(), "文件夹");// 添加Fragment
        viewpager.setAdapter(viewPagerAdapter);// 设置适配器

        tabs.addTab(tabs.newTab().setText("单曲"));// 给TabLayout添加Tab
        tabs.addTab(tabs.newTab().setText("歌手"));
        tabs.addTab(tabs.newTab().setText("专辑"));
        tabs.addTab(tabs.newTab().setText("文件夹"));
        tabs.setupWithViewPager(viewpager);
    }



    @Override
    public void onClick(View v) {
        finish();
    }
}