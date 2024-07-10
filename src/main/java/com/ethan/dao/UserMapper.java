package com.ethan.dao;

import com.ethan.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Johnson
 */
@Mapper
public interface UserMapper {

    @Insert("INSERT INTO user (name, age) values(#{name}, #{age})")
    void insert(User user);
}
