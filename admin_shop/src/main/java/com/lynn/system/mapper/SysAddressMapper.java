package com.lynn.system.mapper;


import com.lynn.system.domain.SysAddress;
import com.lynn.system.domain.SysGoods;

import java.util.List;

/**
 * 字典表 数据层
 *
 * @author ruoyi
 */
public interface SysAddressMapper {


    public List<SysAddress> selectDataList(SysAddress dictType);

    public List<SysAddress> selectDataAll();


//    public List<SysDevice> selectDeviceByName(String deviceName);

    public SysAddress selectDataById(Long dictId);

    public int deleteDataByIds(Long ids);

    public int insertData(SysAddress dictType);


    public int updateDevice(SysAddress dictType);


}
