package com.cafeteria.entity;

import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 菜品实体类
 * Dish entity representing menu items
 */
@Data
public class Dish {
    private Long id;
    private String name;
    private Long categoryId;
    private BigDecimal price;
    private String description;
    private String image;
    private Integer stock; // 库存数量
    private Integer status; // 0-下架, 1-上架
    private Date createTime;
    private Date updateTime;

    public Dish() {
    }

    public Dish(Long id, String name, Long categoryId, BigDecimal price, String description, Integer stock) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
        this.price = price;
        this.description = description;
        this.stock = stock;
        this.status = 1;
        this.createTime = new Date();
        this.updateTime = new Date();
    }
}
