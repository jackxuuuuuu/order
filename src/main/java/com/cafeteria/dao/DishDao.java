package com.cafeteria.dao;

import com.cafeteria.entity.Dish;
import java.util.List;

/**
 * 菜品数据访问接口
 * Dish Data Access Object interface
 */
public interface DishDao {

    /**
     * 添加菜品
     * Add a new dish
     *
     * @param dish 菜品对象
     * @return 影响的行数
     */
    int insert(Dish dish);

    /**
     * 根据ID删除菜品
     * Delete dish by ID
     *
     * @param id 菜品ID
     * @return 影响的行数
     */
    int deleteById(Long id);

    /**
     * 更新菜品信息
     * Update dish information
     *
     * @param dish 菜品对象
     * @return 影响的行数
     */
    int update(Dish dish);

    /**
     * 根据ID查询菜品
     * Find dish by ID
     *
     * @param id 菜品ID
     * @return 菜品对象
     */
    Dish findById(Long id);

    /**
     * 查询所有菜品
     * Find all dishes
     *
     * @return 菜品列表
     */
    List<Dish> findAll();

    /**
     * 根据分类ID查询菜品
     * Find dishes by category ID
     *
     * @param categoryId 分类ID
     * @return 菜品列表
     */
    List<Dish> findByCategoryId(Long categoryId);

    /**
     * 根据状态查询菜品
     * Find dishes by status
     *
     * @param status 状态 (0-下架, 1-上架)
     * @return 菜品列表
     */
    List<Dish> findByStatus(Integer status);

    /**
     * 更新菜品库存
     * Update dish stock
     *
     * @param id    菜品ID
     * @param stock 库存数量
     * @return 影响的行数
     */
    int updateStock(Long id, Integer stock);
}
