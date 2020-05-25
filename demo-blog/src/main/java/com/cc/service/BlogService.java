package com.cc.service;

import com.cc.pojo.Blog;
import com.cc.vo.SearchBlog;
import com.cc.vo.DetailedBlog;
import com.cc.vo.FirstPageBlog;

import java.util.List;
import java.util.Map;

public interface BlogService {

    DetailedBlog getDetailedBlog(Long id);
    //Blog getDetailedBlog(Long id);


    //后台展示博客
    Blog getBlog(Long id);


    List<Blog> getAllBlog();


    //根据类型id获取博客
    List<FirstPageBlog> getByTypeId(Long typeId);

    //根据标签id获取博客
    List<Blog> getByTagId(Long tagId);
    //List<FirstPageBlog> getByTagId(Long tagId);

    //主页博客展示
    List<FirstPageBlog> getIndexBlog();



    //推荐博客展示
    List<Blog> getAllRecommendBlog();

    //全局搜索博客
    List<Blog> getSearchBlog(String query);

    //归档博客
    Map<String,List<Blog>> archiveBlog();

    //查询博客条数
    int countBlog();

    //新增博客
    int saveBlog(Blog blog);

    //修改博客
    int updateBlog(Blog blog);

    //删除博客
    int deleteBlog(Long id);

    //后台根据标题、分类、推荐搜索博客
    List<Blog> searchAllBlog(SearchBlog blog);
}
