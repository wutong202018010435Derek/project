package com.lynn.system.service;



import com.lynn.system.domain.SysGoods;

import java.util.List;

/**
 * 设备 业务层
 * 

 */
public interface ISysGoodsService
{


    public List<SysGoods> selectDataList(SysGoods dictType);

    public List<SysGoods> selectDataAll();


//    public List<SysDevice> selectDeviceByName(String deviceName);

    public SysGoods selectDeviceById(Long dictId);

    public SysGoods selectDataByName(String deviceName);

    public void deleteDeviceById(Long id);

    public void deleteDeviceByIds(String ids);

    public int insertDevice(SysGoods dictType);


    public int updateDevice(SysGoods dictType);





}
