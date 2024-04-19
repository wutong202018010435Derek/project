package com.lynn.system.service;



import com.lynn.common.core.domain.Ztree;
import com.lynn.common.core.domain.entity.SysData;
import com.lynn.common.core.domain.entity.SysDictData;
import com.lynn.common.core.domain.entity.SysDictType;

import java.util.List;

/**
 * 字典 业务层
 * 
 * @author ruoyi
 */
public interface ISysDataService
{
    /**
     * 根据条件分页查询字典类型
     * 
     * @param sysData 字典类型信息
     * @return 字典类型集合信息
     */
    public List<SysData> selectDataList(SysData sysData);

    /**
     * 根据所有字典类型
     * 
     * @return 字典类型集合信息
     */
    public List<SysData> selectDataAll();



    public SysData selectDataFirst(String deviceId);


    /**
     * 新增保存字典类型信息
     * 
     * @param sysData 字典类型信息
     * @return 结果
     */
    public int insertDataType(SysData sysData);




}
