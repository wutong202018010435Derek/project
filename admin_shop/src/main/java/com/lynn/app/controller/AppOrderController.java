package com.lynn.app.controller;


import com.lynn.admin.controller.RestControllerHelper;
import com.lynn.system.domain.OrderBean;
import com.lynn.system.service.ISysOrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
public class AppOrderController {


    @Autowired
    private ISysOrderService orderService;


    @RequestMapping("/app/addOrder")
    public Map<String, Object> addaddress(OrderBean orderBean) {
        RestControllerHelper helper = new RestControllerHelper();
        helper.setCode(RestControllerHelper.FAIL_SIGN);
        helper.setMsg(RestControllerHelper.RESULT_DATA_FAIL);
        orderBean.setoState(2);
        if (orderService.insertData(orderBean) > 0) {
            helper.setCode(RestControllerHelper.SUCCESS);
            helper.setMsg(RestControllerHelper.RESULT_DATA_SUCCESS);
        } else {
            helper.setMsg(RestControllerHelper.RESULT_DATA_FAIL);
        }
        return helper.toJsonMap();
    }

    @RequestMapping("/app/getMyOrder")
    public Map<String, Object> getMyOrder(HttpServletRequest request) {
        String userId = request.getParameter("userId");
        String sellerId = request.getParameter("sellerId");
        String oGoodsid = request.getParameter("oGoodsid");
        List<OrderBean> list;
        OrderBean sysYue = new OrderBean();
        if (StringUtils.hasText(userId))
            sysYue.setoBuyid(userId);

        if (StringUtils.hasText(sellerId))
            sysYue.setoSellerid(sellerId);

        if (StringUtils.hasText(oGoodsid))
            sysYue.setoGoodsid(oGoodsid);

        list = orderService.selectList(sysYue);
        RestControllerHelper helper = new RestControllerHelper();
        helper.setCode(RestControllerHelper.FAIL_SIGN);
        helper.setMsg(RestControllerHelper.RESULT_DATA_FAIL);
        if (list != null && list.size() > 0) {
            helper.setCode(RestControllerHelper.SUCCESS);
            helper.setData(list);
            helper.setMsg(RestControllerHelper.RESULT_DATA_SUCCESS);
        } else {
            helper.setMsg(RestControllerHelper.RESULT_DATA_FAIL);
        }
        return helper.toJsonMap();
    }

    @RequestMapping("/app/updateOrder")
    public Map<String, Object> updateOrder(HttpServletRequest request) {
        String ostate = request.getParameter("ostate");
        String ocomment = request.getParameter("ocomment");
        String oId = request.getParameter("oId");

        OrderBean orderBean = orderService.selectByid(Long.parseLong(oId));


        RestControllerHelper helper = new RestControllerHelper();
        helper.setCode(RestControllerHelper.FAIL_SIGN);
        helper.setMsg(RestControllerHelper.RESULT_DATA_FAIL);
        if (orderBean != null) {
            if (StringUtils.hasText(ostate))
                orderBean.setoState(Integer.parseInt(ostate));
            if (StringUtils.hasText(ocomment))
                orderBean.setoComment(ocomment);
            if (orderService.updateData(orderBean) > 0) {
//                orderBean.setoState(4);
                orderService.updateData(orderBean);
                helper.setCode(RestControllerHelper.SUCCESS);
                helper.setMsg(RestControllerHelper.RESULT_DATA_SUCCESS);
            }


        } else {
            helper.setMsg(RestControllerHelper.RESULT_DATA_FAIL);
        }
        return helper.toJsonMap();
    }


    @RequestMapping("/app/deleOrder")
    public Map<String, Object> deleaddress(HttpServletRequest request) {

        String aId = request.getParameter("oId");
        RestControllerHelper helper = new RestControllerHelper();
        helper.setCode(RestControllerHelper.FAIL_SIGN);
        helper.setMsg(RestControllerHelper.RESULT_DATA_FAIL);
        if (orderService.deleteOrderByIds(aId) > 0) {
            helper.setCode(RestControllerHelper.SUCCESS);

            helper.setMsg(RestControllerHelper.RESULT_DATA_SUCCESS);
        } else {
            helper.setMsg(RestControllerHelper.RESULT_DATA_FAIL);
        }
        return helper.toJsonMap();
    }


}
