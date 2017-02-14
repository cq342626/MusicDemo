package com.chenqi.musicdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;

import com.chenqi.musicdemo.R;
import com.chenqi.musicdemo.bean.localMusic.Music;
import com.chenqi.musicdemo.command.MaterialRippleLayout;
import com.chenqi.musicdemo.common.Util;

public class MusicLocalAdapter extends RecyclerView.Adapter<MusicLocalAdapter.ViewHolder> {

	public Context context;
	public OnItemClick click;
	
	public void setClick(OnItemClick click){
		this.click = click;
	}

	public class ViewHolder extends RecyclerView.ViewHolder implements OnClickListener {
		public TextView text_song;
		public TextView text_songer;
		public TextView text_more;

		public ViewHolder(View v) {
			super(v);
			text_song = (TextView) v.findViewById(R.id.main_item_song_name);
			text_songer = (TextView) v.findViewById(R.id.main_item_songer_name);
			text_more = (TextView) v.findViewById(R.id.main_item_more);
			v.setOnClickListener(this);
		}

		@Override
		public void onClick(View v) {
			if (click != null) {
				click.onItemClick(getAdapterPosition());
			}
		}
	}

	@Override
	public int getItemCount() {
		if (Util.ALL_MUSIC_LIST.size() != 0) {
			return Util.ALL_MUSIC_LIST.size();
		}
		return 0;
	}
	
	@Override
	public void onBindViewHolder(ViewHolder holder, int positioin) {
		Music music = Util.ALL_MUSIC_LIST.get(positioin);
		holder.text_song.setText(music.getTitle());
		holder.text_songer.setText(music.getAuthor());
	}
	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int position) {

		this.context = parent.getContext();

		View view = LayoutInflater.from(context).inflate(R.layout.fragment_local_main_item, parent, false);

		FrameLayout frameLayout = MaterialRippleLayout.on(view).rippleOverlay(true).rippleAlpha(0.3f)
				.rippleColor(0xFF4886f3).rippleHover(true).create();

		LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		
//		lp.leftMargin = 5;
		lp.topMargin = 1;
//		lp.rightMargin = 5;
		lp.bottomMargin = 1;
		
		frameLayout.setLayoutParams(lp);
		frameLayout.setClickable(true);
		
		return new ViewHolder(frameLayout);
	}

	public interface OnItemClick {
		void onItemClick(int position);
	}

	
}
