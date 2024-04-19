package com.lynn.system.service.impl;


import com.lynn.common.constant.UserConstants;

import com.lynn.common.core.domain.entity.SysData;
import com.lynn.common.core.domain.entity.SysDictData;
import com.lynn.common.core.domain.entity.SysDictType;
import com.lynn.common.utils.DictUtils;
import com.lynn.common.utils.StringUtils;


import com.lynn.system.mapper.SysDataMapper;
import com.lynn.system.service.ISysDataService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *  业务层处理
 *
 * @author ruoyi
 */
@Service
public class SysDataServiceImpl implements ISysDataService {
    @Autowired
    private SysDataMapper sysDataMapper;


    /**
     * 项目启动时，初始化字典到缓存
     */
    @PostConstruct
    public void init() {

    }


    @Override
    public List<SysData> selectDataList(SysData sysData) {
        return sysDataMapper.selectSysDataList(sysData);
    }

    @Override
    public List<SysData> selectDataAll() {

        List<SysData> dictDatas = sysDataMapper.selectDataAll();
        if (StringUtils.isNotEmpty(dictDatas)) {

            return dictDatas;
        }
        return null;
    }

    @Override
    public SysData selectDataFirst(String deviceId) {
        return sysDataMapper.selectDataFirst(deviceId);
    }

    @Override
    public int insertDataType(SysData sysData) {
        int row = sysDataMapper.insertDataType(sysData);
        if (row > 0) {
//            DictUtils.setDictCache(dict.getDictType(), null);
        }
        return row;
    }


    public String transDictName(SysDictType dictType) {
        StringBuffer sb = new StringBuffer();
        sb.append("(" + dictType.getDictName() + ")");
        sb.append("&nbsp;&nbsp;&nbsp;" + dictType.getDictType());
        return sb.toString();
    }
}
