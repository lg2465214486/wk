package com.example.wk.controller;


import com.example.wk.config.AdminSession;
import com.example.wk.config.JsonResult;
import com.example.wk.pojo.MoneyOptionParam;
import com.example.wk.service.IWkUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IWkUserService userService;
    /**
     * 获取用户信息
     * @return
     */
    @GetMapping("/info")
    public JsonResult userInfo() {
        return new JsonResult(userService.getUserInfo());
    }

    /**
     * 交易记录
     * @return
     */
    @GetMapping("/deal/detail")
    public JsonResult transactionRecord() {
        return new JsonResult(userService.getTransactionRecordById(AdminSession.getInstance().admin().getId()));
    }

    /**
     * 充值
     * @return
     */
    @PostMapping("/topUp")
    public JsonResult topUp(@RequestBody MoneyOptionParam param) {
        return new JsonResult(userService.topUp(param));
    }

    /**
     * 提现
     * @return
     */
    @PostMapping("/withdraw")
    public JsonResult withdraw(@RequestBody MoneyOptionParam param) {
        return new JsonResult(userService.withdraw(param));
    }

}
