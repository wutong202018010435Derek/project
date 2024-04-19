package com.lynn.web.controller.system;

import com.lynn.common.core.controller.BaseController;
import com.lynn.common.core.domain.AjaxResult;
import com.lynn.common.core.page.TableDataInfo;
import com.lynn.system.domain.SysLine;
import com.lynn.system.service.ISysBannerService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 推荐信息
 * 

 */
@Controller
@RequestMapping("/system/line2")
public class SysLine2Controller extends BaseController
{
//    private String prefix = "system/line";
//
//    @Autowired
//    private ISysBannerService userService;
//
//
//
//    @RequiresPermissions("system:line:view")
//    @GetMapping()
//    public String user()
//    {
//        return prefix + "/line2";
//    }
//
//    @RequiresPermissions("system:line:list")
//    @PostMapping("/list")
//    @ResponseBody
//    public TableDataInfo list(SysLine user)
//    {
//
//        startPage();
//
//        user.setpType(2);
//        List<SysLine> list = userService.selectDataBy(user);
//        return getDataTable(list);
//
//    }
//
//
//
//
//
//    /**
//     * 新增用户
//     */
//    @GetMapping("/add")
//    public String add(ModelMap mmap)
//    {
////        mmap.put("roles", roleService.selectRoleAll().stream().filter(r -> !r.isAdmin()).collect(Collectors.toList()));
////        mmap.put("posts", postService.selectPostAll());
//        return prefix + "/add2";
//    }
//
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
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//    @RequiresPermissions("system:line:remove")
//
//    @PostMapping("/remove")
//    @ResponseBody
//    public AjaxResult remove(String ids)
//    {
//
//        return toAjax(userService.deleteByIds(ids));
//    }


}