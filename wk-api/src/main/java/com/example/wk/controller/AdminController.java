package com.example.wk.controller;

import com.example.wk.config.JsonResult;
import com.example.wk.entity.WkUser;
import com.example.wk.pojo.UserListParam;
import com.example.wk.service.IWkUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/qwertyuiop")
public class AdminController {


    @Autowired
    private IWkUserService wkUserService;
    /**
     * 用户列表
     * @return
     */
    @GetMapping("/user/all")
    public JsonResult userList(UserListParam param) {
        return new JsonResult(wkUserService.findUserList(param));
    }

    /**
     * 新增用户
     * @param param
     * @return
     */
    @GetMapping("/user/add")
    public JsonResult addUser(WkUser param) {
        return wkUserService.addUser(param);
    }
}
