package com.cafeteria.dao.impl;

import com.cafeteria.dao.DishDao;
import com.cafeteria.entity.Dish;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * 菜品数据访问实现类（内存存储）
 * Dish DAO implementation with in-memory storage
 */
public class DishDaoImpl implements DishDao {

    private static final Map<Long, Dish> dishStore = new ConcurrentHashMap<>();
    private static final AtomicLong idGenerator = new AtomicLong(1);

    @Override
    public int insert(Dish dish) {
        if (dish == null) {
            return 0;
        }
        if (dish.getId() == null) {
            dish.setId(idGenerator.getAndIncrement());
        }
        dish.setCreateTime(new Date());
        dish.setUpdateTime(new Date());
        dishStore.put(dish.getId(), dish);
        return 1;
    }

    @Override
    public int deleteById(Long id) {
        if (id == null || !dishStore.containsKey(id)) {
            return 0;
        }
        dishStore.remove(id);
        return 1;
    }

    @Override
    public int update(Dish dish) {
        if (dish == null || dish.getId() == null || !dishStore.containsKey(dish.getId())) {
            return 0;
        }
        dish.setUpdateTime(new Date());
        dishStore.put(dish.getId(), dish);
        return 1;
    }

    @Override
    public Dish findById(Long id) {
        if (id == null) {
            return null;
        }
        return dishStore.get(id);
    }

    @Override
    public List<Dish> findAll() {
        return new ArrayList<>(dishStore.values());
    }

    @Override
    public List<Dish> findByCategoryId(Long categoryId) {
        if (categoryId == null) {
            return new ArrayList<>();
        }
        return dishStore.values().stream()
                .filter(dish -> categoryId.equals(dish.getCategoryId()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Dish> findByStatus(Integer status) {
        if (status == null) {
            return new ArrayList<>();
        }
        return dishStore.values().stream()
                .filter(dish -> status.equals(dish.getStatus()))
                .collect(Collectors.toList());
    }

    @Override
    public int updateStock(Long id, Integer stock) {
        if (id == null || stock == null || !dishStore.containsKey(id)) {
            return 0;
        }
        Dish dish = dishStore.get(id);
        dish.setStock(stock);
        dish.setUpdateTime(new Date());
        return 1;
    }

    /**
     * 根据名称搜索菜品（模糊匹配）
     * Search dishes by name (fuzzy match)
     *
     * @param name 菜品名称关键字
     * @return 菜品列表
     */
    public List<Dish> findByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return new ArrayList<>();
        }
        String keyword = name.trim().toLowerCase();
        return dishStore.values().stream()
                .filter(dish -> dish.getName() != null && dish.getName().toLowerCase().contains(keyword))
                .collect(Collectors.toList());
    }

    /**
     * 根据价格区间查询菜品
     * Find dishes by price range
     *
     * @param minPrice 最低价格
     * @param maxPrice 最高价格
     * @return 菜品列表
     */
    public List<Dish> findByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        return dishStore.values().stream()
                .filter(dish -> {
                    if (dish.getPrice() == null) {
                        return false;
                    }
                    boolean meetMin = minPrice == null || dish.getPrice().compareTo(minPrice) >= 0;
                    boolean meetMax = maxPrice == null || dish.getPrice().compareTo(maxPrice) <= 0;
                    return meetMin && meetMax;
                })
                .collect(Collectors.toList());
    }

    /**
     * 组合查询：根据名称、分类、价格区间、状态查询菜品
     * Combined search: find dishes by name, category, price range, and status
     *
     * @param name       菜品名称关键字
     * @param categoryId 分类ID
     * @param minPrice   最低价格
     * @param maxPrice   最高价格
     * @param status     状态
     * @return 菜品列表
     */
    public List<Dish> search(String name, Long categoryId, BigDecimal minPrice, BigDecimal maxPrice, Integer status) {
        return dishStore.values().stream()
                .filter(dish -> {
                    // 名称过滤
                    if (name != null && !name.trim().isEmpty()) {
                        if (dish.getName() == null || !dish.getName().toLowerCase().contains(name.trim().toLowerCase())) {
                            return false;
                        }
                    }
                    // 分类过滤
                    if (categoryId != null && !categoryId.equals(dish.getCategoryId())) {
                        return false;
                    }
                    // 价格过滤
                    if (dish.getPrice() != null) {
                        if (minPrice != null && dish.getPrice().compareTo(minPrice) < 0) {
                            return false;
                        }
                        if (maxPrice != null && dish.getPrice().compareTo(maxPrice) > 0) {
                            return false;
                        }
                    }
                    // 状态过滤
                    if (status != null && !status.equals(dish.getStatus())) {
                        return false;
                    }
                    return true;
                })
                .collect(Collectors.toList());
    }

    /**
     * 清空所有数据（用于测试）
     * Clear all data (for testing)
     */
    public void clear() {
        dishStore.clear();
        idGenerator.set(1);
    }
}
