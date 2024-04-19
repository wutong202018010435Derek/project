package com.lynn.web.controller.system;

import com.lynn.common.annotation.Log;
import com.lynn.common.core.controller.BaseController;
import com.lynn.common.core.domain.AjaxResult;
import com.lynn.common.core.domain.Ztree;
import com.lynn.common.core.domain.entity.SysControl;
import com.lynn.common.core.domain.entity.SysDictType;
import com.lynn.common.core.page.TableDataInfo;
import com.lynn.common.enums.BusinessType;
import com.lynn.system.service.ISysDictTypeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 阈值设置
 *
 * @author Abc
 */
@Controller
@RequestMapping("/system/setting")
public class SysSettingController extends BaseController {

    private String prefix = "system/control/setting";

    @Autowired
    private ISysDictTypeService dictTypeService;

    @RequiresPermissions("system:control:view")
    @GetMapping()
    public String dictType()
    {
        return prefix + "/type";
    }

    @GetMapping("/set")
    public String toControl() {
        return prefix + "/setting";
    }

    @PostMapping("/list")
    @RequiresPermissions("system:control:list")
    @ResponseBody
    public TableDataInfo list(SysDictType dictType)
    {
        startPage();
        List<SysDictType> list = dictTypeService.selectDictTypeList(dictType);
        return getDataTable(list);
    }



//    /**
//     * 新增保存字典类型
//     */
//    @Log(title = "字典类型", businessType = BusinessType.INSERT)
//    @RequiresPermissions("system:dict:add")
//    @PostMapping("/add")
//    @ResponseBody
//    public AjaxResult addSave(@Validated SysDictType dict)
//    {
//        if (!dictTypeService.checkDictTypeUnique(dict))
//        {
//            return error("新增字典'" + dict.getDictName() + "'失败，字典类型已存在");
//        }
//        dict.setCreateBy(getLoginName());
//        return toAjax(dictTypeService.insertDictType(dict));
//    }

    /**
     * 修改字典类型
     */
    @RequiresPermissions("system:dict:edit")
    @GetMapping("/edit/{dictId}")
    public String edit(@PathVariable("dictId") Long dictId, ModelMap mmap)
    {
        mmap.put("dict", dictTypeService.selectDictTypeById(dictId));
        return prefix + "/edit";
    }

    /**
     * 修改保存字典类型
     */
    @Log(title = "字典类型", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:dict:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated SysDictType dict)
    {
        if (!dictTypeService.checkDictTypeUnique(dict))
        {
            return error("修改字典'" + dict.getDictName() + "'失败，字典类型已存在");
        }
        dict.setUpdateBy(getLoginName());
        return toAjax(dictTypeService.updateDictType(dict));
    }



    /**
     * 刷新字典缓存
     */
    @RequiresPermissions("system:control:remove")
    @Log(title = "字典类型", businessType = BusinessType.CLEAN)
    @GetMapping("/refreshCache")
    @ResponseBody
    public AjaxResult refreshCache()
    {
        dictTypeService.resetDictCache();
        return success();
    }

    /**
     * 查询字典详细
     */
    @RequiresPermissions("system:control:list")
    @GetMapping("/detail/{dictId}")
    public String detail(@PathVariable("dictId") Long dictId, ModelMap mmap)
    {
        mmap.put("dict", dictTypeService.selectDictTypeById(dictId));
        mmap.put("dictList", dictTypeService.selectDictTypeAll());
        return "system/dict/data/data";
    }





}
