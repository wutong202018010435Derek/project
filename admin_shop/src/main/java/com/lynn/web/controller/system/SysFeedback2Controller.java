package com.lynn.web.controller.system;

import com.lynn.common.annotation.Log;
import com.lynn.common.core.controller.BaseController;
import com.lynn.common.core.domain.AjaxResult;
import com.lynn.common.core.page.TableDataInfo;
import com.lynn.common.enums.BusinessType;
import com.lynn.system.domain.SysFeedback;
import com.lynn.system.service.ISysFeedbackService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 意见反馈   信息操作处理
 * 

 */
@Controller
@RequestMapping("/system/feedback2")
public class SysFeedback2Controller extends BaseController
{
    private String prefix = "system/feedback";

    @Autowired
    private ISysFeedbackService noticeService;

    @RequiresPermissions("system:feedback2:view")
    @GetMapping()
    public String notice()
    {
        return prefix + "/feedback2";
    }

    /**
     * 查询列表
     */
    @RequiresPermissions("system:feedback2:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysFeedback notice)
    {
        startPage();
        notice.setFbType(2);
        List<SysFeedback> list = noticeService.selectList(notice);
        return getDataTable(list);
    }







    /**
     * 删除公告
     */
    @RequiresPermissions("system:notice:remove")
    @Log(title = "通知公告", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(noticeService.deleteNoticeByIds(ids));
    }

    @RequiresPermissions("system:feedback:detail")
    @GetMapping("/detail2/{fbId}")
    public String detail(@PathVariable("fbId") Long fbId, ModelMap mmap)
    {
        mmap.put("operLog", noticeService.selectById(fbId));

        return prefix + "/detail2";
    }

}
