package com.example.wk.controller;

import com.example.wk.config.JsonResult;
import com.example.wk.entity.WkUser;
import com.example.wk.pojo.SysEditParam;
import com.example.wk.pojo.UserListParam;
import com.example.wk.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/qwertyuiop")
public class AdminController {


    @Autowired
    private IWkUserService wkUserService;
    @Autowired
    private IWkSystemService systemService;
    @Autowired
    private IWkTopUpService topUpService;
    @Autowired
    private IWkUnderwayService underwayService;
    @Autowired
    private IWkWithdrawService wkWithdrawService;
    @Autowired
    private CommonService commonService;
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
    @PostMapping("/user/add")
    public JsonResult addUser(@RequestBody WkUser param) {
        return wkUserService.addUser(param);
    }

    /**
     * 获取值
     */
    @GetMapping("/getValue")
    public JsonResult getValue(String name) {
        return new JsonResult(commonService.getValueByKey(name));
    }

    /**
     * 系统变量编辑
     * @param param
     * @return
     */
    @PostMapping("/sys/edit")
    public JsonResult edit(@RequestBody SysEditParam param) {
        return new JsonResult(systemService.editKeyValue(param.getKey(), param.getValue()));
    }

}