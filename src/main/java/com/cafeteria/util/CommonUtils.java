package com.cafeteria.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 工具类
 * Utility methods for common operations
 */
public class CommonUtils {

    private static final Random RANDOM = new Random();

    /**
     * 生成订单号
     * Generate unique order number
     *
     * @return 订单号 (格式: ORD + 时间戳 + 4位随机数)
     */
    public static String generateOrderNo() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String timestamp = sdf.format(new Date());
        int randomNum = RANDOM.nextInt(9000) + 1000; // 1000-9999
        return Constants.ORDER_NO_PREFIX + timestamp + randomNum;
    }

    /**
     * 检查字符串是否为空
     * Check if string is null or empty
     *
     * @param str 待检查的字符串
     * @return true if null or empty
     */
    public static boolean isEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    /**
     * 检查字符串是否不为空
     * Check if string is not null and not empty
     *
     * @param str 待检查的字符串
     * @return true if not null and not empty
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 验证手机号格式
     * Validate phone number format (simple validation)
     *
     * @param phone 手机号
     * @return true if valid
     */
    public static boolean isValidPhone(String phone) {
        if (isEmpty(phone)) {
            return false;
        }
        return phone.matches("^1[3-9]\\d{9}$");
    }

    /**
     * 验证邮箱格式
     * Validate email format
     *
     * @param email 邮箱
     * @return true if valid
     */
    public static boolean isValidEmail(String email) {
        if (isEmpty(email)) {
            return false;
        }
        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }

    private CommonUtils() {
        // 私有构造函数，防止实例化
    }
}
