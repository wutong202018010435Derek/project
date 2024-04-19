package com.lynn.common.core.domain.entity;

public class SysControl {

    public String getControlName() {
        return controlName;
    }

    public void setControlName(String controlName) {
        this.controlName = controlName;
    }

    public int getControlState() {
        return controlState;
    }

    public void setControlState(int controlState) {
        this.controlState = controlState;
    }

    public String controlName;
    public int controlState;
}
