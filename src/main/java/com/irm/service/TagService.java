package com.irm.service;
/**
 * @author M.hj
 * @version 1.0
 * @date 2020/9/25 16:02
 */
import com.irm.po.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TagService {
    Tag saveTag(Tag tag);
    Tag getTag(Long id);
    Tag GetTagByName(String name);
    Page<Tag> listTag(Pageable pageable);
    List<Tag> listTag();
    List<Tag> listTag(String ids);
    List<Tag> listTagTop(Integer size);
    Tag updateTag(Long id, Tag tag);
    void  deleteTag(Long id);
}
