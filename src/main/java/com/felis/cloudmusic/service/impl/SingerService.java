package com.felis.cloudmusic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.felis.cloudmusic.entity.Singer;
import com.felis.cloudmusic.mapper.SingerMapper;
import com.felis.cloudmusic.service.ISingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SingerService implements ISingerService {

    @Autowired
    private SingerMapper singerMapper;

    public boolean existSinger(Singer singer){
        QueryWrapper<Singer> queryWrapper = new QueryWrapper<>();
        Singer s = singerMapper.selectOne(queryWrapper);
        return s !=null;
    }

    public void addSinger(Singer singer){
        singerMapper.insert(singer);
    }
    public void deleteSinger(int id){
        singerMapper.deleteById(id);
    }
    public void updateSinger(Singer singer){
        singerMapper.updateById(singer);
    }
    public Singer mergeSingerByName(String name){
        QueryWrapper<Singer> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",name);
        Singer singer = singerMapper.selectOne(queryWrapper);
        if(singer == null){
            singer = new Singer();
            singer.setName(name);
            singerMapper.insert(singer);
            return singer;
        }else{
            return singer;
        }
    }
}
