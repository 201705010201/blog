package com.cc.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Description: 博客首页数据实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FirstPageBlog {

    //Blog
    private Long id;
    private String title;
    private String firstPicture;
    private Integer views;
    private Integer commentCount;
    private Date updateTime;
    private String description;

    //Type
    private String typeName;

    //Tag
    private String tagName;

    //User
    private String nickname;
    private String avatar;
}