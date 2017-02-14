package com.chenqi.musicdemo.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chenqi.musicdemo.JarUtils.OkHttpUtils;
import com.chenqi.musicdemo.R;

/**
 * �ֿ����ҳ
 */
public class MusicNetworkLibraryFragment extends Fragment{

	private TabLayout tabLayout;
	private ViewPager viewPager;
	private OkHttpUtils okHttpUtils;

	private static MusicNetworkLibraryFragment musicLibraryFragment;
	public static Fragment newInstance() {
		if(musicLibraryFragment == null)
			musicLibraryFragment = new MusicNetworkLibraryFragment();
		return musicLibraryFragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_music_network_llist, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		tabLayout = (TabLayout) view.findViewById(R.id.fragment_musicnetworkl_tabs);
		viewPager = (ViewPager) view.findViewById(R.id.fragment_musicnetworkl_viewpager);

//		MyMainViewPagerAdapter viewPagerAdapter = new MyMainViewPagerAdapter(((AppCompatActivity) getActivity()).getSupportFragmentManager());
//		viewPagerAdapter.addFragment(MusicNetworkL_RecommendFragment.newInstance(), "推荐");// 添加Fragment
//		viewPagerAdapter.addFragment(MusicNetworkL_SongSheetFragment.newInstance(), "歌单");// 添加Fragment
//		viewPagerAdapter.addFragment(MusicNetworkL_ListFragment.newInstance(), "榜单");// 添加Fragment
//		viewPagerAdapter.addFragment(MusicNetworkL_VideoFragment.newInstance(), "视频");// 添加Fragment
//		viewPagerAdapter.addFragment(MusicNetworkL_KSongFragment.newInstance(), "K歌");// 添加Fragment
//		viewPager.setAdapter(viewPagerAdapter);// 设置适配器
//
//		tabLayout.addTab(tabLayout.newTab().setText("推荐"));// 给TabLayout添加Tab
//		tabLayout.addTab(tabLayout.newTab().setText("歌单"));
//		tabLayout.addTab(tabLayout.newTab().setText("榜单"));
//		tabLayout.addTab(tabLayout.newTab().setText("视频"));
//		tabLayout.addTab(tabLayout.newTab().setText("K歌"));
//		tabLayout.setupWithViewPager(viewPager);
	}
	
}
