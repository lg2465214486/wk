package com.example.wk.controller;


import com.example.wk.config.AdminSession;
import com.example.wk.config.JsonResult;
import com.example.wk.pojo.LoginParam;
import com.example.wk.service.CommonService;
import com.example.wk.service.IWkUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pub")
public class LoginController {

    @Autowired
    private IWkUserService wkUserService;
    @Autowired
    private CommonService commonService;

    /**
     * 用户登录
     * @param login
     * @return
     */
    @PostMapping("/user/login")
    public JsonResult login(@RequestBody LoginParam login) {
        return wkUserService.userLogin(login);
    }

    /**
     * 用户登出
     * @return
     */
    @PostMapping("/user/logout")
    public JsonResult logout() {
        return new JsonResult(AdminSession.getInstance().logout());
    }

    /**
     * 获取值
     */
    @GetMapping("/getValue")
    public JsonResult getValue(String name) {
        return new JsonResult(commonService.getValueByKey(name));
    }

}
