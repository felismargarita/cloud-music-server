package com.felis.cloudmusic.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.felis.cloudmusic.validation.custom.StringLength;
import com.felis.cloudmusic.validation.group.Add;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("cloud_comment")
public class Comment {

    @TableId(type= IdType.AUTO)
    private Long id;

    @TableField("song_id")
    @NotNull(message = "歌曲id不能为空",groups = {Add.class})
    private Long songId;

    @TableField(exist = false)
    private String songName;

    @TableField("content")
    @StringLength(min = 1,max= 128,message = "评论长度不符合要求")
    private String content;

    @TableField("created_user_id")
    @NotNull(message = "缺少用户ID")
    private Long createdUserId;

    @TableField(exist = false)
    private String createdUsername;
    @TableField(exist = false)
    private String createdNickname;

    @TableField("created_time")
    private Date createdTime;

    @TableField("user_like")
    private Integer like;

    @TableField("refer_id")
    private Long referId;

    @TableField(exist = false)
    private Comment referComment;
}
