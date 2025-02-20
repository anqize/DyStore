package com.store.service;

import com.store.beans.Product;

import java.util.List;

public interface ShoppingService {
    List<Product> FindAll(Integer head, Integer size);

    Integer Find();

    Product findById(Integer id);
}
