package com.felis.cloudmusic.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.felis.cloudmusic.entity.PageParam;
import com.felis.cloudmusic.entity.Song;

import java.util.List;

public interface ISongService {
    IPage<Song> getPaging(PageParam<Song> pageParam);
    List<Song> getAll();
    boolean existSong(Song song);
    void addSong(Song song);
    void deleteSong(int id);
    void updateSong(Song song);
}
