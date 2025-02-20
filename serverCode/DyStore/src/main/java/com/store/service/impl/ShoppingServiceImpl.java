package com.store.service.impl;

import com.store.beans.Product;
import com.store.mapper.ShoppingMapper;
import com.store.service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingServiceImpl implements ShoppingService {
    @Autowired
    private ShoppingMapper sm;

    @Override
    public List<Product> FindAll(Integer head, Integer size) {
        return sm.FindAll(head,size);
    }

    @Override
    public Integer Find() {
        return sm.Find();
    }

    @Override
    public Product findById(Integer id) {
        return sm.findById(id);
    }
}
