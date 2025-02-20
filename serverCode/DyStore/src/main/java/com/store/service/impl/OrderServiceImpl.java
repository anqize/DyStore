package com.store.service.impl;

import com.store.beans.Product;
import com.store.mapper.OrderMapper;
import com.store.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper om;

    @Override
    public Product FindByIds(Integer id) {
        return om.FindByIds(id);
    }
}
