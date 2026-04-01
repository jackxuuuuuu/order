package com.cafeteria.dao.impl;

import com.cafeteria.dao.OrderDao;
import com.cafeteria.entity.Order;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * 订单数据访问实现类（内存存储）
 * Order DAO implementation with in-memory storage
 */
public class OrderDaoImpl implements OrderDao {

    private static final Map<Long, Order> orderStore = new ConcurrentHashMap<>();
    private static final AtomicLong idGenerator = new AtomicLong(1);

    @Override
    public int insert(Order order) {
        if (order == null) {
            return 0;
        }
        if (order.getId() == null) {
            order.setId(idGenerator.getAndIncrement());
        }
        order.setCreateTime(new Date());
        order.setUpdateTime(new Date());
        orderStore.put(order.getId(), order);
        return 1;
    }

    @Override
    public int deleteById(Long id) {
        if (id == null || !orderStore.containsKey(id)) {
            return 0;
        }
        orderStore.remove(id);
        return 1;
    }

    @Override
    public int update(Order order) {
        if (order == null || order.getId() == null || !orderStore.containsKey(order.getId())) {
            return 0;
        }
        order.setUpdateTime(new Date());
        orderStore.put(order.getId(), order);
        return 1;
    }

    @Override
    public Order findById(Long id) {
        if (id == null) {
            return null;
        }
        return orderStore.get(id);
    }

    @Override
    public Order findByOrderNo(String orderNo) {
        if (orderNo == null || orderNo.trim().isEmpty()) {
            return null;
        }
        return orderStore.values().stream()
                .filter(order -> orderNo.equals(order.getOrderNo()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Order> findByUserId(Long userId) {
        if (userId == null) {
            return new ArrayList<>();
        }
        return orderStore.values().stream()
                .filter(order -> userId.equals(order.getUserId()))
                .sorted(Comparator.comparing(Order::getCreateTime).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<Order> findByStatus(Integer status) {
        if (status == null) {
            return new ArrayList<>();
        }
        return orderStore.values().stream()
                .filter(order -> status.equals(order.getStatus()))
                .sorted(Comparator.comparing(Order::getCreateTime).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<Order> findAll() {
        return orderStore.values().stream()
                .sorted(Comparator.comparing(Order::getCreateTime).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public int updateStatus(Long id, Integer status) {
        if (id == null || status == null || !orderStore.containsKey(id)) {
            return 0;
        }
        Order order = orderStore.get(id);
        order.setStatus(status);
        order.setUpdateTime(new Date());
        return 1;
    }

    /**
     * 清空所有数据（用于测试）
     * Clear all data (for testing)
     */
    public void clear() {
        orderStore.clear();
        idGenerator.set(1);
    }
}
