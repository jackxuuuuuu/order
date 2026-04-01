package com.cafeteria.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单实体类
 * Order entity representing customer orders
 */
@Data
public class Order {
    private Long id;
    private String orderNo; // 订单号
    private Long userId;
    private BigDecimal totalAmount;
    private Integer status; // 0-待支付, 1-已支付, 2-制作中, 3-已完成, 4-已取消
    private String paymentMethod; // CASH, ALIPAY, WECHAT
    private String remark; // 备注
    private Date createTime;
    private Date updateTime;

    public Order() {
    }

    public Order(Long id, String orderNo, Long userId, BigDecimal totalAmount, String paymentMethod) {
        this.id = id;
        this.orderNo = orderNo;
        this.userId = userId;
        this.totalAmount = totalAmount;
        this.paymentMethod = paymentMethod;
        this.status = 0;
        this.createTime = new Date();
        this.updateTime = new Date();
    }
}
