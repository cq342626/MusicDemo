package com.chenqi.musicdemo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.LinkedList;

public class MyMainViewPagerAdapter extends FragmentPagerAdapter {
    private final LinkedList<Fragment> mFragments = new LinkedList<Fragment>();//添加的Fragment的集合
    private final LinkedList<String> mFragmentsTitles = new LinkedList<String>();//每个Fragment对应的title的集合
    public MyMainViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    /**
     * @param fragment      添加Fragment
     * @param fragmentTitle Fragment的标题，即TabLayout中对应Tab的标题
     */
    public void addFragment(Fragment fragment, String fragmentTitle) {
        mFragments.add(fragment);
        mFragmentsTitles.add(fragmentTitle);
    }

    @Override
    public Fragment getItem(int position) {
        //得到对应position的Fragment
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        //返回Fragment的数量
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        //得到对应position的Fragment的title
        return mFragmentsTitles.get(position);
    }
}  