package com.chenqi.musicdemo.bean.localMusic.netwMusic;

/**
 * 网络音乐的歌曲信息
 */
public class songinfo {

	private String song_id; // 歌曲id
	private String author; // 歌手
	private String title;// 歌名
	private String lrclink;// 歌词
	private String album_id;// 专辑ID
	private String album_title;// 专辑名称
	private String pic_small;// 小图
	private String pic_big;// 大图

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

	@Override
	public String toString() {
		return "songinfo [song_id=" + song_id + ", author=" + author + ", title=" + title + ", lrclink=" + lrclink
				+ ", album_id=" + album_id + ", album_title=" + album_title + ", pic_small=" + pic_small + ", pic_big="
				+ pic_big + "]";
	}

}
