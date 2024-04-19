package com.lynn.system.mapper;



import com.lynn.common.core.domain.entity.SysData;
import com.lynn.common.core.domain.entity.SysDictType;

import java.util.List;

/**
 * 字典表 数据层
 * 
 * @author ruoyi
 */
public interface SysDataMapper
{

    public List<SysData> selectSysDataList(SysData dictType);

    /**
     * 根据所有字典类型
     * 
     * @return 字典类型集合信息
     */
    public List<SysData> selectDataAll();


    public SysData selectDataFirst(String deviceId);


//    /**
//     * 根据字典类型ID查询信息
//     *
//     * @param dictId 字典类型ID
//     * @return 字典类型
//     */
//    public SysData selectDataById(Long dictId);






    /**
     * 新增字典类型信息
     * 
     * @param dictType 字典类型信息
     * @return 结果
     */
    public int insertDataType(SysData dictType);


}
