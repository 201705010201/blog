package com.cc.service.impl;

import com.cc.exception.NotFoundException;
import com.cc.mapper.BlogMapper;
import com.cc.pojo.Blog;
import com.cc.pojo.BlogAndTag;
import com.cc.pojo.Tag;
import com.cc.service.BlogService;
import com.cc.util.MarkdownUtils;
import com.cc.vo.SearchBlog;
import com.cc.vo.DetailedBlog;
import com.cc.vo.FirstPageBlog;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class BlogServiceImpl implements BlogService {

    @Resource
    private BlogMapper blogMapper;

    @Override
    public DetailedBlog getDetailedBlog(Long id) {
        System.out.println(id);
        DetailedBlog detailedBlog = blogMapper.getDetailedBlog(id);
        //Blog detailedBlog = blogMapper.getDetailedBlog(id);
        if (detailedBlog == null) {
            throw new NotFoundException("该博客不存在");
        }
        String content = detailedBlog.getContent();
        detailedBlog.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
        //文章访问数量自增
        blogMapper.addView(id);
        //文章评论数量更新
        blogMapper.getCommentCountById(id);
        return detailedBlog;
    }




    //后台展示博客
    @Override
    public Blog getBlog(Long id) {
        return blogMapper.getBlog(id);
    }

    @Override
    public List<Blog> getAllBlog() {
        return blogMapper.getAllBlog();
    }

    @Override
    public List<FirstPageBlog> getByTypeId(Long typeId) {
        return blogMapper.getByTypeId(typeId);
    }


    @Override
    public List<Blog> getByTagId(Long tagId) {
        return blogMapper.getByTagId(tagId);
    }


    @Override
    public List<FirstPageBlog> getIndexBlog() {
        return blogMapper.getIndexBlog();
    }

    @Override
    public List<Blog> getAllRecommendBlog() {
        return blogMapper.getAllRecommendBlog();
    }

    @Override
    public List<Blog> getSearchBlog(String query) {
        return blogMapper.getSearchBlog(query);
    }

    @Override
    public Map<String, List<Blog>> archiveBlog() {
        List<String> years = blogMapper.findGroupYear();
        //去掉重复的年份
        HashSet<String> set = new HashSet<>(years);
        HashMap<String , List<Blog>> map = new HashMap<>();
        for (String year: set) {
            map.put(year,blogMapper.findByYear(year));
        }
        return map;
    }

    @Override
    public int countBlog() {
        return blogMapper.getAllBlog().size();
    }

    //新增博客
    @Override
    public int saveBlog(Blog blog) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);
        //保存博客
        blogMapper.saveBlog(blog);

        //保存博客后才能获取自增的id
        Long id = blog.getId();

        //将博客标签的数据存到t_blogs_tag表中
        List<Tag> tags = blog.getTags();
        BlogAndTag blogAndTag = null;
        for(Tag tag: tags) {
            //新增时无法获取自增的id，在mybatis中修改
            BlogAndTag blogAndTag1 = new BlogAndTag(tag.getId(),id);
        }
        return 1;
    }

    //修改博客
    @Override
    public int updateBlog(Blog blog) {
        blog.setUpdateTime(new Date());
        //将标签的数据存到t_blogs_tag表中
        List<Tag> tags = blog.getTags();
        BlogAndTag blogAndTag = null;
        for (Tag tag : tags) {
            blogAndTag = new BlogAndTag(tag.getId(), blog.getId());
            blogMapper.saveBlogAndTag(blogAndTag);
        }
        return blogMapper.updateBlog(blog);
    }

    @Override
    public int deleteBlog(Long id) {
        return blogMapper.deleteBlog(id);
    }

    @Override
    public List<Blog> searchAllBlog(SearchBlog blog) {
        return blogMapper.searchAllBlog(blog);
    }
}
