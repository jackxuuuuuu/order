package com.cafeteria.service.impl;

import com.cafeteria.dao.impl.UserDaoImpl;
import com.cafeteria.entity.User;
import com.cafeteria.service.UserService;
import com.cafeteria.util.CommonUtils;

import java.util.Date;
import java.util.List;

/**
 * 用户服务实现类
 * User service implementation
 */
public class UserServiceImpl implements UserService {

    private final UserDaoImpl userDao;

    public UserServiceImpl() {
        this.userDao = new UserDaoImpl();
    }

    public UserServiceImpl(UserDaoImpl userDao) {
        this.userDao = userDao;
    }

    @Override
    public boolean register(User user) {
        if (user == null) {
            return false;
        }
        // 数据验证
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            throw new IllegalArgumentException("用户名不能为空");
        }
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            throw new IllegalArgumentException("密码不能为空");
        }
        if (user.getPhone() != null && !user.getPhone().trim().isEmpty()
                && !CommonUtils.isValidPhone(user.getPhone())) {
            throw new IllegalArgumentException("手机号格式不正确");
        }
        if (user.getEmail() != null && !user.getEmail().trim().isEmpty()
                && !CommonUtils.isValidEmail(user.getEmail())) {
            throw new IllegalArgumentException("邮箱格式不正确");
        }

        // 检查用户名是否已存在
        User existingUser = userDao.findByUsername(user.getUsername());
        if (existingUser != null) {
            throw new IllegalArgumentException("用户名已存在");
        }

        // 检查手机号是否已存在
        if (user.getPhone() != null && !user.getPhone().trim().isEmpty()) {
            User existingPhone = userDao.findByPhone(user.getPhone());
            if (existingPhone != null) {
                throw new IllegalArgumentException("手机号已被注册");
            }
        }

        // 默认状态为启用
        if (user.getStatus() == null) {
            user.setStatus(1);
        }

        return userDao.insert(user) > 0;
    }

    @Override
    public User login(String username, String password) {
        if (username == null || username.trim().isEmpty()) {
            return null;
        }
        if (password == null || password.trim().isEmpty()) {
            return null;
        }

        User user = userDao.findByUsername(username);
        if (user == null) {
            return null;
        }

        // 检查密码
        if (!password.equals(user.getPassword())) {
            return null;
        }

        // 检查用户状态
        if (user.getStatus() == null || user.getStatus() != 1) {
            throw new IllegalStateException("用户已被禁用");
        }

        return user;
    }

    @Override
    public User getUserById(Long id) {
        if (id == null) {
            return null;
        }
        return userDao.findById(id);
    }

    @Override
    public User getUserByUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            return null;
        }
        return userDao.findByUsername(username);
    }

    @Override
    public boolean updateUser(User user) {
        if (user == null || user.getId() == null) {
            return false;
        }

        User existing = userDao.findById(user.getId());
        if (existing == null) {
            return false;
        }

        // 数据验证
        if (user.getUsername() != null && user.getUsername().trim().isEmpty()) {
            throw new IllegalArgumentException("用户名不能为空");
        }
        if (user.getPassword() != null && user.getPassword().trim().isEmpty()) {
            throw new IllegalArgumentException("密码不能为空");
        }
        if (user.getPhone() != null && !user.getPhone().trim().isEmpty()
                && !CommonUtils.isValidPhone(user.getPhone())) {
            throw new IllegalArgumentException("手机号格式不正确");
        }
        if (user.getEmail() != null && !user.getEmail().trim().isEmpty()
                && !CommonUtils.isValidEmail(user.getEmail())) {
            throw new IllegalArgumentException("邮箱格式不正确");
        }

        user.setUpdateTime(new Date());
        return userDao.update(user) > 0;
    }

    @Override
    public boolean deleteUser(Long id) {
        if (id == null) {
            return false;
        }
        return userDao.deleteById(id) > 0;
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    @Override
    public List<User> getUsersByRole(String role) {
        if (role == null || role.trim().isEmpty()) {
            return List.of();
        }
        return userDao.findByRole(role);
    }
}
