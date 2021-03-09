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
@TableName("cloud_album")
public class Album {
    @TableId(type = IdType.AUTO)
    private int id;

    @TableField("name")
    private String name;

    @TableField("picture_url")
    private String pictureUrl;

    @TableField(exist = false)
    private byte[] picture;

    @TableField("delivery_time")
    private Date deliveryTime;

    @TableField("created_time")
    private Date createdTime;

    @TableField("updated_time")
    private Date updatedTime;

}
