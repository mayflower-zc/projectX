package com.smarthome.message.resp;

/**
 * 音乐消息
 * Created by zhengcong on 14-4-19.
 */
public class MusicMessage extends BaseMessage {
    //音乐
    private Music Music;

    public Music getMusic() {
        return Music;
    }

    public void setMusic(Music music) {
        this.Music = music;
    }
}
