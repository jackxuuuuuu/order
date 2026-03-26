package com.cafeteria.util;

/**
 * 系统常量类
 * System constants
 */
public class Constants {

    // 用户状态
    public static final Integer USER_STATUS_DISABLED = 0;
    public static final Integer USER_STATUS_ENABLED = 1;

    // 菜品状态
    public static final Integer DISH_STATUS_OFFLINE = 0;
    public static final Integer DISH_STATUS_ONLINE = 1;

    // 订单状态
    public static final Integer ORDER_STATUS_PENDING = 0;
    public static final Integer ORDER_STATUS_PAID = 1;
    public static final Integer ORDER_STATUS_PREPARING = 2;
    public static final Integer ORDER_STATUS_COMPLETED = 3;
    public static final Integer ORDER_STATUS_CANCELLED = 4;

    // 默认分页参数
    public static final Integer DEFAULT_PAGE_SIZE = 10;
    public static final Integer DEFAULT_PAGE_NUM = 1;

    // 订单号前缀
    public static final String ORDER_NO_PREFIX = "ORD";

    private Constants() {
        // 私有构造函数，防止实例化
    }
}
