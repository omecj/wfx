package com.qf.common.http;

/**
 * @Author oumae
 * @date 2019/8/5
 */
public enum OrderStatus {

    PAID(0L,"已支付"),
    PENDING(1L,"待支付");

    private Long code;
    private String description;

    OrderStatus(Long code, String description) {
        this.code = code;
        this.description = description;
    }

    public Long getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
