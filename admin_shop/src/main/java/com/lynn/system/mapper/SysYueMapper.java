package com.lynn.system.mapper;

import com.lynn.system.domain.SysBanner;

import java.util.List;

public interface SysYueMapper {


    public List<SysBanner> selectDataAll();



    public List<SysBanner> selectDataByBean(SysBanner sysLine);



    public int insertData(SysBanner sysLine);

    public int deleteByIds(String ids);

    public SysBanner selectDataById(SysBanner sysLine);

    public int updateData(SysBanner sysLine);
}
