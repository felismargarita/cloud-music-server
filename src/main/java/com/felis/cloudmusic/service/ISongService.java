package com.felis.cloudmusic.service;

import com.felis.cloudmusic.entity.Song;

public interface ISongService {
    boolean existSong(Song song);
    void addSong(Song song);
    void deleteSong(int id);
    void updateSong(Song song);
}
