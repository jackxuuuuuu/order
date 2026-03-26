package com.cafeteria.service;

import com.cafeteria.entity.ShoppingCart;
import java.util.List;

/**
 * 购物车服务接口
 * Shopping cart service interface
 */
public interface ShoppingCartService {

    /**
     * 添加菜品到购物车
     * Add dish to shopping cart
     *
     * @param userId   用户ID
     * @param dishId   菜品ID
     * @param quantity 数量
     * @return 是否成功
     */
    boolean addToCart(Long userId, Long dishId, Integer quantity);

    /**
     * 从购物车移除菜品
     * Remove dish from shopping cart
     *
     * @param cartId 购物车项ID
     * @return 是否成功
     */
    boolean removeFromCart(Long cartId);

    /**
     * 更新购物车项数量
     * Update cart item quantity
     *
     * @param cartId   购物车项ID
     * @param quantity 数量
     * @return 是否成功
     */
    boolean updateCartItemQuantity(Long cartId, Integer quantity);

    /**
     * 清空用户购物车
     * Clear user's shopping cart
     *
     * @param userId 用户ID
     * @return 是否成功
     */
    boolean clearCart(Long userId);

    /**
     * 查询用户购物车
     * Get user's shopping cart
     *
     * @param userId 用户ID
     * @return 购物车列表
     */
    List<ShoppingCart> getCartByUserId(Long userId);

    /**
     * 根据ID查询购物车项
     * Get cart item by ID
     *
     * @param id 购物车项ID
     * @return 购物车对象
     */
    ShoppingCart getCartItemById(Long id);
}
