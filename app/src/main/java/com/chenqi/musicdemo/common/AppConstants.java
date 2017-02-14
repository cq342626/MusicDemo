package com.chenqi.musicdemo.common;

public class AppConstants {

    public static String APP_NAME = "音乐山河";

    // 所有音乐接口的头部
    public static String HTTP_URL = "http://tingapi.ting.baidu.com/v1/restserver/ting?format=json&calback=&from=webapp_music&method=";
    // 榜单接口type =
    // 1-新歌榜,2-热歌榜,11-摇滚榜,12-爵士,16-流行,21-欧美金曲榜,22-经典老歌榜,23-情歌对唱榜,24-影视金曲榜,25-网络歌曲榜
    // 榜单歌曲排行接口
    public static String BAIDU_MusicLRanking = "";
    public static void setBAIDU_MusicLRanking(String type) {
        BAIDU_MusicLRanking = "baidu.ting.billboard.billList&type=" + type + "&size=100&offset=";
    }

    // 获取播放音乐数据的接口
    public static String BAIDU_MUSIC_PLAY = "";
    public static void setBAIDU_MUSIC_PLAY(String song_id) {
        BAIDU_MUSIC_PLAY = "baidu.ting.song.play&songid=" + song_id;
    }

    /**
     * 轮播音乐封面
     *
     * @param num 数量
     */
    public static String focusPic(int num) {
        StringBuffer sb = new StringBuffer(HTTP_URL);
        sb.append("baidu.ting.plaza.getFocusPic")
                .append("&num=").append(num);
        return sb.toString();
    }

    /**
     * 唱片专辑
     *
     * @author Sanron
     */
    public static class Album {

        /**
         * 推荐唱片
         *
         * @param offset 偏移量
         * @param limmit 获取数量
         * @return
         */
        public static String recommendAlbum(int offset, int limmit) {
            StringBuffer sb = new StringBuffer(HTTP_URL);
            sb.append("&method=").append("baidu.ting.plaza.getRecommendAlbum")
                    .append("&offset=").append(offset)
                    .append("&limit=").append(limmit);
            return sb.toString();
        }

        /**
         * 唱片信息
         *
         * @param albumid 唱片id
         * @return
         */
        public static String albumInfo(String albumid) {
            StringBuffer sb = new StringBuffer(HTTP_URL);
            sb.append("&method=").append("baidu.ting.album.getAlbumInfo")
                    .append("&album_id=").append(albumid);
            return sb.toString();
        }
    }
}
