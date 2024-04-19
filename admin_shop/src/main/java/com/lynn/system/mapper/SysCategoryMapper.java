package com.lynn.system.mapper;



import com.lynn.common.core.domain.entity.SysDevice;
import com.lynn.system.domain.SysCategory;

import java.util.List;

/**
 * 设备 数据层
 * 

 */
public interface SysCategoryMapper
{

    public List<SysCategory> selectDeviceList(SysCategory dictType);


    public List<SysCategory> selectDictTypeAll();


    public SysCategory selectDeviceById(Long dictId);


    public SysCategory selectDeviceByName(String name);




    public int deleteDeviceById(Long dictId);


    public int deleteDeviceByIds(Long[] ids);


    public int insertDevice(SysCategory dictType);


    public int updateDevice(SysCategory dictType);


}
