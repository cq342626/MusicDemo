package com.chenqi.musicdemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.chenqi.musicdemo.JarUtils.GlideUtils;
import com.chenqi.musicdemo.R;
import com.chenqi.musicdemo.common.BaseActivity;
import com.chenqi.musicdemo.common.BaseFragment;
import com.chenqi.musicdemo.joggle.MusicPlayChangeMusicInterface;
import com.chenqi.musicdemo.joggle.MusicPlayShowInterface;

/**
 * 底部布局
 */
public class QuickControlsFragment extends BaseFragment implements View.OnClickListener, SeekBar.OnSeekBarChangeListener, MusicPlayShowInterface {
    private SeekBar seekBar;// 进度条
    private TextView text_songer;// 歌手名
    private TextView text_song;// 歌名
    private ImageView imageToLyric;// 歌曲头像，点击跳转到歌词界面
    private Button bt_play, bt_next, bt_meum;// 播放/暂停、下一首、播放列表
    private GlideUtils glideUtils;//图片显示工具类

    private MusicPlayChangeMusicInterface musicPlayChangeMusicInterface;
    public void setMusicPlayChangeMusicInterface(MusicPlayChangeMusicInterface musicPlayChangeMusicInterface){
        this.musicPlayChangeMusicInterface = musicPlayChangeMusicInterface;
    }



    private static QuickControlsFragment fragment;
    public static QuickControlsFragment getIntence() {
        if (fragment == null)
            fragment = new QuickControlsFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.bottom_nav, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        BaseActivity baseActivity = new BaseActivity();
//        baseActivity.setMusicPlayShowInterface(this);
    }

    private void initView(View view) {
        glideUtils = new GlideUtils(getActivity());
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
        if (v.getId() == R.id.main_btn_stop) {//暂停和播放
            musicPlayChangeMusicInterface.musicPlay();
        } else if (v.getId() == R.id.main_btn_next) {//下一首
            musicPlayChangeMusicInterface.musicNext();
        } else if (v.getId() == R.id.main_btn_meum) {//打开播放菜单
            musicPlayChangeMusicInterface.musicMenu();
        } else if (v.getId() == R.id.main_songer_photo) {//打开音乐播放详情页
            musicPlayChangeMusicInterface.musicDetail();
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        musicPlayChangeMusicInterface.musicProgress(progress);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    /**
     * 显示歌手名
     */
    @Override
    public void showSongNmae(String songNmae) {
        if (songNmae != null)
            text_song.setText(songNmae);
    }

    /**
     * 显示歌手
     */
    @Override
    public void showSonger(String songer) {
        if (songer != null)
            text_songer.setText(songer);
    }

    /**
     * 更新进度条
     */
    @Override
    public void ShowSeeker(int position) {
        seekBar.setProgress(position);
    }

    /**
     * 显示图片
     */
    @Override
    public void ShowPic(String url) {
        if (url != null)
            glideUtils.downLoadImage(url, imageToLyric);
    }
}