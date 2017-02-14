package com.chenqi.musicdemo.command;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.chenqi.musicdemo.JarUtils.GlideUtils;
import com.chenqi.musicdemo.R;

/**
 * 自定义底部布局控件
 */
public abstract class ButtomRelativeLayout extends RelativeLayout implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {

    private SeekBar seekBar;// 进度条
    private TextView text_songer;// 歌手名
    private TextView text_song;// 歌名
    private ImageView imageToLyric;// 歌曲头像，点击跳转到歌词界面
    private Button bt_play, bt_next, bt_meum;// 播放/暂停、下一首、播放列表
    private GlideUtils glideUtils;//图片显示工具类
    private Context context;

    public ButtomRelativeLayout(Context context) {
        super(context);
        initView(context);
    }

    public ButtomRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ButtomRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        this.context = context;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
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
        bOnProgressChanged(seekBar, progress, fromUser);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        bOnStartTrackingTouch(seekBar);
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        bOnStopTrackingTouch(seekBar);
    }


    /**
     * 设置歌名
     */
    public void setSongText(String text) {
        text_song.setText(text);
    }

    /**
     * 设置歌手名
     */
    public void setSongerText(String text) {
        text_songer.setText(text);
    }

    /**
     * 设置歌曲头像
     */
    public void setImageToLyric(String url) {
        try {
            glideUtils.downLoadImage(url, imageToLyric);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 暂停和播放
     */
    protected abstract void bMusicPlay();

    /**
     * 下一首
     */
    protected abstract void bMusicNext();

    /**
     * /打开音乐播放详情页
     */
    protected abstract void bMusicMenu();

    /**
     * /打开音乐播放详情页
     */
    protected abstract void bMusicDetail();

    /**
     *
     */
    protected abstract void bOnProgressChanged(SeekBar seekBar, int progress, boolean fromUser);
    /**
     *
     */
    protected abstract void bOnStartTrackingTouch(SeekBar seekBar);
    /**
     *
     */
    protected abstract void bOnStopTrackingTouch(SeekBar seekBar);
}
