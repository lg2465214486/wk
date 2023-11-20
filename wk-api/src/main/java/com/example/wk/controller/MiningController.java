package com.example.wk.controller;


import com.example.wk.config.JsonResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mining")
public class MiningController {

    /**
     * 取数查询
     * @return
     */
    @GetMapping("/total")
    public JsonResult totalMining() {
        return new JsonResult("success");
    }

    /**
     * 开始
     * @return
     */
    @PostMapping("/start")
    public JsonResult startMining() {
        return new JsonResult("success");
    }

    /**
     * 停止
     * @return
     */
    @PostMapping("/stop")
    public JsonResult stopMining() {
        return new JsonResult("success");
    }

}
