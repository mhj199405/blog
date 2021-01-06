package com.irm.service;
/**
 * @author M.hj
 * @version 1.0
 * @date 2020/9/25 16:02
 */
import com.irm.ClassNotFoundException;
import com.irm.dao.BlogRepositiory;
import com.irm.po.Blog;
import com.irm.po.Type;
import com.irm.util.MyBeanUtils;
import com.irm.vo.BlogQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BlogServiceImpl implements  BlogService {
    @Autowired
    private BlogRepositiory blogRepositiory;
    @Override
    public Blog getBlog(Long id) {
        return blogRepositiory.findById(id).get();
    }
    // 这是进行模糊查询的方法
    @Override
    public Page<Blog> listBlog(Pageable pageable,BlogQuery blog) {
        return blogRepositiory.findAll(new Specification<Blog>() {
            /**Root<Blog>代表的你要查询的对象是哪个
             * CriteriaQuery查询条件的容器，可以把一些条件放到这个里面
             * CriteriaBuilder设置具体某一个条件的表达式(相等 模糊查询等条件表达式)
             */
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                List<Predicate> predicates=new ArrayList<>();
                // 这条判断语句以标题为条件进行模糊查询的
                if(!"".equals(blog.getTitle()) && blog.getTitle()!=null){
                   predicates.add(cb.like(root.<String>get("title"),"%"+blog.getTitle()+"%"));
                }
                // 这条判断语句是以分类的名称进行模糊查询的
                if(blog.getTypeId()!=null){
                   predicates.add(cb.equal(root.<Type>get("type").get("id"),blog.getTypeId()));
                }
                // 这条判断语句是以是否推荐为条件进行模糊查询的
                if(blog.isRecommend()){
                    predicates.add(cb.<Boolean>equal(root.get("recommend"),blog.isRecommend()));
                }
                cq.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }
        },pageable);
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable) {
        return blogRepositiory.findAll(pageable);
    }
    @Transactional
    @Override
    public Blog saveBlog(Blog blog) {
       if(blog.getId() == null){
           blog.setCreateTime(new Date());
           blog.setUpdateTime(new Date());
           blog.setViews(0);
       }else {
           blog.setUpdateTime(new Date());
       }
        return blogRepositiory.save(blog);
    }

    @Override
    public List<Blog> listRecommendBlogTop(Integer size) {
        Sort.Order sort=new Sort.Order(Sort.Direction.DESC,"updateTime");
        PageRequest pageable=PageRequest.of(0,size,Sort.by(sort));
        return blogRepositiory.findTop(pageable);
    }

    @Transactional
    @Override
    public Blog updateBlog(Long id, Blog blog) {
        Blog b = blogRepositiory.findById(id).get();
        if(b == null){
            throw new ClassNotFoundException("该博客不存在");
        }
        BeanUtils.copyProperties(blog,b, MyBeanUtils.getNullPropertyNames(blog));
        b.setUpdateTime(new Date());
        return  blogRepositiory.save(b);
    }
    @Transactional
    @Override
    public void deleteBlog(Long id) {
     blogRepositiory.deleteById(id);
    }
}
