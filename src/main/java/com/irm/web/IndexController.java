package com.irm.web;
/**
 * @author M.hj
 * @version 1.0
 * @date 2020/9/25 16:02
 */

import com.irm.service.BlogService;
import com.irm.service.TagService;
import com.irm.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.irm.ClassNotFoundException;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class IndexController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;
    @GetMapping("/")
    public String index(@PageableDefault(size = 8,sort = {"updateTime"},direction = Sort.Direction.DESC)Pageable pageable,
                        Model model){
      model.addAttribute("page",blogService.listBlog(pageable));
      model.addAttribute("types",typeService.listTypeTop(6));
      model.addAttribute("tags",tagService.listTagTop(10));
      model.addAttribute("recommendBlogs",blogService.listRecommendBlogTop(8));
//      model.addAttribute("tags",tagService.listTypeTop(6));
        //        int i=9/0;
//        String blog=null;
//        if(blog==null){
//            throw  new  ClassNotFoundException("博客不存在");
//        }
        return "index";
    }
    @GetMapping("/blog")
    public String blog(){

        return "blog";
    }
}
