package com.cafeteria.dao;

import com.cafeteria.entity.User;
import java.util.List;

/**
 * 用户数据访问接口
 * User Data Access Object interface
 */
public interface UserDao {

    /**
     * 添加用户
     * Add a new user
     *
     * @param user 用户对象
     * @return 影响的行数
     */
    int insert(User user);

    /**
     * 根据ID删除用户
     * Delete user by ID
     *
     * @param id 用户ID
     * @return 影响的行数
     */
    int deleteById(Long id);

    /**
     * 更新用户信息
     * Update user information
     *
     * @param user 用户对象
     * @return 影响的行数
     */
    int update(User user);

    /**
     * 根据ID查询用户
     * Find user by ID
     *
     * @param id 用户ID
     * @return 用户对象
     */
    User findById(Long id);

    /**
     * 根据用户名查询用户
     * Find user by username
     *
     * @param username 用户名
     * @return 用户对象
     */
    User findByUsername(String username);

    /**
     * 根据手机号查询用户
     * Find user by phone number
     *
     * @param phone 手机号
     * @return 用户对象
     */
    User findByPhone(String phone);

    /**
     * 查询所有用户
     * Find all users
     *
     * @return 用户列表
     */
    List<User> findAll();

    /**
     * 根据角色查询用户
     * Find users by role
     *
     * @param role 用户角色
     * @return 用户列表
     */
    List<User> findByRole(String role);
}
