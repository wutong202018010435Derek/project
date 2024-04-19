package com.lynn.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lynn.common.core.domain.BaseEntity;

import java.util.Date;

public class SysBanner extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Long bId;
    private String bImg;//
    private String bText;

    public int getbState() {
        return bState;
    }

    public void setbState(int bState) {
        this.bState = bState;
    }

    private int bState;


    public Long getbId() {
        return bId;
    }

    public void setbId(Long bId) {
        this.bId = bId;
    }

    public String getbImg() {
        return bImg;
    }

    public void setbImg(String bImg) {
        this.bImg = bImg;
    }

    public String getbText() {
        return bText;
    }

    public void setbText(String bText) {
        this.bText = bText;
    }






}
