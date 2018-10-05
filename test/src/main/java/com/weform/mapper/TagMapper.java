package com.weform.mapper;

import com.weform.model.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Auther: 简单DI年华
 * @Date: 18-10-5 09:08
 * @Description: 标签操作DAO
 */

@Mapper
@Component(value = "tagMapper")
public interface TagMapper {

    //查找所有
    @Select("SELECT * FROM tags")
    List<Tag> getAllTags();

    //数目加一
    @Update("UPDATE tags SET num = num +1 WHERE name = #{name};")
    void updateTagsNum(String name);


}
