package com.cafeteria.dao.impl;

import com.cafeteria.dao.UserDao;
import com.cafeteria.entity.User;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * 用户数据访问实现类（内存存储）
 * User DAO implementation with in-memory storage
 */
public class UserDaoImpl implements UserDao {

    private static final Map<Long, User> userStore = new ConcurrentHashMap<>();
    private static final AtomicLong idGenerator = new AtomicLong(1);

    @Override
    public int insert(User user) {
        if (user == null) {
            return 0;
        }
        if (user.getId() == null) {
            user.setId(idGenerator.getAndIncrement());
        }
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        userStore.put(user.getId(), user);
        return 1;
    }

    @Override
    public int deleteById(Long id) {
        if (id == null || !userStore.containsKey(id)) {
            return 0;
        }
        userStore.remove(id);
        return 1;
    }

    @Override
    public int update(User user) {
        if (user == null || user.getId() == null || !userStore.containsKey(user.getId())) {
            return 0;
        }
        user.setUpdateTime(new Date());
        userStore.put(user.getId(), user);
        return 1;
    }

    @Override
    public User findById(Long id) {
        if (id == null) {
            return null;
        }
        return userStore.get(id);
    }

    @Override
    public User findByUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            return null;
        }
        return userStore.values().stream()
                .filter(user -> username.equals(user.getUsername()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public User findByPhone(String phone) {
        if (phone == null || phone.trim().isEmpty()) {
            return null;
        }
        return userStore.values().stream()
                .filter(user -> phone.equals(user.getPhone()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(userStore.values());
    }

    @Override
    public List<User> findByRole(String role) {
        if (role == null || role.trim().isEmpty()) {
            return new ArrayList<>();
        }
        return userStore.values().stream()
                .filter(user -> role.equals(user.getRole()))
                .collect(Collectors.toList());
    }

    /**
     * 清空所有数据（用于测试）
     * Clear all data (for testing)
     */
    public void clear() {
        userStore.clear();
        idGenerator.set(1);
    }
}
