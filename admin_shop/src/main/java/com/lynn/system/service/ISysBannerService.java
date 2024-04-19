package com.lynn.system.service;



import com.lynn.system.domain.SysBanner;
import com.lynn.system.domain.SysLine;

import java.util.List;

/**
 * 设备 业务层
 * 

 */
public interface ISysBannerService
{





    public List<SysBanner> selectDataBy(SysBanner sysLine);



    public int insertData(SysBanner sysLine);


    public int deleteByIds(String ids);



}
