package com.chenqi.musicdemo.bean.localMusic;

import java.io.Serializable;

/**
 * 音乐的实体类（包括本地和网络）
 *
 * @author Administrator
 *
 */
public class Music implements Serializable {

	private String song_id; // 歌曲id
	private String title;// 歌名
	private String author; // 歌手
	private String lrclink;// 歌词
	private String album_id;// 专辑ID
	private String album_title;// 专辑名称
	private String pic_small;// 小图
	private String pic_big;// 大图
	private String song_path;// 歌曲路径
	private String file_extension; // 歌曲类型
	private long duration;// 歌曲的总播放时长
	private long size;// 歌曲文件的大小
	private String type;// 1、本地音乐；2、排行榜的音乐；3、歌单上的音乐

	private int listId;// 本地音乐的下标
	private int isPlay = 0;// 是否是正在播放

	public String getSong_id() {
		return song_id;
	}

	public void setSong_id(String song_id) {
		this.song_id = song_id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLrclink() {
		return lrclink;
	}

	public void setLrclink(String lrclink) {
		this.lrclink = lrclink;
	}

	public String getAlbum_id() {
		return album_id;
	}

	public void setAlbum_id(String album_id) {
		this.album_id = album_id;
	}

	public String getAlbum_title() {
		return album_title;
	}

	public void setAlbum_title(String album_title) {
		this.album_title = album_title;
	}

	public String getPic_small() {
		return pic_small;
	}

	public void setPic_small(String pic_small) {
		this.pic_small = pic_small;
	}

	public String getPic_big() {
		return pic_big;
	}

	public void setPic_big(String pic_big) {
		this.pic_big = pic_big;
	}

	public String getSong_path() {
		return song_path;
	}

	public void setSong_path(String song_path) {
		this.song_path = song_path;
	}

	public String getFile_extension() {
		return file_extension;
	}

	public void setFile_extension(String file_extension) {
		this.file_extension = file_extension;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getListId() {
		return listId;
	}

	public void setListId(int listId) {
		this.listId = listId;
	}

	public int getIsPlay() {
		return isPlay;
	}

	public void setIsPlay(int isPlay) {
		this.isPlay = isPlay;
	}

	@Override
	public String toString() {
		return "Music [song_id=" + song_id + ", author=" + author + ", title=" + title + ", lrclink=" + lrclink
				+ ", album_id=" + album_id + ", album_title=" + album_title + ", pic_small=" + pic_small + ", pic_big="
				+ pic_big + ", song_path=" + song_path + ", file_extension=" + file_extension + ", duration=" + duration
				+ ", size=" + size + ", type=" + type + ", listId=" + listId + ", isPlay=" + isPlay + "]";
	}
}
