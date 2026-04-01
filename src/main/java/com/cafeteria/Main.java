package com.cafeteria;

import com.cafeteria.entity.*;
import com.cafeteria.service.impl.*;
import com.cafeteria.util.*;

import java.math.BigDecimal;
import java.util.List;

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
        System.out.println(">>> 系统完整功能演示 <<<");
        System.out.println();

        // 初始化服务层
        UserServiceImpl userService = new UserServiceImpl();
        DishServiceImpl dishService = new DishServiceImpl();
        ShoppingCartServiceImpl cartService = new ShoppingCartServiceImpl();
        OrderServiceImpl orderService = new OrderServiceImpl();

        try {
            // 1. 用户注册和登录
            System.out.println("=== 1. 用户管理功能 ===");
            User customer = new User(null, "zhangsan", "123456", "13800138000",
                    "zhangsan@example.com", UserRole.CUSTOMER.getCode());
            userService.register(customer);
            System.out.println("✓ 用户注册成功: " + customer.getUsername());

            User loginUser = userService.login("zhangsan", "123456");
            System.out.println("✓ 用户登录成功: " + loginUser.getUsername() + " (ID: " + loginUser.getId() + ")");
            System.out.println();

            // 2. 菜品管理
            System.out.println("=== 2. 菜品管理功能 ===");
            Dish dish1 = new Dish(null, "红烧肉", 1L, new BigDecimal("25.00"), "经典红烧肉，肥而不腻", 50);
            Dish dish2 = new Dish(null, "西红柿炒蛋", 1L, new BigDecimal("12.00"), "家常菜", 100);
            Dish dish3 = new Dish(null, "米饭", 2L, new BigDecimal("2.00"), "普通米饭", 200);
            Dish dish4 = new Dish(null, "宫保鸡丁", 1L, new BigDecimal("28.00"), "川菜经典", 30);
            Dish dish5 = new Dish(null, "清蒸鱼", 1L, new BigDecimal("35.00"), "新鲜海鱼", 20);

            dishService.addDish(dish1);
            dishService.addDish(dish2);
            dishService.addDish(dish3);
            dishService.addDish(dish4);
            dishService.addDish(dish5);
            System.out.println("✓ 添加了 5 个菜品");
            System.out.println();

            // 3. 菜品搜索和筛选功能
            System.out.println("=== 3. 菜品搜索和筛选功能 ===");

            // 按名称搜索
            System.out.println(">> 搜索包含'鸡'的菜品:");
            List<Dish> searchResult = dishService.searchByName("鸡");
            for (Dish dish : searchResult) {
                System.out.println("   - " + dish.getName() + " ¥" + dish.getPrice());
            }

            // 按价格区间筛选
            System.out.println("\n>> 价格在 ¥10-¥30 之间的菜品:");
            List<Dish> priceResult = dishService.findByPriceRange(new BigDecimal("10"), new BigDecimal("30"));
            for (Dish dish : priceResult) {
                System.out.println("   - " + dish.getName() + " ¥" + dish.getPrice());
            }

            // 组合搜索
            System.out.println("\n>> 组合搜索 (分类1, 价格>¥20, 上架状态):");
            List<Dish> combinedResult = dishService.searchDishes(null, 1L, new BigDecimal("20"), null, 1);
            for (Dish dish : combinedResult) {
                System.out.println("   - " + dish.getName() + " ¥" + dish.getPrice() + " (库存: " + dish.getStock() + ")");
            }

            // 查询所有上架菜品
            System.out.println("\n>> 所有上架菜品:");
            List<Dish> availableDishes = dishService.getAvailableDishes();
            System.out.println("   共 " + availableDishes.size() + " 个上架菜品");
            System.out.println();

            // 4. 购物车功能
            System.out.println("=== 4. 购物车功能 ===");
            cartService.addToCart(loginUser.getId(), dish1.getId(), 1);
            System.out.println("✓ 添加 " + dish1.getName() + " x1 到购物车");

            cartService.addToCart(loginUser.getId(), dish2.getId(), 1);
            System.out.println("✓ 添加 " + dish2.getName() + " x1 到购物车");

            cartService.addToCart(loginUser.getId(), dish3.getId(), 2);
            System.out.println("✓ 添加 " + dish3.getName() + " x2 到购物车");

            List<ShoppingCart> cartItems = cartService.getCartByUserId(loginUser.getId());
            System.out.println("购物车内容:");
            for (ShoppingCart item : cartItems) {
                System.out.println("   - " + item.getDishName() + " x" + item.getQuantity());
            }
            System.out.println();

            // 5. 创建订单
            System.out.println("=== 5. 订单管理功能 ===");
            Order order = orderService.createOrder(loginUser.getId(), PaymentMethod.ALIPAY.getCode(), "少放辣椒");
            System.out.println("✓ 订单创建成功");
            System.out.println("   订单号: " + order.getOrderNo());
            System.out.println("   总金额: ¥" + order.getTotalAmount());
            System.out.println("   状态: " + OrderStatus.fromCode(order.getStatus()).getDescription());
            System.out.println("   备注: " + order.getRemark());

            // 检查库存是否被扣减
            Dish updatedDish1 = dishService.getDishById(dish1.getId());
            System.out.println("   " + dish1.getName() + " 库存: 50 -> " + updatedDish1.getStock());

            // 检查购物车是否被清空
            List<ShoppingCart> emptyCart = cartService.getCartByUserId(loginUser.getId());
            System.out.println("   购物车已清空: " + emptyCart.isEmpty());
            System.out.println();

            // 6. 订单状态流转
            System.out.println("=== 6. 订单状态流转 ===");
            System.out.println("当前状态: " + OrderStatus.fromCode(order.getStatus()).getDescription());

            orderService.payOrder(order.getId());
            Order paidOrder = orderService.getOrderById(order.getId());
            System.out.println("支付后状态: " + OrderStatus.fromCode(paidOrder.getStatus()).getDescription());

            orderService.updateOrderStatus(order.getId(), OrderStatus.PREPARING.getCode());
            Order preparingOrder = orderService.getOrderById(order.getId());
            System.out.println("制作中状态: " + OrderStatus.fromCode(preparingOrder.getStatus()).getDescription());

            orderService.completeOrder(order.getId());
            Order completedOrder = orderService.getOrderById(order.getId());
            System.out.println("完成状态: " + OrderStatus.fromCode(completedOrder.getStatus()).getDescription());
            System.out.println();

            // 7. 查询功能
            System.out.println("=== 7. 查询功能 ===");
            List<Order> userOrders = orderService.getOrdersByUserId(loginUser.getId());
            System.out.println("用户订单数量: " + userOrders.size());

            List<Order> completedOrders = orderService.getOrdersByStatus(OrderStatus.COMPLETED.getCode());
            System.out.println("已完成订单数量: " + completedOrders.size());
            System.out.println();

            // 8. 工具类功能演示
            System.out.println("=== 8. 工具类功能 ===");
            System.out.println("生成订单号: " + CommonUtils.generateOrderNo());
            System.out.println("验证手机号 (13800138000): " + CommonUtils.isValidPhone("13800138000"));
            System.out.println("验证邮箱 (test@example.com): " + CommonUtils.isValidEmail("test@example.com"));
            System.out.println();

            System.out.println("=========================================");
            System.out.println("系统功能演示完成！");
            System.out.println("✓ DAO层实现完成（内存存储）");
            System.out.println("✓ Service层实现完成（业务逻辑）");
            System.out.println("✓ 菜品搜索和筛选功能实现完成");
            System.out.println("=========================================");

        } catch (Exception e) {
            System.err.println("错误: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
