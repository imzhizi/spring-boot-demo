package com.imzhizi.tdd.repository;

import com.imzhizi.tdd.domain.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * created by zhizi
 * on 5/31/20 16:43
 */
@Mapper
public interface UserRepo {
    @Select("select * from user where username = #{username}")
    User findByUsername(String username);

    @Select("select * from user where id = #{id}")
    User findById(long id);

    @Insert("insert into user(username,pwd) values (#{username},#{pwd})")
    int insertOne(User user);
}
