package com.cafeteria.dao.impl;

import com.cafeteria.dao.ShoppingCartDao;
import com.cafeteria.entity.ShoppingCart;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * 购物车数据访问实现类（内存存储）
 * ShoppingCart DAO implementation with in-memory storage
 */
public class ShoppingCartDaoImpl implements ShoppingCartDao {

    private static final Map<Long, ShoppingCart> cartStore = new ConcurrentHashMap<>();
    private static final AtomicLong idGenerator = new AtomicLong(1);

    @Override
    public int insert(ShoppingCart shoppingCart) {
        if (shoppingCart == null) {
            return 0;
        }
        if (shoppingCart.getId() == null) {
            shoppingCart.setId(idGenerator.getAndIncrement());
        }
        shoppingCart.setCreateTime(new Date());
        cartStore.put(shoppingCart.getId(), shoppingCart);
        return 1;
    }

    @Override
    public int deleteById(Long id) {
        if (id == null || !cartStore.containsKey(id)) {
            return 0;
        }
        cartStore.remove(id);
        return 1;
    }

    @Override
    public int deleteByUserId(Long userId) {
        if (userId == null) {
            return 0;
        }
        List<Long> toRemove = cartStore.values().stream()
                .filter(cart -> userId.equals(cart.getUserId()))
                .map(ShoppingCart::getId)
                .collect(Collectors.toList());
        toRemove.forEach(cartStore::remove);
        return toRemove.size();
    }

    @Override
    public int update(ShoppingCart shoppingCart) {
        if (shoppingCart == null || shoppingCart.getId() == null || !cartStore.containsKey(shoppingCart.getId())) {
            return 0;
        }
        cartStore.put(shoppingCart.getId(), shoppingCart);
        return 1;
    }

    @Override
    public ShoppingCart findById(Long id) {
        if (id == null) {
            return null;
        }
        return cartStore.get(id);
    }

    @Override
    public List<ShoppingCart> findByUserId(Long userId) {
        if (userId == null) {
            return new ArrayList<>();
        }
        return cartStore.values().stream()
                .filter(cart -> userId.equals(cart.getUserId()))
                .sorted(Comparator.comparing(ShoppingCart::getCreateTime))
                .collect(Collectors.toList());
    }

    @Override
    public ShoppingCart findByUserIdAndDishId(Long userId, Long dishId) {
        if (userId == null || dishId == null) {
            return null;
        }
        return cartStore.values().stream()
                .filter(cart -> userId.equals(cart.getUserId()) && dishId.equals(cart.getDishId()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public int updateQuantity(Long id, Integer quantity) {
        if (id == null || quantity == null || !cartStore.containsKey(id)) {
            return 0;
        }
        ShoppingCart cart = cartStore.get(id);
        cart.setQuantity(quantity);
        return 1;
    }

    /**
     * 清空所有数据（用于测试）
     * Clear all data (for testing)
     */
    public void clear() {
        cartStore.clear();
        idGenerator.set(1);
    }
}
