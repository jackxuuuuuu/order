package com.cafeteria.util;

/**
 * 支付方式枚举
 * Payment method enumeration
 */
public enum PaymentMethod {
    CASH("CASH", "现金"),
    ALIPAY("ALIPAY", "支付宝"),
    WECHAT("WECHAT", "微信支付");

    private final String code;
    private final String description;

    PaymentMethod(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static PaymentMethod fromCode(String code) {
        for (PaymentMethod method : PaymentMethod.values()) {
            if (method.code.equals(code)) {
                return method;
            }
        }
        throw new IllegalArgumentException("Unknown payment method code: " + code);
    }
}
