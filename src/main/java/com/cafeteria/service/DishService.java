package com.cafeteria.service;

import com.cafeteria.entity.Dish;
import java.util.List;

/**
 * 菜品服务接口
 * Dish service interface
 */
public interface DishService {

    /**
     * 添加菜品
     * Add a new dish
     *
     * @param dish 菜品对象
     * @return 是否成功
     */
    boolean addDish(Dish dish);

    /**
     * 更新菜品信息
     * Update dish information
     *
     * @param dish 菜品对象
     * @return 是否成功
     */
    boolean updateDish(Dish dish);

    /**
     * 删除菜品
     * Delete dish
     *
     * @param id 菜品ID
     * @return 是否成功
     */
    boolean deleteDish(Long id);

    /**
     * 根据ID查询菜品
     * Get dish by ID
     *
     * @param id 菜品ID
     * @return 菜品对象
     */
    Dish getDishById(Long id);

    /**
     * 查询所有菜品
     * Get all dishes
     *
     * @return 菜品列表
     */
    List<Dish> getAllDishes();

    /**
     * 根据分类查询菜品
     * Get dishes by category
     *
     * @param categoryId 分类ID
     * @return 菜品列表
     */
    List<Dish> getDishesByCategory(Long categoryId);

    /**
     * 查询上架菜品
     * Get available dishes (status = 1)
     *
     * @return 菜品列表
     */
    List<Dish> getAvailableDishes();

    /**
     * 上架菜品
     * Put dish online
     *
     * @param id 菜品ID
     * @return 是否成功
     */
    boolean onlineDish(Long id);

    /**
     * 下架菜品
     * Put dish offline
     *
     * @param id 菜品ID
     * @return 是否成功
     */
    boolean offlineDish(Long id);

    /**
     * 更新菜品库存
     * Update dish stock
     *
     * @param id    菜品ID
     * @param stock 库存数量
     * @return 是否成功
     */
    boolean updateStock(Long id, Integer stock);
}
