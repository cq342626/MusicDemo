package com.chenqi.musicdemo.common;


import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.chenqi.musicdemo.R;

/**
 * 自定义底部布局
 */
public class MusicBottomLayout extends ViewGroup {
    public MusicBottomLayout(Context context) {
        super(context);
    }

    public MusicBottomLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.bottom_nav, null);
    }

    public MusicBottomLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

}
