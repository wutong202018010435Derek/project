package com.edu.squash.sql;

public class SqlCriteriaBuilder {
    private String mWhere;

    public SqlCriteriaBuilder where(String where) {
        this.mWhere = where;
        return this;
    }

    public SqlCriteriaBuilder andWhere(String where) {
        this.mWhere += " AND " + where;
        return this;
    }

    public SqlCriteriaBuilder orWhere(String where) {
        this.mWhere += " OR " + where;
        return this;
    }

    public String build() {
        return this.mWhere;
    }
}
