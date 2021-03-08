package com.felis.cloudmusic.service;

import com.felis.cloudmusic.entity.Album;

public interface IAlbumService {
    boolean existAlbum(Album album);
    void addAlbum(Album album);
    void deleteAlbum(int id);
    void updateAlbum(Album album);
    Album mergeAlbumByName(String name);
}
