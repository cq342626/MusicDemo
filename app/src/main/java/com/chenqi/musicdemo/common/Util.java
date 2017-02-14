package com.chenqi.musicdemo.common;

import com.chenqi.musicdemo.bean.localMusic.Music;

import java.util.LinkedList;


public class Util {

	public static LinkedList<Music> ALL_MUSIC_LIST = new LinkedList<Music>();

	public static boolean isLocalMusic = true;//true:播放本地音乐；false:播放网络音乐

	//将文件大小转化为当前使用的大小格式
	public static String convertFileSize(long size) {

		if (size < 1024) {
			return size + " B";
		} else if (size >= 1024 && size < 1024 * 1024) {

			return Math.round(((double) size / 1024.0) * 100) / 100.00 + " KB";

		} else if (size >= 1024 * 1024 && size < 1024 * 1024 * 1024) {

			return Math.round(((double) size / (1024.0 * 1024.0)) * 100)
					/ 100.00 + " MB";

		} else {
			return Math
					.round(((double) size / (1024.0 * 1024.0 * 1024.0)) * 100)
					/ 100.00 + " GB";
		}

	}

	//将时间处理成秒和分钟
	public static String formatTime(long time) {
		time /= 1000;
		long minute = time / 60;
		long second = time % 60;
		return String.format("%02d:%02d", minute, second);
	}


}
