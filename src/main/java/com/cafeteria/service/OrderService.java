package com.cafeteria.service;

import com.cafeteria.entity.Order;
import java.util.List;

/**
 * 订单服务接口
 * Order service interface
 */
public interface OrderService {

    /**
     * 创建订单
     * Create a new order from shopping cart
     *
     * @param userId        用户ID
     * @param paymentMethod 支付方式
     * @param remark        备注
     * @return 订单对象
     */
    Order createOrder(Long userId, String paymentMethod, String remark);

    /**
     * 支付订单
     * Pay for an order
     *
     * @param orderId 订单ID
     * @return 是否成功
     */
    boolean payOrder(Long orderId);

    /**
     * 取消订单
     * Cancel an order
     *
     * @param orderId 订单ID
     * @return 是否成功
     */
    boolean cancelOrder(Long orderId);

    /**
     * 完成订单
     * Complete an order
     *
     * @param orderId 订单ID
     * @return 是否成功
     */
    boolean completeOrder(Long orderId);

    /**
     * 更新订单状态
     * Update order status
     *
     * @param orderId 订单ID
     * @param status  订单状态
     * @return 是否成功
     */
    boolean updateOrderStatus(Long orderId, Integer status);

    /**
     * 根据ID查询订单
     * Get order by ID
     *
     * @param id 订单ID
     * @return 订单对象
     */
    Order getOrderById(Long id);

    /**
     * 根据订单号查询订单
     * Get order by order number
     *
     * @param orderNo 订单号
     * @return 订单对象
     */
    Order getOrderByOrderNo(String orderNo);

    /**
     * 查询用户的所有订单
     * Get all orders of a user
     *
     * @param userId 用户ID
     * @return 订单列表
     */
    List<Order> getOrdersByUserId(Long userId);

    /**
     * 查询所有订单
     * Get all orders
     *
     * @return 订单列表
     */
    List<Order> getAllOrders();

    /**
     * 根据状态查询订单
     * Get orders by status
     *
     * @param status 订单状态
     * @return 订单列表
     */
    List<Order> getOrdersByStatus(Integer status);
}
