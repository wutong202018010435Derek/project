package com.lynn.system.service.impl;


import com.lynn.common.utils.StringUtils;
import com.lynn.system.domain.SysBanner;
import com.lynn.system.mapper.SysYueMapper;
import com.lynn.system.service.ISysYueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  业务层处理
 *
 * @author ruoyi
 */
@Service
public class SysYueServiceImpl implements ISysYueService {
    @Autowired
    private SysYueMapper sysLineMapper;



    @Override
    public List<SysBanner> selectDataAll() {

        List<SysBanner> dictDatas = sysLineMapper.selectDataAll();
        if (StringUtils.isNotEmpty(dictDatas)) {

            return dictDatas;
        }
        return null;
    }



    @Override
    public List<SysBanner> selectDataByBean(SysBanner sysLine) {
        return sysLineMapper.selectDataByBean(sysLine);
    }



    @Override
    public int insertData(SysBanner sysLine) {
        return sysLineMapper.insertData(sysLine);
    }

    @Override
    public int deleteByIds(String ids) {
        return sysLineMapper.deleteByIds(ids);
    }

    @Override
    public SysBanner selectDataById(SysBanner sysLine) {
        return sysLineMapper.selectDataById(sysLine);
    }

    @Override
    public int updateData(SysBanner sysLine) {
        return sysLineMapper.updateData(sysLine);
    }


}
