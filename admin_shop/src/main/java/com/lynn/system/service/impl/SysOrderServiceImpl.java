package com.lynn.system.service.impl;


import com.lynn.common.utils.text.Convert;
import com.lynn.system.domain.OrderBean;
import com.lynn.system.mapper.SysOrderMapper;
import com.lynn.system.service.ISysOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 业务层处理
 *
 * @author ruoyi
 */
@Service
public class SysOrderServiceImpl implements ISysOrderService {
    @Autowired
    private SysOrderMapper sysDataMapper;


    @Override
    public List<OrderBean> selectList(OrderBean dictType) {
        return sysDataMapper.selectList(dictType);
    }

    @Override
    public OrderBean selectByid(Long oId) {
        return sysDataMapper.selectDataById(oId);
    }


    @Override
    public int insertData(OrderBean dictType) {
        return sysDataMapper.insertData(dictType);
    }

    @Override
    public int deleteOrderByIds(String ids) {
        return sysDataMapper.deleteDataByIds(Convert.toStrArray(ids));
    }

    @Override
    public int updateData(OrderBean dictType) {
        return sysDataMapper.updateData(dictType);
    }
}
