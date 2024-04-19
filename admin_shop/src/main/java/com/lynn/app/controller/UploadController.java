package com.lynn.app.controller;


import com.lynn.admin.controller.RestControllerHelper;
import com.lynn.system.domain.SysAddress;
import com.lynn.system.domain.SysBanner;
import com.lynn.system.service.ISysBannerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class UploadController {


    @Value("${spring.file.dir}")
    private String localFileDir;

    @Value("${spring.file.path}")
    private String localFilePath;

    @Autowired
    private HttpServletRequest request;

    @PostMapping("/app/upload")
    public Map<String, String> uploadFileImg(@RequestParam("file") MultipartFile file) {
        Map<String, String> resultMap = new HashMap<>();
        //获取上传文件的原始文件名
        String fileName = file.getOriginalFilename();
        if (StringUtils.isBlank(fileName) || !fileName.contains(".")) {
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
            file.transferTo(uploadPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String requestName = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                + request.getContextPath();

        // 拼文件名
        resultMap.put("url", requestName + localFilePath + "/" + localFileName);
        return resultMap;
    }


//    @RequestMapping(value = "/uploadFile", produces = "application/json;charset=UTF-8")
//    @ResponseBody
//    public Map<String, Object> uploadFile(@RequestParam("fileName") MultipartFile file) {
//        RestControllerHelper helper = new RestControllerHelper();
//        helper.setCode(RestControllerHelper.FAIL_SIGN);
//        helper.setMsg("失败");
//
//        System.out.print("上传文件===" + "\n");
//        //判断文件是否为空
//        if (file.isEmpty()) {
//
//            helper.setMsg("上传文件不可为空");
//            return helper.toJsonMap();
//        }
//
//
//        // 获取文件名
//        String fileName = file.getOriginalFilename();
////        System.out.print("上传的文件名为: "+fileName+"\n");
//
//        fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + fileName;
//        System.out.print("（加个时间戳，尽量避免文件名称重复）保存的文件名为: " + fileName + "\n");
//
//
//        //加个时间戳，尽量避免文件名称重复
//        String path = localFileDir + fileName;
//        //String path = "E:/fileUpload/" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + fileName;
//        //文件绝对路径
//        System.out.print("保存文件绝对路径" + path + "\n");
//
//        //创建文件路径
//        File dest = new File(path);
//
//        //判断文件是否已经存在
//        if (dest.exists()) {
//
//            helper.setMsg("文件已经存在");
//            return helper.toJsonMap();
//        }
//
//        //判断文件父目录是否存在
//        if (!dest.getParentFile().exists()) {
//            dest.getParentFile().mkdir();
//        }
//
//        try {
//            //上传文件
//            file.transferTo(dest); //保存文件
//            System.out.print("保存文件路径" + path + "\n");
//            String url = "http://localhost:8080/images/" + fileName;
////            int jieguo= shiPinService.insertUrl(fileName,path,url);
////            System.out.print("插入结果"+jieguo+"\n");
//            BufferedImage image = ImageIO.read(new File(path));
//
//
//            System.out.print("保存的完整url====" + url + "\n");
//            helper.setCode(RestControllerHelper.SUCCESS);
//            helper.setMsg("恭喜添加成功");
//        } catch (IOException e) {
//
//            helper.setMsg("上传失败");
//            return helper.toJsonMap();
//        }
//
//        return helper.toJsonMap();
//    }

    @Autowired
    private ISysBannerService bannerService;

    //    @PostMapping("/system/banner/bannerAdd")
    @RequestMapping(value = "/system/banner/bannerAdd", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Map<String, Object> bannerAdd(SysBanner book, MultipartFile pic) {
        System.out.println("============add()========");

        RestControllerHelper helper = new RestControllerHelper();
        helper.setCode(RestControllerHelper.FAIL_SIGN);
        helper.setMsg("fail");

        //获取上传文件的原始文件名
        String fileName = pic.getOriginalFilename();
        if (StringUtils.isBlank(fileName) || !fileName.contains(".")) {
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

//        book.setbImg(localFilePath.substring(1) + "/" + localFileName);
        String requestName = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                + request.getContextPath();
        book.setbImg(requestName + localFilePath + "/" + localFileName);
        bannerService.insertData(book);
        helper.setCode(RestControllerHelper.SUCCESS);
        helper.setMsg("success");

//        return "redirect:system/banner/banner"; // /list
//        return helper.toJsonMap(); // /list
//        return toAjax(1); // /list

//        return prefix + "/add";
        return helper.toJsonMap();
//        return "ceshi";

    }


    @RequestMapping("/app/getScheme")
    public String addaddress(SysAddress sysAddress) {
        String requestName = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                + request.getContextPath()  +  "   ==:" + getIpAddr(request);
        return requestName;
    }
    @GetMapping("/app/testip")
    public String test() {
        String remoteAddr = request.getRemoteAddr();
        return "Request received from: " + remoteAddr;
    }


    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        return filterIp(ip);
    }

    private static String filterIp(String ip) {
        if (ip != null) {
            String[] data = ip.split(",");

            for(int i = 0; i < data.length; ++i) {
                if (!"unknown".equalsIgnoreCase(data[i].replaceAll("\\s*", ""))) {
                    ip = data[i].replaceAll("\\s*", "");
                    break;
                }
            }
        }

        return ip;
    }



}
