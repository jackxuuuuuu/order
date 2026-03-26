package com.cafeteria.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单实体类
 * Order entity representing customer orders
 */
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

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderNo='" + orderNo + '\'' +
                ", userId=" + userId +
                ", totalAmount=" + totalAmount +
                ", status=" + status +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
