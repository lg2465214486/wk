package com.example.wk.controller;


import com.example.wk.config.JsonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

    /**
     * 充值
     * @return
     */
    @PostMapping("/recharge")
    public JsonResult recharge() {
        return new JsonResult("success");
    }
}
