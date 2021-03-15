package com.felis.cloudmusic.service;

import com.felis.cloudmusic.entity.Comment;
import com.felis.cloudmusic.entity.PageParam;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

public interface ICommentService {

    IPage<Comment> getPaging(PageParam<Comment> pageParam);

    Comment getById(Long id);

    void add(Comment comment);
}
