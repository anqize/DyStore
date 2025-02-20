package com.store.controller;

import com.store.beans.Product;
import com.store.beans.Result;
import com.store.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cs;

    //查找购物车中全部商品信息
    @GetMapping
    public Result FindAll(){
        List<Product> list = cs.FindAll();
        return Result.success(list);
    }

    @PutMapping
    public Result Add(@RequestBody Product product){
        cs.Add(product);
        return Result.success();
    }

    @DeleteMapping
    public Result empty(@RequestParam List<Integer> ids){
        log.info("删除购物车中的商品id为: {}", ids);
        cs.deleteByIds(ids);
        return Result.success();
    }


}
