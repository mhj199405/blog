package com.irm.dao;
/**
 * @author M.hj
 * @version 1.0
 * @date 2020/9/25 16:02
 */
import com.irm.po.Blog;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BlogRepositiory extends JpaRepository<Blog,Long>,JpaSpecificationExecutor<Blog> {
    @Query("select b from Blog b where b.recommend=true")
    List<Blog> findTop(Pageable pageable);
}
