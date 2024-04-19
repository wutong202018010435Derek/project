package com.lynn.system.domain;

import com.lynn.common.core.domain.BaseEntity;

public class SysCategory extends BaseEntity {

    private static final long serialVersionUID = 1L;
    private String categoryName;
    private Long categoryId;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }




}
