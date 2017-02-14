package com.chenqi.musicdemo.common;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.chenqi.musicdemo.JarUtils.GlideUtils;
import com.chenqi.musicdemo.R;
import com.chenqi.musicdemo.service.MusicPlayService;

/**
 * 统一底部的基类activity总
 */
public class BaseActivity extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {

    private RelativeLayout parentRelativeLayout;//把父类activity和子类activity的view都add到这里

    private SeekBar seekBar;// 进度条
    private TextView text_songer;// 歌手名
    private TextView text_song;// 歌名
    private ImageView imageToLyric;// 歌曲头像，点击跳转到歌词界面
    private Button bt_play, bt_next, bt_meum;// 播放/暂停、下一首、播放列表

    private Context context;

    private GlideUtils glideUtils;//图片显示工具类

    private ServiceConnection connection;
    private MusicPlayService.MyBinder myBinder;//自定义的binder，用来与service交互
    private Intent intentService;//打开service的intent
    private MyBroadcast myBroadcast;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContentView(R.layout.bottom_base);
        initView();
        bindView();
    }

    private void initContentView(int layoutResID) {
        ViewGroup viewGroup = (ViewGroup) findViewById(android.R.id.content);
        viewGroup.removeAllViews();
        parentRelativeLayout = new RelativeLayout(this);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);//这个就是添加其他属性的，这个是在父元素的底部。
        viewGroup.addView(parentRelativeLayout);
        View view = LayoutInflater.from(this).inflate(layoutResID, null);
        view.setLayoutParams(params);
        view.setId(R.id.base_bottom);
        parentRelativeLayout.addView(view, params);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        View view = LayoutInflater.from(this).inflate(layoutResID, null);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
        params.addRule(RelativeLayout.ABOVE,R.id.base_bottom);
        parentRelativeLayout.addView(view, params);
    }

    private void initView() {
        context = getApplicationContext();
        glideUtils = new GlideUtils(context);
        text_song = (TextView) findViewById(R.id.main_text_song_name);
        text_songer = (TextView) findViewById(R.id.main_text_songer_name);
        imageToLyric = (ImageView) findViewById(R.id.main_songer_photo);
        bt_play = (Button) findViewById(R.id.main_btn_stop);
        bt_next = (Button) findViewById(R.id.main_btn_next);
        bt_meum = (Button) findViewById(R.id.main_btn_meum);
        seekBar = (SeekBar) findViewById(R.id.main_seekbar);
        imageToLyric.setOnClickListener(this);
        bt_play.setOnClickListener(this);
        bt_next.setOnClickListener(this);
        bt_meum.setOnClickListener(this);
        seekBar.setOnSeekBarChangeListener(this);
    }

    /**
     * 开启播放器的服务并且动态注册广播
     */
    private void bindView() {
        connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                myBinder = (MusicPlayService.MyBinder) service;
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
            }
        };
        intentService = new Intent(context, MusicPlayService.class);
        intentService.setAction("com.chenqi.musicdemo.service");
        intentService.setPackage("com.chenqi.musicdemo.service");
        bindService(intentService, connection, BIND_AUTO_CREATE);//打开播放服务并绑定

        myBroadcast = new MyBroadcast();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("myBroadcast");
        registerReceiver(myBroadcast, intentFilter);//开启广播
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.main_btn_stop) {//暂停和播放
            bMusicPlay();
        } else if (v.getId() == R.id.main_btn_next) {//下一首
            bMusicNext();
        } else if (v.getId() == R.id.main_btn_meum) {//打开播放菜单
            bMusicMenu();
        } else if (v.getId() == R.id.main_songer_photo) {//打开音乐播放详情页
            bMusicDetail();
        }
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

    public class MyBroadcast extends BroadcastReceiver {//自定义广播更新进度条
        @Override
        public void onReceive(Context context, Intent intent) {
            int progress = intent.getIntExtra("progress", 0);
            seekBar.setProgress(progress);
        }
    }

    /**
     * 暂停和播放
     */
    public void bMusicPlay() {
        myBinder.playBtn(1);
    }

    /**
     * 下一首
     */
    public void bMusicNext() {
        myBinder.nextBtn(1);
    }

    /**
     * /打开音乐播放菜单页
     */
    public void bMusicMenu() {
    }

    /**
     * /打开音乐播放详情页
     */
    public void bMusicDetail() {
    }
}
