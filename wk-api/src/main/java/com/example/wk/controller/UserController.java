package com.example.wk.controller;


import com.example.wk.config.AdminSession;
import com.example.wk.config.JsonResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    /**
     * 获取用户信息
     * @return
     */
    @GetMapping("/info")
    public JsonResult userInfo() {
        return new JsonResult(AdminSession.getInstance().admin());
    }

}
