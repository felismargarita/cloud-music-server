package com.felis.cloudmusic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.felis.cloudmusic.entity.Song;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongMapper extends BaseMapper<Song> {
    IPage<Song> get(Page<Song> page, @Param("p") Song song);
    List<Song> get();
}
