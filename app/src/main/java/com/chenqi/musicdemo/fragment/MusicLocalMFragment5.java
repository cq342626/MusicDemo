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
 * �������ֲ���
 */
public class MusicLocalMFragment5 extends Fragment implements OnItemClick {
	
	private RecyclerView mRecyclerView;//�������ֵ��б�
	private MusicLocalAdapter adapter;//������
	private Context context;
	
	private static MusicLocalMFragment5 localFragment = new MusicLocalMFragment5();
	
	
	public static Fragment newInstance() {
		if(localFragment == null)
			localFragment = new MusicLocalMFragment5();
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
