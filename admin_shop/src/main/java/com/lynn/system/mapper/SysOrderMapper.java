package com.lynn.system.mapper;



import com.lynn.common.core.domain.entity.SysDictData;
import com.lynn.system.domain.OrderBean;
import com.lynn.system.domain.SysAddress;

import java.util.List;

/**
 * 字典表 数据层
 * 
 * @author ruoyi
 */
public interface SysOrderMapper
{


    public List<OrderBean> selectList(OrderBean orderBean);



    public int insertData(OrderBean dictType);

    public OrderBean selectDataById(Long dictId);

    public int deleteDataByIds(String[] noticeIds);


    public int updateData(OrderBean orderBean);


}
