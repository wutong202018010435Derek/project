package com.lynn.system.service;




import com.lynn.system.domain.OrderBean;


import java.util.List;

/**
 *  业务层
 * 

 */
public interface ISysOrderService
{

    public List<OrderBean> selectList(OrderBean dictType);

    public OrderBean  selectByid(Long oId);


//    public List<OrderBean> selectByTypeAndUser(int orderType,String userId);

    public int insertData(OrderBean dictType);

    /**
     * 删除订单
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteOrderByIds(String ids);


    public int updateData(OrderBean dictType);



}
