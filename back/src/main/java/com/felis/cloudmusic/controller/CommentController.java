package com.felis.cloudmusic.controller;

import com.felis.cloudmusic.entity.Comment;
import com.felis.cloudmusic.entity.PageParam;
import com.felis.cloudmusic.entity.ResResult;
import com.felis.cloudmusic.service.ICommentService;
import com.felis.cloudmusic.utils.CommonResUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private ICommentService commentService;

    @PostMapping("/paging")
    public ResResult getPaging(@RequestBody PageParam<Comment> pageParam){
        return CommonResUtil.success(commentService.getPaging(pageParam));
    }

    @GetMapping("/getById")
    public ResResult getById(Long id){
        return CommonResUtil.success(commentService.getById(id));
    }

    @PostMapping
    public ResResult add(@RequestBody Comment comment){
        commentService.add(comment);
        return CommonResUtil.success();
    }


}
