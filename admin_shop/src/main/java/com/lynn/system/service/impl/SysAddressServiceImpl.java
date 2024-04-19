package com.lynn.system.service.impl;


import com.lynn.common.utils.text.Convert;
import com.lynn.system.domain.SysAddress;
import com.lynn.system.domain.SysGoods;
import com.lynn.system.mapper.SysAddressMapper;
import com.lynn.system.mapper.SysGoodsMapper;
import com.lynn.system.service.ISysAddressService;
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
public class SysAddressServiceImpl implements ISysAddressService {
    @Autowired
    private SysAddressMapper sysAddressMapperr;


    @Override
    public List<SysAddress> selectDataList(SysAddress dictType) {
        return sysAddressMapperr.selectDataList(dictType);
    }

    @Override
    public List<SysAddress> selectDataAll() {
        return sysAddressMapperr.selectDataAll();
    }

    @Override
    public SysAddress selectDataById(Long dictId) {
        return sysAddressMapperr.selectDataById(dictId);
    }



//    @Override
//    public SysAddress selectDataByName(String deviceName) {
//        return sysAddressMapperr.selectDataByName(deviceName);
//    }

    @Override
    public int deleteDataByIds(String ids) {
//        Long[] dictIds = Convert.toLongArray(ids);
//        for (Long dictId : dictIds) {
          return  sysAddressMapperr.deleteDataByIds(Long.parseLong(ids));

//        }
//        return 0;
    }

    @Override
    public int insertData(SysAddress dictType) {
        return sysAddressMapperr.insertData(dictType);
    }

    @Override
    public int updateData(SysAddress dictType) {
        return 0;
    }
}
