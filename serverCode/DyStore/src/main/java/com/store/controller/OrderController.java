package com.store.controller;

import com.store.beans.Product;
import com.store.beans.Result;
import com.store.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping
public class OrderController {
    @Autowired
    private OrderService os;

    @GetMapping("/order")
    public Result createOrderForm(Integer id){
        Product p = os.FindByIds(id);
        if(p == null){
            return Result.error("请选择商品后结算");
        }
        return Result.success(p);
    }
}
