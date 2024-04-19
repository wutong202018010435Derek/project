package com.lynn.system.service;



import com.lynn.common.core.domain.entity.SysDevice;
import com.lynn.system.domain.SysCategory;

import java.util.List;

/**


 */
public interface ISysCategoryService
{

    public List<SysCategory> selectDeviceList(SysCategory dictType);

    public List<SysCategory> selectDeviceAll();


//    public List<SysDevice> selectDeviceByName(String deviceName);

    public SysCategory selectDeviceById(Long dictId);

    public SysCategory selectDeviceByName(String deviceName);



    public void deleteDeviceByIds(String ids);

    public int insertDevice(SysCategory dictType);


    public int updateDevice(SysCategory dictType);


}
