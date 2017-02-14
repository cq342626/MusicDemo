package com.chenqi.musicdemo.JarUtils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chenqi.musicdemo.R;

/**
 * 通过Glide下载图片
 */
public class GlideUtils {

	private Context context;

	public GlideUtils(Context context) {
		super();
		this.context = context;
	}

	/**
	 * Glide 获取图片 有默认图片及错误图片
	 *
	 * @param url
	 *            图片地址
	 * @param view
	 *            图片要显示的ImageView
	 */
	public void downLoadImage(String url, ImageView view) {
		Glide.with(context).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.pic_icon)
				.error(R.drawable.pic_icon_error).into(view);
	}

	/**
	 * Glide 下载图片 带默认图片
	 *
	 * @param url
	 *            下载地址
	 * @param view
	 *            显示的ImageView
	 */
	public void downLoadImageNomal(String url, ImageView view) {
		Glide.with(context).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.pic_icon)
				.error(R.drawable.pic_icon_error).into(view);
	}

	/**
	 * Glide 下载图片 带下载错误图片
	 *
	 * @param url
	 *            下载地址
	 * @param view
	 *            显示的ImageView
	 */
	public void downLoadImageError(String url, ImageView view) {
		Glide.with(context).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).error(R.drawable.pic_icon_error)
				.into(view);
	}
}