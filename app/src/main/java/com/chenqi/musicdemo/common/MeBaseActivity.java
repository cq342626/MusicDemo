package com.chenqi.musicdemo.common;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.chenqi.musicdemo.JarUtils.GlideUtils;
import com.chenqi.musicdemo.R;

/**
 *
 */
public class MeBaseActivity extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {

    private SeekBar seekBar;// 进度条
    private TextView text_songer;// 歌手名
    private TextView text_song;// 歌名
    private ImageView imageToLyric;// 歌曲头像，点击跳转到歌词界面
    private Button bt_play, bt_next, bt_meum;// 播放/暂停、下一首、播放列表
    private GlideUtils glideUtils;//图片显示工具类
    private Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        showQuickControl(true);
    }

    private void showQuickControl(boolean b) {

        View view = LayoutInflater.from(context).inflate(R.layout.bottom_nav, null);
        glideUtils = new GlideUtils(context);
        text_song = (TextView) view.findViewById(R.id.main_text_song_name);
        text_songer = (TextView) view.findViewById(R.id.main_text_songer_name);
        imageToLyric = (ImageView) view.findViewById(R.id.main_songer_photo);
        bt_play = (Button) view.findViewById(R.id.main_btn_stop);
        bt_next = (Button) view.findViewById(R.id.main_btn_next);
        bt_meum = (Button) view.findViewById(R.id.main_btn_meum);
        seekBar = (SeekBar) view.findViewById(R.id.main_seekbar);
        imageToLyric.setOnClickListener(this);
        bt_play.setOnClickListener(this);
        bt_next.setOnClickListener(this);
        bt_meum.setOnClickListener(this);
        seekBar.setOnSeekBarChangeListener(this);

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
