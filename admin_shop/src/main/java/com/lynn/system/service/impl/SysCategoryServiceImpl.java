package com.lynn.system.service.impl;


import com.lynn.common.core.domain.entity.SysDevice;
import com.lynn.common.utils.text.Convert;
import com.lynn.system.domain.SysCategory;
import com.lynn.system.mapper.SysCategoryMapper;
import com.lynn.system.service.ISysCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 设备 业务层处理
 *

 */
@Service
public class SysCategoryServiceImpl implements ISysCategoryService {


    @Autowired
    private SysCategoryMapper sysDeviceMapper;


    @Override
    public List<SysCategory> selectDeviceList(SysCategory dictType) {
        return sysDeviceMapper.selectDeviceList(dictType);
    }




    @Override
    public List<SysCategory> selectDeviceAll() {
        return sysDeviceMapper.selectDictTypeAll();
    }




    @Override
    public SysCategory selectDeviceById(Long dictId) {
        return sysDeviceMapper.selectDeviceById(dictId);
    }








    @Override
    public SysCategory selectDeviceByName(String deviceName) {
        return sysDeviceMapper.selectDeviceByName(deviceName);
    }



    @Override
    public void deleteDeviceByIds(String ids) {
        Long[] dictIds = Convert.toLongArray(ids);
        for (Long dictId : dictIds) {
            SysCategory dictType = selectDeviceById(dictId);

            sysDeviceMapper.deleteDeviceById(dictId);

        }
    }

    @Override
    public int insertDevice(SysCategory dictType) {
        int row = sysDeviceMapper.insertDevice(dictType);
        return row;
    }

    @Override
    public int updateDevice(SysCategory dictType) {
        int row = sysDeviceMapper.updateDevice(dictType);
        return row;
    }
}
