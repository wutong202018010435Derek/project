package com.lynn.system.service;



import com.lynn.system.domain.SysFeedback;
import com.lynn.system.domain.SysNotice;

import java.util.List;

/**
 * 意见反馈 服务层

 */
public interface ISysFeedbackService
{

    public SysFeedback selectById(Long fbId);


    public List<SysFeedback> selectList(SysFeedback notice);


    public int insertFeedback(SysFeedback notice);


    public int updateFeedback(SysFeedback notice);


    public int deleteNoticeByIds(String ids);
}
