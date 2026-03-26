package com.cafeteria.entity;

import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单详情实体类
 * OrderDetail entity representing items in an order
 */
@Data
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
}
