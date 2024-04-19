package com.lynn.web.controller.system;

import com.lynn.common.annotation.Log;
import com.lynn.common.core.controller.BaseController;
import com.lynn.common.core.domain.AjaxResult;
import com.lynn.common.core.domain.entity.SysDevice;
import com.lynn.common.core.page.TableDataInfo;
import com.lynn.common.enums.BusinessType;
import com.lynn.mqtt.MqttConsumerClient;
import com.lynn.system.domain.SysCategory;
import com.lynn.system.service.ISysCategoryService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@Controller
@RequestMapping("/system/category")
public class SysCategoryController extends BaseController {
    private String prefix = "system/category";

    @Autowired
    private ISysCategoryService dictTypeService;

    @RequiresPermissions("system:category:view")
    @GetMapping()
    public String Device() {
        return prefix + "/data";
    }

    @PostMapping("/list")
    @RequiresPermissions("system:category:list")
    @ResponseBody
    public TableDataInfo list(SysCategory dictType) {
        startPage();
        List<SysCategory> list = dictTypeService.selectDeviceList(dictType);
        return getDataTable(list);
    }



    /**
     * 新增字典类型
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存字典类型
     */

    @RequiresPermissions("system:category:add")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated SysCategory dict) {

        dict.setCreateBy(getLoginName());
        return toAjax(dictTypeService.insertDevice(dict));
    }

    /**
     * 修改字典类型
     */
    @RequiresPermissions("system:category:edit")
    @GetMapping("/edit/{categoryId}")
    public String edit(@PathVariable("categoryId") Long dictId, ModelMap mmap) {
        mmap.put("dict", dictTypeService.selectDeviceById(dictId));
        return prefix + "/edit";
    }



    /**
     * 修改保存字典类型
     */
    @Log(title = "字典类型", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:category:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated SysCategory dict) {
        System.out.println("----进行保存操作");

        SysCategory md = dictTypeService.selectDeviceById(dict.getCategoryId());
//        if (md != null){
//            dict.setDictName(md.getDictName());
//            dict.setDictMqtt(md.getDictMqtt());
//        }
        dict.setUpdateBy(getLoginName());
//        mqttConsumerClient.pushMsg(MqttConsumerClient.changeToSendSet(1,dict));

        return toAjax(dictTypeService.updateDevice(dict));
    }

    @Log(title = "字典类型", businessType = BusinessType.DELETE)
    @RequiresPermissions("system:category:remove")
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        dictTypeService.deleteDeviceByIds(ids);
        return success();
    }



    /**
     * 查询字典详细
     */
    @RequiresPermissions("system:category:list")
    @GetMapping("/detail/{categoryId}")
    public String detail(@PathVariable("categoryId") Long dictId, ModelMap mmap) {
        mmap.put("dict", dictTypeService.selectDeviceById(dictId));
        mmap.put("dictList", dictTypeService.selectDeviceAll());
        return "system/category/data/data";
    }




}
