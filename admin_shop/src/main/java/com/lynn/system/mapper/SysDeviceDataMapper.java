package com.lynn.system.mapper;



import com.lynn.common.core.domain.entity.SysDeviceData;

import java.util.List;

/**
 * 字典表 数据层
 * 
 * @author ruoyi
 */
public interface SysDeviceDataMapper
{

    public List<SysDeviceData> selectSysDeviceList(SysDeviceData dictType);

    /**
     * 根据所有字典类型
     * 
     * @return 字典类型集合信息
     */
    public List<SysDeviceData> selectDataAll();




    /**
     * 新增字典类型信息
     * 
     * @param dictType 字典类型信息
     * @return 结果
     */
    public int insertDataType(SysDeviceData dictType);

    public int deleteDeviceById(Long dictId);
}
