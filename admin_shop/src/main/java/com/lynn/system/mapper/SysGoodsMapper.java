package com.lynn.system.mapper;



import com.lynn.system.domain.SysGoods;

import java.util.List;

/**
 * 字典表 数据层
 * 
 * @author ruoyi
 */
public interface SysGoodsMapper
{


    public List<SysGoods> selectDataList(SysGoods dictType);

    public List<SysGoods> selectDataAll();


//    public List<SysDevice> selectDeviceByName(String deviceName);

    public SysGoods selectDeviceById(Long dictId);

    public SysGoods selectDataByName(String deviceName);



    public void deleteDeviceByIds(String ids);

    public void deleteDeviceById(Long id);

    public int insertDevice(SysGoods dictType);


    public int updateDevice(SysGoods dictType);









}
