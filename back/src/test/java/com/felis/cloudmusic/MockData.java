package com.felis.cloudmusic;


import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.felis.cloudmusic.entity.Comment;
import com.felis.cloudmusic.entity.Song;
import com.felis.cloudmusic.entity.User;
import com.felis.cloudmusic.mapper.CommentMapper;
import com.felis.cloudmusic.mapper.SongMapper;
import com.felis.cloudmusic.mapper.UserMapper;
import com.felis.cloudmusic.utils.RandomChineseCharUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
public class MockData {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SongMapper songMapper;

    @Autowired
    private CommentMapper commentMapper;

//    @Test
    void mockUsers(){
        //生产一千个随机用户
        for(int m = 0;m<1000;m++){
            String username = UUID.randomUUID().toString();
            String nickname="";
            for(int i = 0;i<6;i++){
                nickname += RandomChineseCharUtil.get();
            }
            User user = new User();
            user.setUsername(username);
            user.setNickname(nickname);
            userMapper.insert(user);
        }
    }

    private String getRandomChineseChar(int cnt){
        StringBuilder contentBuilder= new StringBuilder();
        for(int i = 0 ;i<cnt;i++){
            contentBuilder.append(RandomChineseCharUtil.get());
        }
        return contentBuilder.toString();
    }

    @Test
    void mockComment(){
        List<Song> songList = songMapper.selectList(new QueryWrapper<>());
        List<Long> commentIdList = new ArrayList<>();
        for(Song song:songList){
            List<User> userList = userMapper.selectList(new QueryWrapper<>());
            Collections.shuffle(userList);
            List<User> randUsers = userList.subList(0,500);//随机取500个用户
            for(User user:randUsers){
                Comment comment = new Comment();
                comment.setSongId(song.getId());
                comment.setCreatedUserId(user.getId());
                comment.setContent(getRandomChineseChar(RandomUtil.randomInt(10,128)));
                comment.setCreatedTime(new Date());
                if(RandomUtil.randomInt(1,101)%100 ==0){
                    comment.setLike(RandomUtil.randomInt(1,100000));
                }
                if(RandomUtil.randomInt(1,101)%100 ==0 && commentIdList.size()>0){
                    int randomIndex = RandomUtil.randomInt(0,commentIdList.size() -1);
                    comment.setReferId(commentIdList.get(randomIndex));
                }
                commentMapper.insert(comment);
                commentIdList.add(comment.getId());
            }
        }

    }
}
