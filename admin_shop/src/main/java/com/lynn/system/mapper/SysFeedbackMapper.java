package com.lynn.system.mapper;


import com.lynn.system.domain.SysFeedback;
import com.lynn.system.domain.SysNotice;

import java.util.List;

/**
 * 意见反馈 数据层
 * 

 */
public interface SysFeedbackMapper
{

    public SysFeedback selectById(Long noticeId);


    public List<SysFeedback> selectList(SysFeedback notice);


    public int insertFeedback(SysFeedback notice);


    public int updateFeedback(SysFeedback notice);


    public int deleteFeedbackByIds(String[] noticeIds);
}