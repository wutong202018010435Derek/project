package com.lynn.web.controller.system;

import com.lynn.common.annotation.Log;
import com.lynn.common.core.controller.BaseController;
import com.lynn.common.core.domain.AjaxResult;
import com.lynn.common.core.page.TableDataInfo;
import com.lynn.common.enums.BusinessType;
import com.lynn.system.domain.SysFeedback;
import com.lynn.system.domain.SysNotice;
import com.lynn.system.service.ISysFeedbackService;
import com.lynn.system.service.ISysNoticeService;
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
@RequestMapping("/system/feedback")
public class SysFeedbackController extends BaseController
{
    private String prefix = "system/feedback";

    @Autowired
    private ISysFeedbackService noticeService;

    @RequiresPermissions("system:feedback:view")
    @GetMapping()
    public String notice()
    {
        return prefix + "/feedback";
    }

    /**
     * 查询列表
     */
    @RequiresPermissions("system:feedback:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysFeedback notice)
    {
        startPage();
        notice.setFbType(1);
        List<SysFeedback> list = noticeService.selectList(notice);
        return getDataTable(list);
    }

    /**
     * 新增公告
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存公告
     */
    @RequiresPermissions("system:notice:add")

    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated SysFeedback notice)
    {
        notice.setCreateBy(getLoginName());
        return toAjax(noticeService.insertFeedback(notice));
    }

    /**
     * 修改公告
     */
    @RequiresPermissions("system:notice:edit")
    @GetMapping("/edit/{noticeId}")
    public String edit(@PathVariable("noticeId") Long noticeId, ModelMap mmap)
    {
        mmap.put("notice", noticeService.selectById(noticeId));
        return prefix + "/edit";
    }

    /**
     * 修改保存公告
     */
    @RequiresPermissions("system:notice:edit")
    @Log(title = "通知公告", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated SysFeedback notice)
    {
        notice.setUpdateBy(getLoginName());
        return toAjax(noticeService.updateFeedback(notice));
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
    @GetMapping("/detail/{fbId}")
    public String detail(@PathVariable("fbId") Long fbId, ModelMap mmap)
    {
        mmap.put("operLog", noticeService.selectById(fbId));

        return prefix + "/detail";
    }

}
