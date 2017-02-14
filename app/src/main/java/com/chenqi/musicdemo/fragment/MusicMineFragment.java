package com.chenqi.musicdemo.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chenqi.musicdemo.MainActivity;
import com.chenqi.musicdemo.R;
import com.chenqi.musicdemo.common.Util;

/**
 * 本地音乐的主页
 */
public class MusicMineFragment extends Fragment implements OnClickListener {

	private Context context;
	private RelativeLayout layout_localMusic, layout_Recentplay, layout_downLoad;
	private TextView text_localMusicSize;

	private static MusicMineFragment localFragment = new MusicMineFragment();

	public static Fragment newInstance() {
		if (localFragment == null)
			localFragment = new MusicMineFragment();
		return localFragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_locall_main, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		initView(view);
	}

	// 初始化控件和数据
	public void initView(View view) {
		context = getActivity();
		layout_localMusic = (RelativeLayout) view.findViewById(R.id.fragment_l_l_main_Local);
		layout_Recentplay = (RelativeLayout) view.findViewById(R.id.fragment_l_l_main_Recentplay);
		layout_downLoad = (RelativeLayout) view.findViewById(R.id.fragment_l_l_main_MyDownLoad);
		text_localMusicSize = (TextView) view.findViewById(R.id.fragment_l_l_main_Localtext2);
		text_localMusicSize.setText(Util.ALL_MUSIC_LIST.size() + "首");
		layout_localMusic.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.fragment_l_l_main_Local:
				Message msg1 = new Message();
				msg1.what = MainActivity.FALG_JUMPTO_LOCALMUSIC;
				MainActivity.handler.sendMessage(msg1);
				break;
			case R.id.fragment_l_l_main_Recentplay:
				Message msg2 = new Message();
				msg2.what = MainActivity.FALG_JUMPTO_RECENTPLAY;
				MainActivity.handler.sendMessage(msg2);
				break;
			case R.id.fragment_l_l_main_MyDownLoad:
				Message msg3 = new Message();
				msg3.what = MainActivity.FALG_JUMPTO_DOWNLOADMUSIC;
				MainActivity.handler.sendMessage(msg3);
				break;

			default:
				break;
		}

	}

}