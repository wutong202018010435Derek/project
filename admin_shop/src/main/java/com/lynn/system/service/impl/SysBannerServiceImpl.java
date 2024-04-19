package com.lynn.system.service.impl;


import com.lynn.common.utils.StringUtils;
import com.lynn.system.domain.SysBanner;
import com.lynn.system.domain.SysLine;
import com.lynn.system.mapper.SysBannerMapper;
import com.lynn.system.service.ISysBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  业务层处理
 *
 * @author ruoyi
 */
@Service
public class SysBannerServiceImpl implements ISysBannerService {
    @Autowired
    private SysBannerMapper sysBannerMapper;





    @Override
    public List<SysBanner> selectDataBy(SysBanner sysLine) {
        return sysBannerMapper.selectDataBy(sysLine);
    }



    @Override
    public int insertData(SysBanner sysLine) {
        return sysBannerMapper.insertData(sysLine);
    }

    @Override
    public int deleteByIds(String ids) {
        return sysBannerMapper.deleteByIds(ids);
    }




}
