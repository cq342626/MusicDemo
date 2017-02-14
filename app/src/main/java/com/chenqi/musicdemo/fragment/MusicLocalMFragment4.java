package com.chenqi.musicdemo.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chenqi.musicdemo.MainActivity;
import com.chenqi.musicdemo.R;
import com.chenqi.musicdemo.adapter.MusicLocalAdapter;
import com.chenqi.musicdemo.adapter.MusicLocalAdapter.OnItemClick;
import com.chenqi.musicdemo.command.RecycleView.RecyclerViewDivider;
import com.chenqi.musicdemo.common.Util;

/**
 * 本地音乐播放
 */
public class MusicLocalMFragment4 extends Fragment implements OnItemClick {

	private RecyclerView mRecyclerView;//本地音乐的列表
	private MusicLocalAdapter adapter;//适配器
	private Context context;
	
	private static MusicLocalMFragment4 localFragment = new MusicLocalMFragment4();
	
	
	public static Fragment newInstance() {
		if(localFragment == null)
			localFragment = new MusicLocalMFragment4();
		return localFragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_localm_mian, container, false);
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		initView(view);
	}

	
	public void initView(View view){
		
		context = getActivity();
		
		mRecyclerView = (RecyclerView) view.findViewById(R.id.main_listview);
		LinearLayoutManager mLayoutManager = new LinearLayoutManager(context);
		mRecyclerView.setLayoutManager(mLayoutManager);
		mRecyclerView.addItemDecoration(new RecyclerViewDivider(getActivity(), LinearLayoutManager.HORIZONTAL));
		adapter = new MusicLocalAdapter();
		adapter.setClick(this);
		mRecyclerView.setAdapter(adapter);
		
	}
	

	@Override
	public void onItemClick(int position) {
		Message msg = new Message();
		msg.what = MainActivity.FALG_PLAYMUSIC_START;
		msg.obj = Util.ALL_MUSIC_LIST.get(position).getListId();
		MainActivity.handler.sendMessage(msg);
	}
}
