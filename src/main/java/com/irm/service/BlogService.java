package com.irm.service;
/**
 * @author M.hj
 * @version 1.0
 * @date 2020/9/25 16:02
 */
import com.irm.po.Blog;
import com.irm.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BlogService {
    Blog getBlog(Long id);
    Page<Blog> listBlog(Pageable pageable,BlogQuery blog);
    Page<Blog> listBlog(Pageable pageable);
    List<Blog>  listRecommendBlogTop(Integer size);
    Blog saveBlog(Blog blog);
    Blog updateBlog(Long id,Blog blog);
    void deleteBlog(Long id);
}
