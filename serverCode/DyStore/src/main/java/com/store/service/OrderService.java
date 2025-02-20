package com.store.service;

import com.store.beans.Product;

import java.util.List;

public interface OrderService {
    Product FindByIds(Integer id);
}
