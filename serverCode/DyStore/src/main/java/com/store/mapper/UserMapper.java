package com.store.mapper;

import com.store.beans.Users;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select * from users where username = #{username} and password = #{password}")
    Users SelectByUsernameAndPassword(Users users);

    @Select("select * from users where username = #{username} or phone = #{phone}")
    Users SelectByUsername(Users users);

    @Insert("INSERT INTO users (username,password,name,gender,phone,image,entry_date) VALUES (#{username},#{password},#{name},#{gender},#{phone},#{image},#{entryDate})")
    void signup(Users user);
}
