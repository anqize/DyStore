package com.store.mapper;

import com.store.beans.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderMapper {
    @Select("select * from products where id = #{id}")
    Product FindByIds(Integer id);
}
