package com.felis.cloudmusic.service;

import com.felis.cloudmusic.entity.Singer;

public interface ISingerService {
    boolean existSinger(Singer singer);
    void addSinger(Singer singer);
    void deleteSinger(int id);
    void updateSinger(Singer singer);
    Singer mergeSingerByName(String name);
}
