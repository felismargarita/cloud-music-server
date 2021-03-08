package com.felis.cloudmusic.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("cloud_song")
public class Song {
    @TableId(type = IdType.AUTO)
    private int id;

    @TableField(exist = false)
    private String singer; //歌手名称

    @TableField("singer_id")
    private int singerId; //歌手Id

    @TableField("name")
    private String name; //歌曲名称

    @TableField("song_url")
    private String songUrl; //歌曲URL

    @TableField("duration")
    private int duration;

    @TableField(exist = false)
    private byte[] song;//歌曲

    @TableField("introduction")
    private String introduction; //介绍

    @TableField("picture_url")
    private String pictureUrl; //封面URL

    @TableField(exist = false)
    private byte[] picture; //封面

    @TableField("lyric")
    private String lyric; //歌词

    @TableField(exist = false)
    private String album; //专辑

    @TableField("album_id")
    private int albumId; //专辑ID

    @TableField("delivery_time")
    private Date deliveryTime;//发行时间

    @TableField("created_time")
    private Date createdTime;

    @TableField("updated_time")
    private Date updatedTime;

}
