package com.cafeteria.dao.impl;

import com.cafeteria.dao.OrderDetailDao;
import com.cafeteria.entity.OrderDetail;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * 订单详情数据访问实现类（内存存储）
 * OrderDetail DAO implementation with in-memory storage
 */
public class OrderDetailDaoImpl implements OrderDetailDao {

    private static final Map<Long, OrderDetail> detailStore = new ConcurrentHashMap<>();
    private static final AtomicLong idGenerator = new AtomicLong(1);

    @Override
    public int insert(OrderDetail orderDetail) {
        if (orderDetail == null) {
            return 0;
        }
        if (orderDetail.getId() == null) {
            orderDetail.setId(idGenerator.getAndIncrement());
        }
        orderDetail.setCreateTime(new Date());
        detailStore.put(orderDetail.getId(), orderDetail);
        return 1;
    }

    @Override
    public int batchInsert(List<OrderDetail> orderDetails) {
        if (orderDetails == null || orderDetails.isEmpty()) {
            return 0;
        }
        int count = 0;
        for (OrderDetail detail : orderDetails) {
            count += insert(detail);
        }
        return count;
    }

    @Override
    public int deleteById(Long id) {
        if (id == null || !detailStore.containsKey(id)) {
            return 0;
        }
        detailStore.remove(id);
        return 1;
    }

    @Override
    public int deleteByOrderId(Long orderId) {
        if (orderId == null) {
            return 0;
        }
        List<Long> toRemove = detailStore.values().stream()
                .filter(detail -> orderId.equals(detail.getOrderId()))
                .map(OrderDetail::getId)
                .collect(Collectors.toList());
        toRemove.forEach(detailStore::remove);
        return toRemove.size();
    }

    @Override
    public OrderDetail findById(Long id) {
        if (id == null) {
            return null;
        }
        return detailStore.get(id);
    }

    @Override
    public List<OrderDetail> findByOrderId(Long orderId) {
        if (orderId == null) {
            return new ArrayList<>();
        }
        return detailStore.values().stream()
                .filter(detail -> orderId.equals(detail.getOrderId()))
                .sorted(Comparator.comparing(OrderDetail::getCreateTime))
                .collect(Collectors.toList());
    }

    /**
     * 清空所有数据（用于测试）
     * Clear all data (for testing)
     */
    public void clear() {
        detailStore.clear();
        idGenerator.set(1);
    }
}
