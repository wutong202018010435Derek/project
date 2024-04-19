package com.lynn.system.mapper;

import com.lynn.system.domain.SysBanner;
import com.lynn.system.domain.SysLine;

import java.util.List;

public interface SysBannerMapper {


//    public List<SysBanner> selectDataAll();

    public List<SysBanner> selectDataBy(SysBanner sysLine);

    public int insertData(SysBanner sysLine);

    public int deleteByIds(String ids);

//    public SysBanner selectDataById(SysBanner sysLine);
}
