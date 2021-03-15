package com.felis.cloudmusic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.felis.cloudmusic.entity.Comment;
import com.felis.cloudmusic.entity.PageParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface CommentMapper extends BaseMapper<Comment> {
    IPage<Comment> get(PageParam<Comment> pageParam, @Param("p") Comment comment);
}
