package com.example.wk.controller;


import com.example.wk.config.AdminSession;
import com.example.wk.config.JsonResult;
import com.example.wk.pojo.LoginParam;
import com.example.wk.service.IWkUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pub")
public class LoginController {

    @Autowired
    private IWkUserService wkUserService;

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

}
