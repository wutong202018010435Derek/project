package com.lynn.app.controller;


import com.lynn.admin.controller.RestControllerHelper;
import com.lynn.common.core.domain.entity.SysData;
import com.lynn.common.core.domain.entity.SysDevice;
import com.lynn.common.core.domain.entity.SysDeviceData;
import com.lynn.common.core.domain.entity.SysDictType;
import com.lynn.system.domain.*;
import com.lynn.system.service.*;
import org.attoparser.util.TextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
public class AppDeviceController {


    @Autowired
    private ISysCategoryService categoryService;

    @Autowired
    private ISysGoodsService goodsService;

    @RequestMapping("/app/getCategory")
    public Map<String, Object> getCategory(HttpServletRequest request) {


        List<SysCategory> list;

        list = categoryService.selectDeviceAll();
        RestControllerHelper helper = new RestControllerHelper();
        helper.setCode(RestControllerHelper.FAIL_SIGN);
        helper.setMsg("fail");
        if (list != null && list.size() > 0) {
            helper.setCode(RestControllerHelper.SUCCESS);
            helper.setData(list);
            helper.setMsg("success");
        } else {
            helper.setMsg("no data");
        }
        return helper.toJsonMap();
    }

    @RequestMapping("/app/addGoods")
    public Map<String, Object> addGoods(SysGoods sysGoods) {

        RestControllerHelper helper = new RestControllerHelper();
        helper.setCode(RestControllerHelper.FAIL_SIGN);
        helper.setMsg(RestControllerHelper.RESULT_DATA_FAIL);

        if (goodsService.insertDevice(sysGoods) > 0) {
            helper.setCode(RestControllerHelper.SUCCESS);
            helper.setMsg(RestControllerHelper.RESULT_DATA_SUCCESS);
        } else {
            helper.setMsg(RestControllerHelper.RESULT_DATA_FAIL);
        }
        return helper.toJsonMap();
    }

    @RequestMapping("/app/getMyGoods")
    public Map<String, Object> getMyGoods(SysGoods sysGoods) {

        List<SysGoods> list;
//        if (StringUtils.hasText(sysGoods.getGoodsName()))
//            sysGoods.setGoodsName("%" +  sysGoods.getGoodsName()+ "%");//模拟

        System.out.println(sysGoods.getGoodsName());
       System.out.println(sysGoods.getGoodsState());

        list = goodsService.selectDataList(sysGoods);
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

    @RequestMapping("/app/updateGoods")
    public Map<String, Object> updateGoods(SysGoods sysGoods) {


        RestControllerHelper helper = new RestControllerHelper();
        helper.setCode(RestControllerHelper.FAIL_SIGN);
        helper.setMsg(RestControllerHelper.RESULT_DATA_FAIL);
        SysGoods sysGoods1 = goodsService.selectDeviceById(sysGoods.getGoodsId());
        sysGoods1.setGoodsState(sysGoods.getGoodsState());

        if (goodsService.updateDevice(sysGoods1) > 0) {
            helper.setCode(RestControllerHelper.SUCCESS);
//            helper.setData(list);
            helper.setMsg(RestControllerHelper.RESULT_DATA_SUCCESS);
        } else {
            helper.setMsg(RestControllerHelper.RESULT_DATA_FAIL);
        }
        return helper.toJsonMap();
    }

    @RequestMapping("/app/delGoods")
    public Map<String, Object> delGoods(SysGoods sysGoods) {


        RestControllerHelper helper = new RestControllerHelper();
        helper.setCode(RestControllerHelper.FAIL_SIGN);
        helper.setMsg(RestControllerHelper.RESULT_DATA_FAIL);
        SysGoods sysGoods1 = goodsService.selectDeviceById(sysGoods.getGoodsId());
//        sysGoods1.setGoodsState(sysGoods.getGoodsState());


        try {
            goodsService.deleteDeviceById(sysGoods.getGoodsId());
            helper.setCode(RestControllerHelper.SUCCESS);
//            helper.setData(list);
            helper.setMsg(RestControllerHelper.RESULT_DATA_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            helper.setMsg(RestControllerHelper.RESULT_DATA_FAIL);
        }


        return helper.toJsonMap();
    }


    @RequestMapping("/app/getGoodsWithCategory")
    public Map<String, Object> getGoodsWithCategory(SysGoods sysGoods) {

        List<SysGoods> list;

        list = goodsService.selectDataList(sysGoods);
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

    @Autowired
    private ISysAddressService addressService;

    @RequestMapping("/app/addaddress")
    public Map<String, Object> addaddress(SysAddress sysAddress) {
        RestControllerHelper helper = new RestControllerHelper();
        helper.setCode(RestControllerHelper.FAIL_SIGN);
        helper.setMsg(RestControllerHelper.RESULT_DATA_FAIL);

        if (addressService.insertData(sysAddress) > 0) {
            helper.setCode(RestControllerHelper.SUCCESS);
            helper.setMsg(RestControllerHelper.RESULT_DATA_SUCCESS);
        } else {
            helper.setMsg(RestControllerHelper.RESULT_DATA_FAIL);
        }
        return helper.toJsonMap();
    }

    @RequestMapping("/app/getaddress")
    public Map<String, Object> getaddress(HttpServletRequest request) {
        String userId = request.getParameter("userId");
        List<SysAddress> list;
        SysAddress sysYue = new SysAddress();
        sysYue.setaUserId(userId);
        list = addressService.selectDataList(sysYue);
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


    @RequestMapping("/app/deleaddress")
    public Map<String, Object> deleaddress(HttpServletRequest request) {

        String aId = request.getParameter("aId");
        RestControllerHelper helper = new RestControllerHelper();
        helper.setCode(RestControllerHelper.FAIL_SIGN);
        helper.setMsg(RestControllerHelper.RESULT_DATA_FAIL);
        if (addressService.deleteDataByIds(aId) > 0) {
            helper.setCode(RestControllerHelper.SUCCESS);

            helper.setMsg(RestControllerHelper.RESULT_DATA_SUCCESS);
        } else {
            helper.setMsg(RestControllerHelper.RESULT_DATA_FAIL);
        }
        return helper.toJsonMap();
    }


}
