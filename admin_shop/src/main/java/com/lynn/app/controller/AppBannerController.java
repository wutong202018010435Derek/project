package com.lynn.app.controller;


import com.lynn.admin.controller.RestControllerHelper;
import com.lynn.system.domain.OrderBean;
import com.lynn.system.domain.SysAddress;
import com.lynn.system.domain.SysBanner;
import com.lynn.system.service.ISysBannerService;
import com.lynn.system.service.ISysOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
public class AppBannerController {


    @Autowired
    private ISysOrderService orderService;

    @Autowired
    private ISysBannerService bannerService;


    @RequestMapping("/app/queryBanner")
    public Map<String, Object> addaddress(SysBanner sysBanner) {
        RestControllerHelper helper = new RestControllerHelper();
        helper.setCode(RestControllerHelper.FAIL_SIGN);
        helper.setMsg(RestControllerHelper.RESULT_DATA_FAIL);

        if (bannerService.selectDataBy(sysBanner).size() > 0) {
            helper.setCode(RestControllerHelper.SUCCESS);
            helper.setMsg(RestControllerHelper.RESULT_DATA_SUCCESS);
        } else {
            helper.setMsg(RestControllerHelper.RESULT_DATA_FAIL);
        }
        return helper.toJsonMap();
    }

    @RequestMapping("/app/queryAllBanner")
    public Map<String, Object> getMyOrder(HttpServletRequest request) {
//        String userId = request.getParameter("userId");
//        String sellerId = request.getParameter("sellerId");
        List<SysBanner> list;
        SysBanner sysBanner = new SysBanner();
//        if (StringUtils.hasText(userId))
//            sysYue.setoBuyid(userId);
//
//        if (StringUtils.hasText(sellerId))
//            sysYue.setoSellerid(sellerId);

        list = bannerService.selectDataBy(sysBanner);
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





}
