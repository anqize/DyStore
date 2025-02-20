package com.store.mapper;

import com.store.beans.Product;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CartMapper {
    @Select("select * from cart")
    List<Product> FindAllInCart();

    void deleteByIds(List<Integer> ids);

    @Insert("insert into cart(name, description, price, create_time, update_time, image) values (#{name}, #{description}, #{price}, #{createTime}, #{updateTime}, #{image})")
    void Add(Product product);


}
