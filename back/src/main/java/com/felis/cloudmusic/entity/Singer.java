package com.felis.cloudmusic.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("cloud_singer")
public class Singer {

    @TableId(type = IdType.AUTO)
    public int id;

    @TableField("name")
    private String name;

    @TableField("sex")
    private String sex;

    @TableField("picture_url")
    private String pictureUrl;

    @TableField(exist = false)
    private byte[] picture;

    @TableField("birthday")
    private Date birthday;

    @TableField("location")
    private String location;

    @TableField("introduction")
    private String introduction;

    @TableField("created_time")
    private Date createdTime;

    @TableField("updated_time")
    private Date updatedTime;

}
