package com.cc.controller;

import com.cc.pojo.Blog;
import com.cc.pojo.Comment;
import com.cc.pojo.Tag;
import com.cc.pojo.Type;
import com.cc.service.BlogService;
import com.cc.service.CommentService;
import com.cc.service.TagService;
import com.cc.service.TypeService;
import com.cc.vo.DetailedBlog;
import com.cc.vo.FirstPageBlog;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.annotation.Resource;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @Resource
    private CommentService commentService;

    @GetMapping("/")
    public String toIndex(@RequestParam(required = false, defaultValue = "1", value = "pageNum") int pageNum,
                          Model model){

        PageHelper.startPage(pageNum, 8);
        List<FirstPageBlog> allBlog = blogService.getIndexBlog();
        List<Type> allType = typeService.getBlogType();  //获取博客的类型(联表查询)
        List<Tag> allTag = tagService.getBlogTag();  //获取博客的标签(联表查询)
        List<Blog> recommendBlog =blogService.getAllRecommendBlog();  //获取推荐博客

        //得到分页结果对象
        PageInfo pageInfo = new PageInfo(allBlog);
       /* for (Blog blog: allBlog) {
            System.out.println(blog.getType().getName());
        }*/
        //System.out.println(pageInfo);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("tags", allTag);
        model.addAttribute("types", allType);
        model.addAttribute("recommendBlogs", recommendBlog);
        return "index";
    }

    //index搜索博客
    @PostMapping("/search")
    public String search(@RequestParam(required = false,defaultValue = "1",value = "pagenum")int pagenum,
                         @RequestParam String query, Model model){

        PageHelper.startPage(pagenum, 100);
        List<Blog> searchBlog = blogService.getSearchBlog(query);
        PageInfo pageInfo = new PageInfo(searchBlog);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("query", query);
        return "search";
    }

    //根据博客id查看博客详情
    @GetMapping("/blog/{id}")
    public String toLogin(@PathVariable Long id, Model model){
        DetailedBlog blog = blogService.getDetailedBlog(id);
        //Blog blog = blogService.getDetailedBlog(id);
        List<Comment> comments = commentService.listCommentByBlogId(id);
        model.addAttribute("comments", comments);
        model.addAttribute("blog", blog);
        return "blog";
    }
}
