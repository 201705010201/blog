package com.cc.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 搜索博客管理列表
 */
@Data
@NoArgsConstructor
public class SearchBlog {

    private String title;
    private Long typeId;
   /* private boolean recommend;*/

}
