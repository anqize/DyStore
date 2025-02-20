package com.store.service;

import com.store.beans.Product;

import java.util.List;

public interface CartService {
    List<Product> FindAll();

    void deleteByIds(List<Integer> ids);

    void Add(Product product);


}
