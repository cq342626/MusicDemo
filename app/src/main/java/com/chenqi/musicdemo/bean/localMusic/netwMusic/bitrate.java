package com.chenqi.musicdemo.bean.localMusic.netwMusic;

/**
 * 歌曲地址
 */

public class bitrate {

	private String file_extension; // 歌曲类型
	private String show_link; // 歌曲专门试听地址
	private String file_link; // 歌曲试听地址
	private String file_size;// 歌曲大小
	private String file_duration;// 歌曲时长


	public String getFile_extension() {
		return file_extension;
	}

	public void setFile_extension(String file_extension) {
		this.file_extension = file_extension;
	}

	public String getShow_link() {
		return show_link;
	}

	public void setShow_link(String show_link) {
		this.show_link = show_link;
	}

	public String getFile_link() {
		return file_link;
	}

	public void setFile_link(String file_link) {
		this.file_link = file_link;
	}

	public String getFile_size() {
		return file_size;
	}

	public void setFile_size(String file_size) {
		this.file_size = file_size;
	}

	public String getFile_duration() {
		return file_duration;
	}

	public void setFile_duration(String file_duration) {
		this.file_duration = file_duration;
	}

	@Override
	public String toString() {
		return "bitrate [file_extension=" + file_extension + ", show_link=" + show_link + ", file_link=" + file_link
				+ ", file_size=" + file_size + ", file_duration=" + file_duration + "]";
	}

}
