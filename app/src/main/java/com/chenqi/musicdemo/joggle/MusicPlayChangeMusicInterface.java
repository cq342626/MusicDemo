package com.chenqi.musicdemo.joggle;

/**
 * 点击底部布局操作歌曲的接口
 */
public interface MusicPlayChangeMusicInterface {

    public void musicPlay();//暂停播放音乐

    public void musicNext();//下一首

    public void musicProgress(int position);//拖动进度条

    public void musicMenu();//打开菜单

    public void musicDetail();//进入音乐播放界面

}
