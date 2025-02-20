package com.store.controller;

import com.store.beans.Product;
import com.store.beans.Result;
import com.store.service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class ShoppingController {
    @Autowired
    private ShoppingService ss;

    @GetMapping("/shopping")
    public Result FindAll(Integer head, Integer size){
        List<Product> list = ss.FindAll(head, size);
        return Result.success(list);
    }
    @GetMapping("/products")
    public Result Find(){
        Integer size = ss.Find();
        return Result.success(size);
    }
    @GetMapping("/product")
    public Result findById(Integer id){
        Product p = ss.findById(id);
        return Result.success(p);
    }
}
