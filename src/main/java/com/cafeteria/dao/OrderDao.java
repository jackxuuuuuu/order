package com.cafeteria.dao;

import com.cafeteria.entity.Order;
import java.util.List;

/**
 * 订单数据访问接口
 * Order Data Access Object interface
 */
public interface OrderDao {

    /**
     * 添加订单
     * Add a new order
     *
     * @param order 订单对象
     * @return 影响的行数
     */
    int insert(Order order);

    /**
     * 根据ID删除订单
     * Delete order by ID
     *
     * @param id 订单ID
     * @return 影响的行数
     */
    int deleteById(Long id);

    /**
     * 更新订单信息
     * Update order information
     *
     * @param order 订单对象
     * @return 影响的行数
     */
    int update(Order order);

    /**
     * 根据ID查询订单
     * Find order by ID
     *
     * @param id 订单ID
     * @return 订单对象
     */
    Order findById(Long id);

    /**
     * 根据订单号查询订单
     * Find order by order number
     *
     * @param orderNo 订单号
     * @return 订单对象
     */
    Order findByOrderNo(String orderNo);

    /**
     * 根据用户ID查询订单
     * Find orders by user ID
     *
     * @param userId 用户ID
     * @return 订单列表
     */
    List<Order> findByUserId(Long userId);

    /**
     * 根据状态查询订单
     * Find orders by status
     *
     * @param status 订单状态
     * @return 订单列表
     */
    List<Order> findByStatus(Integer status);

    /**
     * 查询所有订单
     * Find all orders
     *
     * @return 订单列表
     */
    List<Order> findAll();

    /**
     * 更新订单状态
     * Update order status
     *
     * @param id     订单ID
     * @param status 订单状态
     * @return 影响的行数
     */
    int updateStatus(Long id, Integer status);
}
