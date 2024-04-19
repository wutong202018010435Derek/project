package com.lynn.web.controller.system;

import com.lynn.admin.controller.RestControllerHelper;
import com.lynn.common.annotation.Log;
import com.lynn.common.core.controller.BaseController;
import com.lynn.common.core.domain.AjaxResult;
import com.lynn.common.core.page.TableDataInfo;
import com.lynn.common.enums.BusinessType;
import com.lynn.system.domain.SysBanner;
import com.lynn.system.domain.SysGoods;
import com.lynn.system.service.ISysBannerService;
import com.lynn.system.service.ISysGoodsService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 推荐信息
 */
@Controller
@RequestMapping("/system/banner")
public class BannerController extends BaseController {
    private String prefix = "system/banner";


    @Autowired
    private ISysBannerService bannerService;


    @RequiresPermissions("system:banner:view")
    @GetMapping()
    public String user() {
        return prefix + "/banner";
    }

    @RequiresPermissions("system:banner:view")
    @GetMapping("/refresh")
    public String refresh() {
        return prefix + "/data";
    }

    @RequiresPermissions("system:banner:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysBanner user) {
        startPage();


        List<SysBanner> list = bannerService.selectDataBy(user);
        return getDataTable(list);
    }

    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }


    @RequiresPermissions("system:banner:remove")
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        bannerService.deleteByIds(ids);
        return success();
    }


    @Value("${spring.file.dir}")
    private String localFileDir;

    @Value("${spring.file.path}")
    private String localFilePath;


    /**
     * 日期处理
     * 文件上传
     *
     * @param book
     * @return
     */
    @PostMapping("/bannerAdd2")
    public String add(SysBanner book, MultipartFile pic) {
        System.out.println("============add()========");

        //获取上传文件的原始文件名
        String fileName = pic.getOriginalFilename();
        if (StringUtils.isBlank(fileName) || !fileName.contains(".")) {
            //空名或包含.不能添加
            throw new RuntimeException("文件名有误！");
        }
        // uuid生成文件
        String fileLastName = fileName.substring(fileName.lastIndexOf('.'));
        String localFileName = UUID.randomUUID() + fileLastName;
        //保存文件
//        FileUtils.saveFile(file, localFileDir + localFileName);

        File uploadPath = new File(localFileDir + localFileName);

//如果上传目录不存在，创建目录

        if (!uploadPath.exists()) {

            uploadPath.mkdirs();

        }

//上传文件
        try {
            pic.transferTo(uploadPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        book.setbImg(localFilePath.substring(1) + "/" + localFileName);
        bannerService.insertData(book);

//        return "redirect:system/banner/banner"; // /list
//        return helper.toJsonMap(); // /list
//        return toAjax(1); // /list

//        return prefix + "/add";
        return "redirect:/system/banner/refresh";
//        return prefix + "/refresh";

    }


}