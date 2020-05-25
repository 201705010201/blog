package com.cc.controller.admin;

import com.cc.pojo.Blog;
import com.cc.pojo.User;
import com.cc.service.BlogService;
import com.cc.service.TagService;
import com.cc.service.TypeService;
import com.cc.vo.SearchBlog;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;


    public void setTypeAndTag(Model model) {
        //往前台传数据
        model.addAttribute("types",typeService.getAllType());
        model.addAttribute("tags",tagService.getAllTag());
    }

    //后台展示博客列表
    @GetMapping("/blogs")
    public String blogs(@RequestParam(required = false,defaultValue = "1",value = "pagenum")int pagenum,
                        Model model) {
        PageHelper.startPage(pagenum,8);
        List<Blog> allBlog = blogService.getAllBlog();

        //得到分页结果
        PageInfo pageInfo = new PageInfo(allBlog);
        model.addAttribute("pageInfo",pageInfo);
        //查询类型和标签
        setTypeAndTag(model);
        return "admin/blogs";
    }

    //按条件查询博客
    @PostMapping("/blogs/search")
    public String searchBlogs(@RequestParam(required = false,defaultValue = "1") int pagenum,
                              //@RequestParam(required = false,defaultValue = "5") Integer pageSize,
                              SearchBlog blog,
                              Model model) {
        PageHelper.startPage(pagenum,100);
        List<Blog> allBlog = blogService.searchAllBlog(blog);
        //得到分页结果对象
        PageInfo pageInfo = new PageInfo(allBlog);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("message", "查询成功");
        setTypeAndTag(model);

        //System.out.println(pageInfo);

        return "admin/blogs :: blogList";
    }

    //去新增博客页面
    @GetMapping("/blogs/input")
    public String toAddBlog(Model model){
        model.addAttribute("blog", new Blog());  //返回一个blog对象给前端th:object
        setTypeAndTag(model);
        return "admin/blogs-input";
    }

    //去编辑博客页面
    @GetMapping("/blogs/{id}/input")
    public String toEditBlog(@PathVariable Long id, Model model){
        Blog blog = blogService.getBlog(id);
        //将tags集合转换为tagIds字符串
        blog.init();
        //返回一个blog对象给前端th:object
        model.addAttribute("blog", blog);
        setTypeAndTag(model);
        return "admin/blogs-input";
    }

    //新增、编辑博客
    @PostMapping("/blogs")
    public String addBlog(Blog blog, HttpSession session, RedirectAttributes attributes){
        //设置user属性
        blog.setUser((User) session.getAttribute("user"));
        //设置用户id
        blog.setUserId(blog.getUser().getId());
        //设置blog的type
        blog.setType(typeService.getType(blog.getType().getId()));
        //设置blog中typeId属性
        blog.setTypeId(blog.getType().getId());
        //给blog中的List<Tag>赋值
        blog.setTags(tagService.getTagByString(blog.getTagIds()));

        if (blog.getId() == null) {   //id为空，则为新增
            blogService.saveBlog(blog);
        } else {
            blogService.updateBlog(blog);
        }

        attributes.addFlashAttribute("msg", "新增成功");
        return "redirect:/admin/blogs";
    }

    @GetMapping("/blogs/{id}/delete")
    public String deleteBlogs(@PathVariable Long id, RedirectAttributes attributes){
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("msg", "删除成功");
        return "redirect:/admin/blogs";
    }


}
