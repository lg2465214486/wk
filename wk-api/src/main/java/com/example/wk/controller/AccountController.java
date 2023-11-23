package com.example.wk.controller;


import com.example.wk.config.JsonResult;
import com.example.wk.pojo.ConversionParam;
import com.example.wk.pojo.MoneyOptionParam;
import com.example.wk.service.IWkUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private IWkUserService userService;

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
    public JsonResult withdraw(@RequestBody MoneyOptionParam param) throws Exception {
        return new JsonResult(userService.withdraw(param));
    }

    /**
     * 闪兑
     * @return
     */
    @PostMapping("/conversion")
    public JsonResult conversion(@RequestBody ConversionParam param) throws Exception{
        return new JsonResult(userService.conversion(param));
    }
}
