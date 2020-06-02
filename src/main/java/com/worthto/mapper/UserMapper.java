package com.worthto.mapper;

import com.worthto.bean.User;
import org.apache.ibatis.annotations.Select;

/**
 * @author gezz
 * @description
 * @date 2020/6/1.
 */
public interface UserMapper {

    @Select("SELECT * FROM user WHERE id = #{id}")
    User selectUser(Long id);
}
