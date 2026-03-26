package com.cafeteria.entity;

import java.util.Date;

/**
 * 购物车实体类
 * ShoppingCart entity for storing user's selected dishes temporarily
 */
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

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getDishId() {
        return dishId;
    }

    public void setDishId(Long dishId) {
        this.dishId = dishId;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "id=" + id +
                ", userId=" + userId +
                ", dishId=" + dishId +
                ", dishName='" + dishName + '\'' +
                ", quantity=" + quantity +
                ", createTime=" + createTime +
                '}';
    }
}
