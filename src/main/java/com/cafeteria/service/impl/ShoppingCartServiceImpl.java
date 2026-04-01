package com.cafeteria.service.impl;

import com.cafeteria.dao.impl.DishDaoImpl;
import com.cafeteria.dao.impl.ShoppingCartDaoImpl;
import com.cafeteria.entity.Dish;
import com.cafeteria.entity.ShoppingCart;
import com.cafeteria.service.ShoppingCartService;

import java.util.List;

/**
 * 购物车服务实现类
 * Shopping cart service implementation
 */
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartDaoImpl shoppingCartDao;
    private final DishDaoImpl dishDao;

    public ShoppingCartServiceImpl() {
        this.shoppingCartDao = new ShoppingCartDaoImpl();
        this.dishDao = new DishDaoImpl();
    }

    public ShoppingCartServiceImpl(ShoppingCartDaoImpl shoppingCartDao, DishDaoImpl dishDao) {
        this.shoppingCartDao = shoppingCartDao;
        this.dishDao = dishDao;
    }

    @Override
    public boolean addToCart(Long userId, Long dishId, Integer quantity) {
        if (userId == null || dishId == null || quantity == null || quantity <= 0) {
            return false;
        }

        // 检查菜品是否存在
        Dish dish = dishDao.findById(dishId);
        if (dish == null) {
            throw new IllegalArgumentException("菜品不存在");
        }

        // 检查菜品状态
        if (dish.getStatus() == null || dish.getStatus() != 1) {
            throw new IllegalArgumentException("菜品未上架");
        }

        // 检查库存
        if (dish.getStock() == null || dish.getStock() < quantity) {
            throw new IllegalArgumentException("菜品库存不足");
        }

        // 检查购物车中是否已存在该菜品
        ShoppingCart existingCart = shoppingCartDao.findByUserIdAndDishId(userId, dishId);
        if (existingCart != null) {
            // 更新数量
            int newQuantity = existingCart.getQuantity() + quantity;
            if (dish.getStock() < newQuantity) {
                throw new IllegalArgumentException("菜品库存不足");
            }
            return shoppingCartDao.updateQuantity(existingCart.getId(), newQuantity) > 0;
        } else {
            // 新增购物车项
            ShoppingCart cart = new ShoppingCart();
            cart.setUserId(userId);
            cart.setDishId(dishId);
            cart.setDishName(dish.getName());
            cart.setQuantity(quantity);
            return shoppingCartDao.insert(cart) > 0;
        }
    }

    @Override
    public boolean removeFromCart(Long cartId) {
        if (cartId == null) {
            return false;
        }
        return shoppingCartDao.deleteById(cartId) > 0;
    }

    @Override
    public boolean updateCartItemQuantity(Long cartId, Integer quantity) {
        if (cartId == null || quantity == null || quantity <= 0) {
            return false;
        }

        ShoppingCart cart = shoppingCartDao.findById(cartId);
        if (cart == null) {
            return false;
        }

        // 检查菜品库存
        Dish dish = dishDao.findById(cart.getDishId());
        if (dish == null) {
            throw new IllegalArgumentException("菜品不存在");
        }
        if (dish.getStock() == null || dish.getStock() < quantity) {
            throw new IllegalArgumentException("菜品库存不足");
        }

        return shoppingCartDao.updateQuantity(cartId, quantity) > 0;
    }

    @Override
    public boolean clearCart(Long userId) {
        if (userId == null) {
            return false;
        }
        return shoppingCartDao.deleteByUserId(userId) > 0;
    }

    @Override
    public List<ShoppingCart> getCartByUserId(Long userId) {
        if (userId == null) {
            return List.of();
        }
        return shoppingCartDao.findByUserId(userId);
    }

    @Override
    public ShoppingCart getCartItemById(Long id) {
        if (id == null) {
            return null;
        }
        return shoppingCartDao.findById(id);
    }
}
