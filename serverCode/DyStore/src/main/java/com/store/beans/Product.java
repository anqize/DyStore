package com.store.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private int id; // 商品唯一标识
    private String name; // 商品名称
    private String description; // 商品描述
    private int categoryId; // 商品分类ID
    private double price; // 商品价格
    private int stockQuantity; // 商品库存数量
    private String status; // 商品状态：available/unavailable
    private LocalDateTime createTime; // 商品创建时间
    private LocalDateTime updateTime; // 商品更新时间
    private String image; // 商品主图URL
}
