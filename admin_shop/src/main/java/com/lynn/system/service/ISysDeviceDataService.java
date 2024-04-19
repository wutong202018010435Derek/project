package com.lynn.system.service;



import com.lynn.common.core.domain.entity.SysDeviceData;

import java.util.List;

/**
 * 字典 业务层
 * 
 * @author ruoyi
 */
public interface ISysDeviceDataService
{
    /**

     */
    public List<SysDeviceData> selectDeviceList(SysDeviceData sysData);

    /**

     */
    public List<SysDeviceData> selectDeviceAll();



    public int deleteDictDataByIds(String ids);


    /**

     */
    public int insertDevice(SysDeviceData sysData);




}
