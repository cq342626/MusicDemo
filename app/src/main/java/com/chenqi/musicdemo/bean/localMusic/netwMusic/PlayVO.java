package com.chenqi.musicdemo.bean.localMusic.netwMusic;


/**
 * 网络歌曲所有信息
 */
public class PlayVO {

	private songinfo songinfo;
	private bitrate bitrate;

	public songinfo getSonginfo() {
		return songinfo;
	}

	public void setSonginfo(songinfo songinfo) {
		this.songinfo = songinfo;
	}

	public bitrate getBitrate() {
		return bitrate;
	}

	public void setBitrate(bitrate bitrate) {
		this.bitrate = bitrate;
	}

	@Override
	public String toString() {
		return "PlayVO [songinfo=" + songinfo + ", bitrate=" + bitrate + "]";
	}

}
