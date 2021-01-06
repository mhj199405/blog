package com.irm.service;
/**
 * @author M.hj
 * @version 1.0
 * @date 2020/9/25 16:02
 */
import com.irm.ClassNotFoundException;
import com.irm.dao.TagRespository;
import com.irm.po.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagRespository tagRepository;
    @Transactional
    @Override
    public Tag saveTag(Tag tag) {
        return tagRepository.save(tag);
    }
    @Transactional
    @Override
    public Tag getTag(Long id) {
        return tagRepository.findById(id).get();
    }
    @Transactional
    @Override
    public Tag GetTagByName(String name) {
        Tag tag = tagRepository.findByName(name);
        return tag;
    }
    @Transactional
    @Override
    public Page<Tag> listTag(Pageable pageable) {
        return tagRepository.findAll(pageable);
    }

    @Override
    public List<Tag> listTag() {
        return tagRepository.findAll();
    }

    @Override
    public List<Tag> listTag(String ids) { //1,2,3
        return tagRepository.findAllById(convertToList(ids));
    }
    private  List<Long> convertToList(String ids){
        List<Long> list = new ArrayList<>();
        if(!"".equals(ids) && ids !=null){
            String[] idarry = ids.split(",");
            for (int i=0;i<idarry.length;i++){
                list.add(new Long(idarry[i]));
            }
        }
        return list;
    }

    @Override
    public List<Tag> listTagTop(Integer size) {
        Sort.Order sort=new Sort.Order(Sort.Direction.DESC,"blogs.size");
        PageRequest pageable =  PageRequest.of(0, size,Sort.by(sort));
        return tagRepository.findTop(pageable);
    }

    @Transactional
    @Override
    public Tag updateTag(Long id, Tag tag) {
        Tag t = tagRepository.findById(id).get();
        if(t==null){
            throw new ClassNotFoundException("不存在该类型");
        }
        BeanUtils.copyProperties(tag,t);

        return tagRepository.save(t);
    }
    @Transactional
    @Override
    public void deleteTag(Long id) {
      tagRepository.deleteById(id);
    }
}
