package com.qf.common.http;

/**
 * @Author-izumi
 */
public enum GoodsState {

    PENDING(0L,"待审核"),
    UP(1L,"已上架"),
    DOWN(2L,"已下架");

    private Long code;
    private String description;

    GoodsState(Long code, String description) {
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
