package com.cafeteria.dao;

import com.cafeteria.entity.ShoppingCart;
import java.util.List;

/**
 * 购物车数据访问接口
 * ShoppingCart Data Access Object interface
 */
public interface ShoppingCartDao {

    /**
     * 添加购物车项
     * Add item to shopping cart
     *
     * @param shoppingCart 购物车对象
     * @return 影响的行数
     */
    int insert(ShoppingCart shoppingCart);

    /**
     * 根据ID删除购物车项
     * Delete shopping cart item by ID
     *
     * @param id 购物车项ID
     * @return 影响的行数
     */
    int deleteById(Long id);

    /**
     * 根据用户ID删除购物车所有项
     * Delete all shopping cart items by user ID
     *
     * @param userId 用户ID
     * @return 影响的行数
     */
    int deleteByUserId(Long userId);

    /**
     * 更新购物车项
     * Update shopping cart item
     *
     * @param shoppingCart 购物车对象
     * @return 影响的行数
     */
    int update(ShoppingCart shoppingCart);

    /**
     * 根据ID查询购物车项
     * Find shopping cart item by ID
     *
     * @param id 购物车项ID
     * @return 购物车对象
     */
    ShoppingCart findById(Long id);

    /**
     * 根据用户ID查询购物车
     * Find shopping cart items by user ID
     *
     * @param userId 用户ID
     * @return 购物车列表
     */
    List<ShoppingCart> findByUserId(Long userId);

    /**
     * 根据用户ID和菜品ID查询购物车项
     * Find shopping cart item by user ID and dish ID
     *
     * @param userId 用户ID
     * @param dishId 菜品ID
     * @return 购物车对象
     */
    ShoppingCart findByUserIdAndDishId(Long userId, Long dishId);

    /**
     * 更新购物车项数量
     * Update shopping cart item quantity
     *
     * @param id       购物车项ID
     * @param quantity 数量
     * @return 影响的行数
     */
    int updateQuantity(Long id, Integer quantity);
}
