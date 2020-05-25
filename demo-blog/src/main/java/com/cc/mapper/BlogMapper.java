package com.cc.mapper;

import com.cc.pojo.Blog;
import com.cc.pojo.BlogAndTag;
import com.cc.vo.SearchBlog;
import com.cc.vo.DetailedBlog;
import com.cc.vo.FirstPageBlog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BlogMapper {

    //根据博客id增加view量
    int addView(@Param("id") Long id);

    //根据博客id查出评论数
    int getCommentCountById(Long id);



    //后台展示博客
    Blog getBlog(Long id);

    //后台根据标题、分类、推荐搜索博客
    //List<Blog> searchAllBlog(BlogQuery blog);
    List<Blog> searchAllBlog(SearchBlog blog);




    //博客详情
    DetailedBlog getDetailedBlog(@Param("id") Long id);
    //Blog getDetailedBlog(@Param("id") Long id);

    //所有博客
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



    //查询所有年份，返回一个集合
    List<String> findGroupYear();

    //按年份查询博客
    List<Blog> findByYear(@Param("year") String year);

    //新增博客
    int saveBlog(Blog blog);

    //新增博客和标签
    int saveBlogAndTag(BlogAndTag blogAndTag);

    //更新博客
    int updateBlog(Blog blog);

    //删除博客
    int deleteBlog(Long id);

}
