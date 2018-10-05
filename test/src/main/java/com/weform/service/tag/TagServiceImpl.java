package com.weform.service.tag;

import com.weform.mapper.TagMapper;
import com.weform.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: 简单DI年华
 * @Date: 18-10-5 09:14
 * @Description:
 */

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    TagMapper tagMapper;

    @Override
    public List<Tag> finAllTags() {

        List data = tagMapper.getAllTags();

        return data;
    }
}
