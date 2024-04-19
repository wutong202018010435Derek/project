package com.lynn.system.service.impl;


import com.lynn.common.core.domain.entity.SysDeviceData;
import com.lynn.common.utils.StringUtils;
import com.lynn.system.mapper.SysDeviceDataMapper;
import com.lynn.system.service.ISysDeviceDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 业务层处理
 *
 * @author ruoyi
 */
@Service
public class SysDeviceDataServiceImpl implements ISysDeviceDataService {
    @Autowired
    private SysDeviceDataMapper sysDataMapper;


    @Override
    public List<SysDeviceData> selectDeviceList(SysDeviceData sysData) {
        return sysDataMapper.selectSysDeviceList(sysData);
    }

    @Override
    public List<SysDeviceData> selectDeviceAll() {
        List<SysDeviceData> dictDatas = sysDataMapper.selectDataAll();
        if (StringUtils.isNotEmpty(dictDatas)) {

            return dictDatas;
        }
        return null;
    }

    @Override
    public int deleteDictDataByIds(String ids) {
        return sysDataMapper.deleteDeviceById(Long.parseLong(ids));
    }

    @Override
    public int insertDevice(SysDeviceData sysData) {
        int row = sysDataMapper.insertDataType(sysData);
        if (row > 0) {
//            DictUtils.setDictCache(dict.getDictType(), null);
        }
        return row;
    }
}
