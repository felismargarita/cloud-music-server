package com.felis.cloudmusic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.felis.cloudmusic.entity.Song;
import com.felis.cloudmusic.mapper.SongMapper;
import com.felis.cloudmusic.service.ISongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SongService implements ISongService {

    @Autowired
    private SongMapper songMapper;

    public boolean existSong(Song song){
        QueryWrapper<Song> queryWrapper = new QueryWrapper();
        queryWrapper.eq("name",song.getName());
        Song s = songMapper.selectOne(queryWrapper);
        return s!=null;
    }

    public void addSong(Song song){
        songMapper.insert(song);
    }
    public void deleteSong(int id){
        songMapper.deleteById(id);
    }
    public void updateSong(Song song){
        songMapper.updateById(song);
    }
}
