package com.chenqi.musicdemo.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

import com.chenqi.musicdemo.JarUtils.OkHttpUtils;
import com.chenqi.musicdemo.common.AppConstants;
import com.chenqi.musicdemo.common.Util;

import java.io.IOException;

/**
 * 播放音乐的service
 */
public class MusicPlayService extends Service {

    private MediaPlayer player;// 定义一个播放控件
    private MyBinder myBinder = new MyBinder();// 定义一个binder，用于绑定服务
    private Intent intent = new Intent("MyBroadcast");// 定义一个intent
    private Handler handler;// 用于接收消息然后发送广播
    private boolean isOnceToPlay = true;// 是否是第一次播放（主要用于点击播放按钮）
    private OkHttpUtils okHttpUtils;

    @Override
    public void onCreate() {
        super.onCreate();
        player = new MediaPlayer();
        okHttpUtils = OkHttpUtils.getInstance();
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                int start = player.getCurrentPosition();// 播放进度
                int all = player.getDuration();// 播放总时长
                int progress = (int) (start * 100 * 1.f / all * 1.f);
                intent.putExtra("progress", progress);
                sendBroadcast(intent);// 发送广播通知进度条更新
                handler.sendEmptyMessageDelayed(1, 1000);// 循环handler消息
            }
        };
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        if (player != null)
            player.release();
        super.onDestroy();
    }

    public class MyBinder extends Binder implements MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnPreparedListener {
        private int play_id;

        /**
         * 播放
         */
        public void playMusic(int play_id) {// file
            // 本地或者网络音乐地址；isLocal：true是本地音乐，false是网络音乐
            this.play_id = play_id;
            player.reset();// 重用处于Error错误状态的MediaPlayer对象，可以通过调用reset()方法，使其恢复到idle空闲状态。
            try {
                if (Util.isLocalMusic) {
                    player.setDataSource(Util.ALL_MUSIC_LIST.get(play_id).getSong_path());// 播放路径
                    player.prepare();// 准备好本地音乐就播放
                } else {
                    getMusicUrl(play_id);
                }
                player.setOnPreparedListener(this);// 播放前调用
                player.setOnCompletionListener(this);// 播放完成后调用
                player.setOnErrorListener(this);// 播放发生错误时调用
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * 播放下一首
         */
        public void nextBtn(int play_id) {
            playMusic(play_id);
        }

        /**
         * 暂停和播放
         */
        public boolean playBtn(int play_id) {
            if (isOnceToPlay) {// 如果进入应用未播放，这时候第一次点击播放按钮时处理
                isOnceToPlay = false;
                playMusic(1);
                return player.isPlaying();
            }
            if (player.isPlaying()) {
                player.pause();
            } else {
                player.start();
            }
            return player.isPlaying();
        }

        /**
         * 拖动进度条快进快退
         */
        public void updatePlayProgress(int progress) {
            int curr = progress * player.getDuration() / 100;
            player.seekTo(curr);
        }

        @Override
        public boolean onError(MediaPlayer mp, int what, int extra) {
            return false;
        }

        @Override
        public void onCompletion(MediaPlayer mp) {
            nextBtn(play_id);
        }

        @Override
        public void onPrepared(MediaPlayer mp) {
            player.start();
            handler.sendEmptyMessageDelayed(1, 1000);
        }

    }

    /**
     * 获取网络歌曲的URL
     */
    public void getMusicUrl(final int position) {
        AppConstants.setBAIDU_MUSIC_PLAY(Util.ALL_MUSIC_LIST.get(position).getSong_id());
//        okHttpUtils.getRequest(AppConstants.HTTP_URL + AppConstants.BAIDU_MUSIC_PLAY, new OkHttpUtils.DataCallBack() {
//            @Override
//            public void requestSuccess(String result) throws Exception {
//                PlayVO playVO = new Gson().fromJson(result, PlayVO.class);
//                Util.ALL_MUSIC_LIST.get(position).setFile_extension(playVO.getBitrate().getFile_extension());
//                Util.ALL_MUSIC_LIST.get(position).setDuration(Long.parseLong(playVO.getBitrate().getFile_duration()));
//                Util.ALL_MUSIC_LIST.get(position).setSize(Long.parseLong(playVO.getBitrate().getFile_size()));
//                Util.ALL_MUSIC_LIST.get(position).setSong_path(playVO.getBitrate().getShow_link());
//                player.setDataSource(playVO.getBitrate().getShow_link());// 播放路径
//                player.prepareAsync();// 准备好网络就播放
//            }
//
//            @Override
//            public void requestFailure(DownloadManager.Request request, Exception e) {
//
//            }
//        });
    }

    @Override
    public IBinder onBind(Intent intent) {
        if (myBinder == null)
            myBinder = new MyBinder();
        return myBinder;
    }
}