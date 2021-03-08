package com.felis.cloudmusic.utils;

import com.felis.cloudmusic.entity.Song;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.id3.AbstractID3v2Frame;
import org.jaudiotagger.tag.id3.AbstractID3v2Tag;
import org.jaudiotagger.tag.id3.ID3v23Frame;
import org.jaudiotagger.tag.id3.framebody.FrameBodyAPIC;

import java.io.File;

public class SongUtil {
    private static final String SONG_NAME_KEY = "TIT2";
    private static final String ARTIST_KEY = "TPE1";
    private static final String ALBUM_KEY = "TALB";

    /**
     * 通过歌曲文件地址, 获取歌曲信息
     *
     * @param file 歌曲文件
     * @return 歌曲信息
     * @throws Exception 可能抛出空指针异常
     */
    public static Song getMusicInfo(File file) {
        Song song = null;
        try {
            MP3File mp3File = (MP3File) AudioFileIO.read(file);
            MP3AudioHeader audioHeader = (MP3AudioHeader) mp3File.getAudioHeader();
            String name = getInfoFromFrameMap(mp3File, SONG_NAME_KEY);//歌曲名称
            String singer = getInfoFromFrameMap(mp3File, ARTIST_KEY);//歌手名称
            String album = getInfoFromFrameMap(mp3File, ALBUM_KEY);//歌曲专辑
            int duration = audioHeader.getTrackLength();// 播放时长
            AbstractID3v2Tag tag = mp3File.getID3v2Tag();
            AbstractID3v2Frame frame = (AbstractID3v2Frame) tag.getFrame("APIC");
            FrameBodyAPIC image = (FrameBodyAPIC) frame.getBody();//封面
            song = new Song();
            song.setName(name);
            song.setSinger(singer);
            song.setAlbum(album);
            song.setDuration(duration);
            song.setPicture(image.getImageData());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("文件读取失败！" + e);
        }
        return song;
    }

    public static Song getMusicInfo(String filePath) {
        return getMusicInfo(new File(filePath));
    }
    /**
     * 通过键值,获取歌曲中对应的字段信息
     *
     * @param mp3File mp3音乐文件
     * @param key     键值
     * @return 歌曲信息
     * @throws Exception 可能抛出空指针异常
     */
    private static String getInfoFromFrameMap(MP3File mp3File, String key) throws Exception {
        ID3v23Frame frame = (ID3v23Frame) mp3File.getID3v2Tag().frameMap.get(key);
        return frame.getContent();
    }
}
