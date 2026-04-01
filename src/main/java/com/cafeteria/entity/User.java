package com.cafeteria.entity;

import lombok.Data;

import java.util.Date;

/**
 * 用户实体类
 * User entity representing system users (customers and administrators)
 */
@Data
public class User {
    private Long id;
    private String username;
    private String password;
    private String phone;
    private String email;
    private String role; // CUSTOMER, ADMIN
    private Integer status; // 0-禁用, 1-启用
    private Date createTime;
    private Date updateTime;

    public User() {
    }

    public User(Long id, String username, String password, String phone, String email, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.role = role;
        this.status = 1;
        this.createTime = new Date();
        this.updateTime = new Date();
    }
}
