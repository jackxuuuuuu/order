package com.cafeteria;

import com.cafeteria.entity.*;
import com.cafeteria.util.*;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 * 食堂点餐系统主程序
 * Cafeteria Order System Main Application
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("=========================================");
        System.out.println("    欢迎使用食堂点餐系统");
        System.out.println("    Cafeteria Order System");
        System.out.println("=========================================");
        System.out.println();

        // 演示系统功能
        demonstrateSystem();
    }

    /**
     * 演示系统基本功能
     * Demonstrate basic system functionality
     */
    private static void demonstrateSystem() {
        System.out.println(">>> 系统功能演示 <<<");
        System.out.println();

        // 1. 创建用户
        System.out.println("1. 创建用户示例：");
        User customer = new User(1L, "zhangsan", "123456", "13800138000",
                                "zhangsan@example.com", UserRole.CUSTOMER.getCode());
        System.out.println("   创建顾客: " + customer);

        User admin = new User(2L, "admin", "admin123", "13900139000",
                             "admin@example.com", UserRole.ADMIN.getCode());
        System.out.println("   创建管理员: " + admin);
        System.out.println();

        // 2. 创建菜品
        System.out.println("2. 创建菜品示例：");
        Dish dish1 = new Dish(1L, "红烧肉", 1L, new BigDecimal("25.00"), "经典红烧肉，肥而不腻", 50);
        Dish dish2 = new Dish(2L, "西红柿炒蛋", 1L, new BigDecimal("12.00"), "家常菜", 100);
        Dish dish3 = new Dish(3L, "米饭", 2L, new BigDecimal("2.00"), "普通米饭", 200);
        System.out.println("   " + dish1);
        System.out.println("   " + dish2);
        System.out.println("   " + dish3);
        System.out.println();

        // 3. 创建购物车
        System.out.println("3. 添加到购物车示例：");
        ShoppingCart cart1 = new ShoppingCart(1L, customer.getId(), dish1.getId(), dish1.getName(), 1);
        ShoppingCart cart2 = new ShoppingCart(2L, customer.getId(), dish2.getId(), dish2.getName(), 1);
        ShoppingCart cart3 = new ShoppingCart(3L, customer.getId(), dish3.getId(), dish3.getName(), 2);
        System.out.println("   " + cart1);
        System.out.println("   " + cart2);
        System.out.println("   " + cart3);
        System.out.println();

        // 4. 创建订单
        System.out.println("4. 创建订单示例：");
        String orderNo = CommonUtils.generateOrderNo();
        BigDecimal totalAmount = dish1.getPrice()
                .add(dish2.getPrice())
                .add(dish3.getPrice().multiply(new BigDecimal("2")));
        Order order = new Order(1L, orderNo, customer.getId(), totalAmount, PaymentMethod.ALIPAY.getCode());
        order.setRemark("少放辣椒");
        System.out.println("   " + order);
        System.out.println("   订单总金额: ¥" + totalAmount);
        System.out.println();

        // 5. 创建订单详情
        System.out.println("5. 订单详情示例：");
        OrderDetail detail1 = new OrderDetail(1L, order.getId(), dish1.getId(), dish1.getName(), 1, dish1.getPrice());
        OrderDetail detail2 = new OrderDetail(2L, order.getId(), dish2.getId(), dish2.getName(), 1, dish2.getPrice());
        OrderDetail detail3 = new OrderDetail(3L, order.getId(), dish3.getId(), dish3.getName(), 2, dish3.getPrice());
        System.out.println("   " + detail1);
        System.out.println("   " + detail2);
        System.out.println("   " + detail3);
        System.out.println();

        // 6. 订单状态流转演示
        System.out.println("6. 订单状态流转：");
        System.out.println("   当前状态: " + OrderStatus.fromCode(order.getStatus()).getDescription());
        order.setStatus(OrderStatus.PAID.getCode());
        System.out.println("   支付后状态: " + OrderStatus.fromCode(order.getStatus()).getDescription());
        order.setStatus(OrderStatus.PREPARING.getCode());
        System.out.println("   制作中状态: " + OrderStatus.fromCode(order.getStatus()).getDescription());
        order.setStatus(OrderStatus.COMPLETED.getCode());
        System.out.println("   完成状态: " + OrderStatus.fromCode(order.getStatus()).getDescription());
        System.out.println();

        // 7. 工具类功能演示
        System.out.println("7. 工具类功能演示：");
        System.out.println("   生成订单号: " + CommonUtils.generateOrderNo());
        System.out.println("   验证手机号 (13800138000): " + CommonUtils.isValidPhone("13800138000"));
        System.out.println("   验证邮箱 (test@example.com): " + CommonUtils.isValidEmail("test@example.com"));
        System.out.println();

        System.out.println("=========================================");
        System.out.println("系统演示完成！");
        System.out.println("注意：以上仅为基础代码结构演示");
        System.out.println("实际应用需要配置数据库并实现DAO/Service层");
        System.out.println("=========================================");
    }
}
