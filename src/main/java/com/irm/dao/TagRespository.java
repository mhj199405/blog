package com.irm.dao;
/**
 * @author M.hj
 * @version 1.0
 * @date 2020/9/25 16:02
 */
import com.irm.po.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TagRespository  extends JpaRepository<Tag,Long> {
    Tag findByName(String name);
    @Query("select t from Tag t")
    List<Tag> findTop(Pageable pageable);
}
