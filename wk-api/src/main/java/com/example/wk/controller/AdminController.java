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

    /**
     * 编辑简介
     * @param intro
     * @return
     */
    @PostMapping("/intro/edit")
    public JsonResult editIntro(@RequestBody String intro) {
        return new JsonResult(systemService.editKeyValue("intro", intro));
    }

    /**
     * 编辑TRC20
     * @param trc
     * @return
     */
    @PostMapping("/TRC20/edit")
    public JsonResult editTRC20(@RequestBody String trc) {
        return new JsonResult(systemService.editKeyValue("TRC20", trc));
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
