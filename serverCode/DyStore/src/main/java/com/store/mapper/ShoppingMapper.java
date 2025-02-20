package com.store.mapper;

import com.store.beans.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ShoppingMapper {
    @Select("select * from products limit #{head}, #{size}")
    List<Product> FindAll(Integer head, Integer size);

    @Select("select count(*) from products")
    Integer Find();

    @Select("select * from products where id = #{id}")
    Product findById(Integer id);
}
