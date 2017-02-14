package com.chenqi.musicdemo.joggle;

/**
 * 改变底部播放布局状态的接口
 */
public interface MusicPlayShowInterface {

    public void showSongNmae(String songNmae);//改变歌曲名

    public void showSonger(String songer);//改变歌手名

    public void ShowSeeker(int position);//改变进度条

    public void ShowPic(String url);//改变图片显示

}
