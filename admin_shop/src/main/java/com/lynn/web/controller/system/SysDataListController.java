package com.lynn.web.controller.system;

import com.lynn.common.annotation.Log;
import com.lynn.common.core.controller.BaseController;
import com.lynn.common.core.domain.AjaxResult;
import com.lynn.common.core.domain.Ztree;
import com.lynn.common.core.domain.entity.SysData;
import com.lynn.common.core.domain.entity.SysDictType;
import com.lynn.common.core.page.TableDataInfo;
import com.lynn.common.enums.BusinessType;
import com.lynn.system.service.ISysDataService;
import com.lynn.system.service.ISysDictTypeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 设备控制
 *
 * @author Abc
 */
@Controller
@RequestMapping("/system/datalist")
public class SysDataListController extends BaseController {

    private String prefix = "system/echarts/";

    @Autowired
    private ISysDataService dictTypeService;

    @RequiresPermissions("system:datalist:view")
    @GetMapping()
    public String dictType() {
        return prefix + "/datalist";
    }

    @PostMapping("/list")
    @RequiresPermissions("system:datalist:list")
    @ResponseBody
    public TableDataInfo list(SysData dictType) {
        startPage();
        List<SysData> list = dictTypeService.selectDataList(dictType);
        return getDataTable(list);
    }


    /**
     * 新增保存字典类型
     */
    @Log(title = "字典类型", businessType = BusinessType.INSERT)
    @RequiresPermissions("system:dict:add")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated SysData dict) {
        if (dictTypeService.insertDataType(dict) < 1) {
            return error("新增字典'" + dict.getDataName() + "'失败，字典类型已存在");
        }
        dict.setCreateBy(getLoginName());
        return toAjax(dictTypeService.insertDataType(dict));
    }





//    /**
//     * 查询字典详细
//     */
//    @RequiresPermissions("system:control:list")
//    @GetMapping("/detail/{dictId}")
//    public String detail(@PathVariable("dictId") Long dictId, ModelMap mmap) {
//        mmap.put("dict", dictTypeService.selectDictTypeById(dictId));
//        mmap.put("dictList", dictTypeService.selectDictTypeAll());
//        return "system/dict/data/data";
//    }


}
