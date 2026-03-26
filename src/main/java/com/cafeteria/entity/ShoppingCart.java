package com.cafeteria.entity;

import lombok.Data;
import java.util.Date;

/**
 * 购物车实体类
 * ShoppingCart entity for storing user's selected dishes temporarily
 */
@Data
public class ShoppingCart {
    private Long id;
    private Long userId;
    private Long dishId;
    private String dishName;
    private Integer quantity;
    private Date createTime;

    public ShoppingCart() {
    }

    public ShoppingCart(Long id, Long userId, Long dishId, String dishName, Integer quantity) {
        this.id = id;
        this.userId = userId;
        this.dishId = dishId;
        this.dishName = dishName;
        this.quantity = quantity;
        this.createTime = new Date();
    }
}
