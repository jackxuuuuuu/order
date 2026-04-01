package com.cafeteria.service.impl;

import com.cafeteria.dao.impl.DishDaoImpl;
import com.cafeteria.entity.Dish;
import com.cafeteria.service.DishService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 菜品服务实现类
 * Dish service implementation
 */
public class DishServiceImpl implements DishService {

    private final DishDaoImpl dishDao;

    public DishServiceImpl() {
        this.dishDao = new DishDaoImpl();
    }

    public DishServiceImpl(DishDaoImpl dishDao) {
        this.dishDao = dishDao;
    }

    @Override
    public boolean addDish(Dish dish) {
        if (dish == null) {
            return false;
        }
        // 数据验证
        if (dish.getName() == null || dish.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("菜品名称不能为空");
        }
        if (dish.getPrice() == null || dish.getPrice().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("菜品价格必须大于等于0");
        }
        if (dish.getStock() == null || dish.getStock() < 0) {
            throw new IllegalArgumentException("菜品库存必须大于等于0");
        }
        // 默认状态为上架
        if (dish.getStatus() == null) {
            dish.setStatus(1);
        }
        return dishDao.insert(dish) > 0;
    }

    @Override
    public boolean updateDish(Dish dish) {
        if (dish == null || dish.getId() == null) {
            return false;
        }
        Dish existing = dishDao.findById(dish.getId());
        if (existing == null) {
            return false;
        }
        // 数据验证
        if (dish.getName() != null && dish.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("菜品名称不能为空");
        }
        if (dish.getPrice() != null && dish.getPrice().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("菜品价格必须大于等于0");
        }
        if (dish.getStock() != null && dish.getStock() < 0) {
            throw new IllegalArgumentException("菜品库存必须大于等于0");
        }
        dish.setUpdateTime(new Date());
        return dishDao.update(dish) > 0;
    }

    @Override
    public boolean deleteDish(Long id) {
        if (id == null) {
            return false;
        }
        return dishDao.deleteById(id) > 0;
    }

    @Override
    public Dish getDishById(Long id) {
        if (id == null) {
            return null;
        }
        return dishDao.findById(id);
    }

    @Override
    public List<Dish> getAllDishes() {
        return dishDao.findAll();
    }

    @Override
    public List<Dish> getDishesByCategory(Long categoryId) {
        if (categoryId == null) {
            return List.of();
        }
        return dishDao.findByCategoryId(categoryId);
    }

    @Override
    public List<Dish> getAvailableDishes() {
        return dishDao.findByStatus(1);
    }

    @Override
    public boolean onlineDish(Long id) {
        if (id == null) {
            return false;
        }
        Dish dish = dishDao.findById(id);
        if (dish == null) {
            return false;
        }
        dish.setStatus(1);
        dish.setUpdateTime(new Date());
        return dishDao.update(dish) > 0;
    }

    @Override
    public boolean offlineDish(Long id) {
        if (id == null) {
            return false;
        }
        Dish dish = dishDao.findById(id);
        if (dish == null) {
            return false;
        }
        dish.setStatus(0);
        dish.setUpdateTime(new Date());
        return dishDao.update(dish) > 0;
    }

    @Override
    public boolean updateStock(Long id, Integer stock) {
        if (id == null || stock == null || stock < 0) {
            return false;
        }
        return dishDao.updateStock(id, stock) > 0;
    }

    /**
     * 根据名称搜索菜品（模糊匹配）
     * Search dishes by name (fuzzy match)
     *
     * @param name 菜品名称关键字
     * @return 菜品列表
     */
    public List<Dish> searchByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return List.of();
        }
        return dishDao.findByName(name);
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
        if (minPrice != null && maxPrice != null && minPrice.compareTo(maxPrice) > 0) {
            throw new IllegalArgumentException("最低价格不能大于最高价格");
        }
        return dishDao.findByPriceRange(minPrice, maxPrice);
    }

    /**
     * 组合搜索：根据名称、分类、价格区间、状态查询菜品
     * Combined search: find dishes by name, category, price range, and status
     *
     * @param name       菜品名称关键字
     * @param categoryId 分类ID
     * @param minPrice   最低价格
     * @param maxPrice   最高价格
     * @param status     状态
     * @return 菜品列表
     */
    public List<Dish> searchDishes(String name, Long categoryId, BigDecimal minPrice, BigDecimal maxPrice, Integer status) {
        if (minPrice != null && maxPrice != null && minPrice.compareTo(maxPrice) > 0) {
            throw new IllegalArgumentException("最低价格不能大于最高价格");
        }
        return dishDao.search(name, categoryId, minPrice, maxPrice, status);
    }
}
