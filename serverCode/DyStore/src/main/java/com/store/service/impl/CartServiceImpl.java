package com.store.service.impl;

import com.store.beans.Product;
import com.store.mapper.CartMapper;
import com.store.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartMapper cm;

    @Override
    public List<Product> FindAll() {
        return cm.FindAllInCart();
    }

    @Override
    public void deleteByIds(List<Integer> ids) {
        cm.deleteByIds(ids);
    }

    @Override
    public void Add(Product product) {
        // 定义新的日期时间格式化器
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        product.setCreateTime(LocalDateTime.parse(LocalDateTime.now().format(formatter),formatter));
        product.setUpdateTime(LocalDateTime.parse(LocalDateTime.now().format(formatter),formatter));
        cm.Add(product);
    }


}
