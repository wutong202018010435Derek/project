package com.lynn.system.service;



import com.lynn.system.domain.SysAddress;
import com.lynn.system.domain.SysGoods;

import java.util.List;

/**
 * 设备 业务层
 * 

 */
public interface ISysAddressService
{


    public List<SysAddress> selectDataList(SysAddress dictType);

    public List<SysAddress> selectDataAll();


//    public List<SysDevice> selectDeviceByName(String deviceName);

    public SysAddress selectDataById(Long dictId);




    public int deleteDataByIds(String ids);

    public int insertData(SysAddress dictType);


    public int updateData(SysAddress dictType);





}
