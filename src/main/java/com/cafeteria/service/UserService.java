package com.cafeteria.service;

import com.cafeteria.entity.User;
import java.util.List;

/**
 * 用户服务接口
 * User service interface
 */
public interface UserService {

    /**
     * 用户注册
     * Register a new user
     *
     * @param user 用户对象
     * @return 是否成功
     */
    boolean register(User user);

    /**
     * 用户登录
     * User login
     *
     * @param username 用户名
     * @param password 密码
     * @return 用户对象，登录失败返回null
     */
    User login(String username, String password);

    /**
     * 根据ID查询用户
     * Find user by ID
     *
     * @param id 用户ID
     * @return 用户对象
     */
    User getUserById(Long id);

    /**
     * 根据用户名查询用户
     * Find user by username
     *
     * @param username 用户名
     * @return 用户对象
     */
    User getUserByUsername(String username);

    /**
     * 更新用户信息
     * Update user information
     *
     * @param user 用户对象
     * @return 是否成功
     */
    boolean updateUser(User user);

    /**
     * 删除用户
     * Delete user
     *
     * @param id 用户ID
     * @return 是否成功
     */
    boolean deleteUser(Long id);

    /**
     * 查询所有用户
     * Get all users
     *
     * @return 用户列表
     */
    List<User> getAllUsers();

    /**
     * 根据角色查询用户
     * Get users by role
     *
     * @param role 角色
     * @return 用户列表
     */
    List<User> getUsersByRole(String role);
}
