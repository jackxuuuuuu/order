package com.cafeteria.dao;

import com.cafeteria.entity.OrderDetail;
import java.util.List;

/**
 * 订单详情数据访问接口
 * OrderDetail Data Access Object interface
 */
public interface OrderDetailDao {

    /**
     * 添加订单详情
     * Add a new order detail
     *
     * @param orderDetail 订单详情对象
     * @return 影响的行数
     */
    int insert(OrderDetail orderDetail);

    /**
     * 批量添加订单详情
     * Batch insert order details
     *
     * @param orderDetails 订单详情列表
     * @return 影响的行数
     */
    int batchInsert(List<OrderDetail> orderDetails);

    /**
     * 根据ID删除订单详情
     * Delete order detail by ID
     *
     * @param id 订单详情ID
     * @return 影响的行数
     */
    int deleteById(Long id);

    /**
     * 根据订单ID删除订单详情
     * Delete order details by order ID
     *
     * @param orderId 订单ID
     * @return 影响的行数
     */
    int deleteByOrderId(Long orderId);

    /**
     * 根据ID查询订单详情
     * Find order detail by ID
     *
     * @param id 订单详情ID
     * @return 订单详情对象
     */
    OrderDetail findById(Long id);

    /**
     * 根据订单ID查询订单详情列表
     * Find order details by order ID
     *
     * @param orderId 订单ID
     * @return 订单详情列表
     */
    List<OrderDetail> findByOrderId(Long orderId);
}
