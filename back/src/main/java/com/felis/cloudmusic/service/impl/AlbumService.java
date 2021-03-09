package com.felis.cloudmusic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.felis.cloudmusic.entity.Album;
import com.felis.cloudmusic.mapper.AlbumMapper;
import com.felis.cloudmusic.service.IAlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AlbumService implements IAlbumService {

    @Autowired
    private AlbumMapper albumMapper;


    public boolean existAlbum(Album album){
        QueryWrapper<Album> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",album.getName());
        Album a = albumMapper.selectOne(queryWrapper);
        return a  != null;
    }
    public void addAlbum(Album album){
        albumMapper.insert(album);
    }
    public void deleteAlbum(int id){
        albumMapper.deleteById(id);
    }
    public void updateAlbum(Album album){
        albumMapper.updateById(album);
    }
    public Album mergeAlbumByName(String name){
        QueryWrapper<Album> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",name);
        Album album = albumMapper.selectOne(queryWrapper);
        if(album == null){
            album = new Album();
            album.setName(name);
            albumMapper.insert(album);
            return album;
        }else{
            return album;
        }
    }
}
