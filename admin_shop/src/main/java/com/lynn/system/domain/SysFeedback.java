package com.lynn.system.domain;


import com.lynn.common.core.domain.BaseEntity;
import com.lynn.common.xss.Xss;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 意见反馈表 sys_feedback
 * 

 */
public class SysFeedback extends BaseEntity
{
    private static final long serialVersionUID = 1L;


    private Long fbId;

    private String fbTitle;
    private String fbContent;
    private String fbUserId;
    private String fbRemark;



    private int fbType;


    public int getFbType() {
        return fbType;
    }

    public void setFbType(int fbType) {
        this.fbType = fbType;
    }

    public Long getFbId() {
        return fbId;
    }

    public void setFbId(Long fbId) {
        this.fbId = fbId;
    }

    public String getFbTitle() {
        return fbTitle;
    }

    public void setFbTitle(String fbTitle) {
        this.fbTitle = fbTitle;
    }

    public String getFbContent() {
        return fbContent;
    }

    public void setFbContent(String fbContent) {
        this.fbContent = fbContent;
    }

    public String getFbUserId() {
        return fbUserId;
    }

    public void setFbUserId(String fbUserId) {
        this.fbUserId = fbUserId;
    }

    public String getFbRemark() {
        return fbRemark;
    }

    public void setFbRemark(String fbRemark) {
        this.fbRemark = fbRemark;
    }





}
