package com.lynn.system.service.impl;


import com.lynn.common.utils.text.Convert;
import com.lynn.system.domain.SysCategory;
import com.lynn.system.domain.SysGoods;
import com.lynn.system.mapper.SysGoodsMapper;
import com.lynn.system.service.ISysGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  业务层处理
 *
 * @author ruoyi
 */
@Service
public class SysGoodsServiceImpl implements ISysGoodsService {

    @Autowired
    private SysGoodsMapper sysDataMapper;


    @Override
    public List<SysGoods> selectDataList(SysGoods dictType) {
        return sysDataMapper.selectDataList(dictType);
    }

    @Override
    public List<SysGoods> selectDataAll() {
        return sysDataMapper.selectDataAll();
    }

    @Override
    public SysGoods selectDeviceById(Long dictId) {
        return sysDataMapper.selectDeviceById(dictId);
    }

    @Override
    public SysGoods selectDataByName(String deviceName) {
        return sysDataMapper.selectDataByName(deviceName);
    }

    @Override
    public void deleteDeviceById(Long id) {
        sysDataMapper.deleteDeviceById(id);
    }

    @Override
    public void deleteDeviceByIds(String ids) {
        Long[] dictIds = Convert.toLongArray(ids);
        for (Long dictId : dictIds) {
            SysGoods dictType = selectDeviceById(dictId);

            sysDataMapper.deleteDeviceByIds(dictId + "");

        }
    }

    @Override
    public int insertDevice(SysGoods dictType) {
        return sysDataMapper.insertDevice(dictType);
    }

    @Override
    public int updateDevice(SysGoods dictType) {
        return sysDataMapper.updateDevice(dictType);
    }
}
