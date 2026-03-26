package com.cafeteria.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单详情实体类
 * OrderDetail entity representing items in an order
 */
public class OrderDetail {
    private Long id;
    private Long orderId;
    private Long dishId;
    private String dishName;
    private Integer quantity;
    private BigDecimal price; // 单价
    private BigDecimal amount; // 总价 (quantity * price)
    private Date createTime;

    public OrderDetail() {
    }

    public OrderDetail(Long id, Long orderId, Long dishId, String dishName, Integer quantity, BigDecimal price) {
        this.id = id;
        this.orderId = orderId;
        this.dishId = dishId;
        this.dishName = dishName;
        this.quantity = quantity;
        this.price = price;
        this.amount = price.multiply(new BigDecimal(quantity));
        this.createTime = new Date();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", dishId=" + dishId +
                ", dishName='" + dishName + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", amount=" + amount +
                '}';
    }
}
