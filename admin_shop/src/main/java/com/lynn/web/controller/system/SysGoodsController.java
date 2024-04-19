package com.lynn.web.controller.system;

import com.lynn.common.annotation.Log;
import com.lynn.common.core.controller.BaseController;
import com.lynn.common.core.domain.AjaxResult;
import com.lynn.common.core.domain.entity.SysUser;
import com.lynn.common.core.page.TableDataInfo;
import com.lynn.common.enums.BusinessType;
import com.lynn.system.domain.SysGoods;
import com.lynn.system.domain.SysLine;
import com.lynn.system.service.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 推荐信息
 * 

 */
@Controller
@RequestMapping("/system/goods")
public class SysGoodsController extends BaseController
{
    private String prefix = "system/goods";

    @Autowired
    private ISysGoodsService goodsService;



    @RequiresPermissions("system:line:view")
    @GetMapping()
    public String user()
    {
        return prefix + "/goods";
    }

    @RequiresPermissions("system:goods:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysGoods user)
    {
        startPage();


        List<SysGoods> list = goodsService.selectDataList(user);
        return getDataTable(list);
    }



    @RequiresPermissions("system:goods:edit")
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(SysGoods user)
    {

        return toAjax(goodsService.updateDevice(user));
    }



    /**
     * 新增用户
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
//        mmap.put("roles", roleService.selectRoleAll().stream().filter(r -> !r.isAdmin()).collect(Collectors.toList()));
//        mmap.put("posts", postService.selectPostAll());
        return prefix + "/add";
    }

//    /**
//     * 新增保存用户
//     */
//    @RequiresPermissions("system:line:add")
//
//    @PostMapping("/add")
//    @ResponseBody
//    public AjaxResult addSave(@Validated SysLine user)
//    {
//
//        return toAjax(userService.insertData(user));
//    }


    @RequiresPermissions("system:goods:remove")

    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        goodsService.deleteDeviceByIds(ids);
        return success();
    }


}