package com.chenqi.musicdemo;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.chenqi.musicdemo.activity.LocalMusicLAcivity;
import com.chenqi.musicdemo.adapter.MyMainViewPagerAdapter;
import com.chenqi.musicdemo.bean.localMusic.Music;
import com.chenqi.musicdemo.common.Util;
import com.chenqi.musicdemo.fragment.FragmentThree;
import com.chenqi.musicdemo.fragment.MusicMineFragment;
import com.chenqi.musicdemo.fragment.MusicNetworkLibraryFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @InjectView(R.id.main_top_menu)
    TextView mainTopMenu;//左上角菜单
    @InjectView(R.id.tab_layout)
    TabLayout mTabLayout;//
    @InjectView(R.id.main_top_searsh)
    TextView mainTopSearsh;//搜索
    @InjectView(R.id.view_page)
    ViewPager mViewPager;
    @InjectView(R.id.main_navigation)
    NavigationView navigationView;//左侧菜单
    @InjectView(R.id.dl_main_drawer)
    DrawerLayout mDrawerLayout;

    public static Handler handler;
    public static int FALG_PLAYMUSIC_START = 100; // 开始播放音乐
    public static int FALG_PLAYMUSIC_PLAYORSTOP = 101; // 播放或暂停音乐
    public static int FALG_PLAYMUSIC_NEXT = 102; // 播放下一首
    public static int FALG_JUMPTO_LOCALMUSIC = 110; // 主页跳转到本地音乐界面
    public static int FALG_JUMPTO_RECENTPLAY = 111; // 主页跳转到最近播放界面
    public static int FALG_JUMPTO_DOWNLOADMUSIC = 112; // 主页跳转到下载界面

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        initView();
        getHanler();
        getData();
    }

    private void initView() {

        MyMainViewPagerAdapter viewPagerAdapter = new MyMainViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(MusicMineFragment.newInstance(), "我的");// 添加Fragment
        viewPagerAdapter.addFragment(MusicNetworkLibraryFragment.newInstance(), "乐库");
        viewPagerAdapter.addFragment(FragmentThree.newInstance(), "其他");
        mViewPager.setAdapter(viewPagerAdapter);// 设置适配器

        mTabLayout.addTab(mTabLayout.newTab().setText("我的"));// 给TabLayout添加Tab
        mTabLayout.addTab(mTabLayout.newTab().setText("乐库"));
        mTabLayout.addTab(mTabLayout.newTab().setText("其他"));
        mTabLayout.setupWithViewPager(mViewPager);// 给TabLayout设置关联ViewPager，如果设置了ViewPager，那么ViewPagerAdapter中的getPageTitle()方法返回的就是Tab上的标题

        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(MenuItem menuItem) {
                    menuItem.setChecked(true);
                    mDrawerLayout.closeDrawers();
                    return true;
                }
            });
        }
        mainTopSearsh.setOnClickListener(this);
    }

    private void getHanler() {
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == FALG_JUMPTO_LOCALMUSIC) {// 主页跳转到本地音乐
                    Intent intent = new Intent(MainActivity.this, LocalMusicLAcivity.class);
                    startActivity(intent);
                } else if (msg.what == FALG_JUMPTO_RECENTPLAY) {// 主页跳转到最近播放界面

                } else if (msg.what == FALG_JUMPTO_DOWNLOADMUSIC) {// 主页跳转到下载界面

                }
            }
        };
    }

    /**
     * 从多媒体数据库中获取音乐的信息
     */
    private void getData() {
        Cursor cursor = getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        int i = 0;
        while (!cursor.isAfterLast()) {
            int musicId = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media._ID));// 歌曲ID
            String name = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));// 歌曲的名称
            String title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));// 音乐标题
            String singer = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));// 歌曲的歌手名
            String album = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM));// 歌曲的专辑名：
            long size = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.SIZE));// 歌曲文件的大小
            long time = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));// 歌曲的总播放时长
            String url = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));// 歌曲文件的全路径
            if(name.contains("-")){
                singer = name.substring(0, name.indexOf("-"));
                name = name.substring(name.indexOf("-")+1, name.length());
            }
            Music m = new Music();
            m.setSong_id(musicId + "");
            m.setAlbum_title(album);
            m.setTitle(name);
            m.setDuration(time);
            m.setSize(size);
            m.setSong_path(url);
            m.setAuthor(singer);
            m.setListId(i);
            Util.ALL_MUSIC_LIST.add(m);
            ++i;
            cursor.moveToNext();
        }
        cursor.close();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.main_top_menu) {//打开菜单栏
            mDrawerLayout.openDrawer(GravityCompat.START);
        } else if (v.getId() == R.id.main_top_searsh) {//点击跳转到搜索界面

        }

    }
}
