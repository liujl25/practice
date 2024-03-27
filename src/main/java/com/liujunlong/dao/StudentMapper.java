package com.liujunlong.dao;

import com.liujunlong.pojo.Student;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *
 * @author liujunlong
 * @date 2023/4/29 13:26
 */
@Mapper
public interface StudentMapper {
    int insert(Student student);
}
