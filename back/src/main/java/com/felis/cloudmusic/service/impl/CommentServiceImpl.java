package com.felis.cloudmusic.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.felis.cloudmusic.entity.Comment;
import com.felis.cloudmusic.entity.PageParam;
import com.felis.cloudmusic.mapper.CommentMapper;
import com.felis.cloudmusic.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements ICommentService {

    @Autowired
    private CommentMapper commentMapper;

    public IPage<Comment> getPaging(PageParam<Comment> pageParam){
        IPage<Comment> commentIPage = commentMapper.get(pageParam,pageParam.getParams());
        List<Comment> commentList = commentIPage.getRecords();
        //获取下参考评论
        for(Comment comment : commentList){
            if(comment.getReferId() != null){
                Comment referComment = commentMapper.selectById(comment.getReferId());
                comment.setReferComment(referComment);
            }
        }
        return commentIPage;
    }

    public Comment getById(Long id){
        return commentMapper.selectById(id);
    }

    public void add(Comment comment){
        commentMapper.insert(comment);
    }
}
