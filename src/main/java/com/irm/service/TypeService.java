package com.irm.service;
/**
 * @author M.hj
 * @version 1.0
 * @date 2020/9/25 16:02
 */
import com.irm.po.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TypeService {
    Type saveType(Type type);
    Type getType(Long id);
    Type GetTypeByName(String name);
    Page<Type> listType(Pageable pageable);
    List<Type> listType();
    List<Type> listTypeTop(Integer size);
    Type updateType(Long id,Type type);
    void  deleteType(Long id);
}
