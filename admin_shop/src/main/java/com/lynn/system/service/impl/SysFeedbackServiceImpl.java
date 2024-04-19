package com.lynn.system.service.impl;


import com.lynn.common.utils.text.Convert;
import com.lynn.system.domain.SysFeedback;
import com.lynn.system.domain.SysNotice;
import com.lynn.system.mapper.SysFeedbackMapper;
import com.lynn.system.mapper.SysNoticeMapper;
import com.lynn.system.service.ISysFeedbackService;
import com.lynn.system.service.ISysNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 意见反馈 服务层实现
 * 

 */
@Service
public class SysFeedbackServiceImpl implements ISysFeedbackService
{
    @Autowired
    private SysFeedbackMapper sysFeedbackMapper;

    @Override
    public SysFeedback selectById(Long fbId) {
        return sysFeedbackMapper.selectById(fbId);
    }

    @Override
    public List<SysFeedback> selectList(SysFeedback notice) {
        return sysFeedbackMapper.selectList(notice);
    }

    @Override
    public int insertFeedback(SysFeedback notice) {
        return sysFeedbackMapper.insertFeedback(notice);
    }

    @Override
    public int updateFeedback(SysFeedback notice) {
        return 0;
    }

    @Override
    public int deleteNoticeByIds(String ids) {
        return sysFeedbackMapper.deleteFeedbackByIds(Convert.toStrArray(ids));
    }


    /**
     * 删除公告对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
//    @Override
//    public int deleteNoticeByIds(String ids)
//    {
//        return noticeMapper.deleteNoticeByIds(Convert.toStrArray(ids));
//    }
}
