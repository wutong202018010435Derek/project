package com.lynn.app.controller;


import com.lynn.admin.controller.RestControllerHelper;
import com.lynn.common.core.domain.entity.SysUser;
import com.lynn.common.utils.DateUtils;
import com.lynn.common.utils.ShiroUtils;
import com.lynn.framework.shiro.service.SysPasswordService;
import com.lynn.system.domain.SysGoods;
import com.lynn.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


//app登录使用
@RestController
public class AppLoginController {


    @Autowired
    ISysUserService mUserService;

    @Autowired
    private SysPasswordService passwordService;

    @RequestMapping("/app/register")
    public Map<String, Object> toRegisterUser(HttpServletRequest request) {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String nick = request.getParameter("nick");
        String user_type = request.getParameter("usertype");


        RestControllerHelper helper = new RestControllerHelper();
        helper.setCode(RestControllerHelper.FAIL_SIGN);
        helper.setMsg("fail");
        SysUser sysUser = new SysUser();
        if (StringUtils.hasText(username) && StringUtils.hasText(password)) {
            sysUser.setUserName(username);
            sysUser.setLoginName(nick);
            if (StringUtils.hasText(user_type) && user_type.equals("01"))
                sysUser.setUserType(user_type);
            SysUser userModel2 = mUserService.selectUserByLoginName(username);
            if (userModel2 == null) {

                if (!mUserService.checkLoginNameUnique(sysUser)) {
                    helper.setMsg("account already exist");
                    return helper.toJsonMap();
                }
                sysUser.setUserName(username);
                sysUser.setLoginName(username);
                sysUser.setRemark(nick);//车牌号
                sysUser.setSalt(ShiroUtils.randomSalt());
                sysUser.setPassword(passwordService.encryptPassword(sysUser.getLoginName(), password, sysUser.getSalt()));
                sysUser.setPwdUpdateDate(DateUtils.getNowDate());


                int row = mUserService.insertUser(sysUser);
                if (row > 0) {
                    helper.setCode(RestControllerHelper.SUCCESS);
                    helper.setData(sysUser);
                    helper.setMsg("success");
                }
            } else {
                helper.setMsg("account already exist 1");
            }

        } else {
            helper.setMsg("account and password can not be empty");
        }
        return helper.toJsonMap();
    }

    @RequestMapping("/app/login")
    public Map<String, Object> toLoginUser(HttpServletRequest request) {

        String userid = request.getParameter("username");
        String password = request.getParameter("password");
        RestControllerHelper helper = new RestControllerHelper();

        helper.setCode(RestControllerHelper.FAIL_SIGN);
        helper.setMsg("fail");
        if (StringUtils.hasText(userid) && StringUtils.hasText(password)) {


            SysUser userModel2 = mUserService.selectUserByLoginName(userid);
            if (userModel2 != null) {
                if (passwordService.encryptPassword(userModel2.getLoginName(), password, userModel2.getSalt()).equals(userModel2.getPassword())) {

                    helper.setCode(RestControllerHelper.SUCCESS);
                    helper.setData(userModel2);
                    helper.setMsg("success");
//					}

                } else {
                    helper.setCode(RestControllerHelper.SUCCESS_no_correct);
                    helper.setMsg("password error");
                }

            } else {

                helper.setMsg("login fail");
            }

        } else {
            helper.setMsg("account and password can not be empty");
        }
        return helper.toJsonMap();
    }


    @RequestMapping("/app/change")
    public Map<String, Object> tochange(HttpServletRequest request) {

        String userid = request.getParameter("username");
        String carNo = request.getParameter("carNo");
        RestControllerHelper helper = new RestControllerHelper();

        helper.setCode(RestControllerHelper.FAIL_SIGN);
        helper.setMsg("fail");
        if (StringUtils.hasText(userid) && StringUtils.hasText(carNo)) {


            SysUser userModel2 = mUserService.selectUserByLoginName(userid);
            if (userModel2 != null) {
                userModel2.setRemark(carNo);
                if (mUserService.updateUserInfo(userModel2) > 0) {

                    helper.setCode(RestControllerHelper.SUCCESS);
                    helper.setData(userModel2);
                    helper.setMsg("success");
//					}

                } else {
                    helper.setCode(RestControllerHelper.SUCCESS_no_correct);
                    helper.setMsg("操作失败");
                }

            } else {

                helper.setMsg("操作失败");
            }

        } else {
            helper.setMsg("操作失败");
        }
        return helper.toJsonMap();
    }



    @RequestMapping("/app/updataUser")
    public Map<String, Object> updataUser(SysUser sysGoods) {

        RestControllerHelper helper = new RestControllerHelper();
        helper.setCode(RestControllerHelper.FAIL_SIGN);
        helper.setMsg(RestControllerHelper.RESULT_DATA_FAIL);

        if (mUserService.updateUserInfo(sysGoods) > 0) {
            helper.setCode(RestControllerHelper.SUCCESS);
            helper.setMsg(RestControllerHelper.RESULT_DATA_SUCCESS);
        } else {
            helper.setMsg(RestControllerHelper.RESULT_DATA_FAIL);
        }
        return helper.toJsonMap();
    }


}

