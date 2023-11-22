package com.example.wk.controller;


import com.example.wk.config.JsonResult;
import com.example.wk.pojo.MiningParam;
import com.example.wk.service.IWkUnderwayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mining")
public class MiningController {

    @Autowired
    private IWkUnderwayService underwayService;
    /**
     * 总收益查询
     * @return
     */
    @GetMapping("/total")
    public JsonResult totalMining() {
        return new JsonResult("success");
    }

    /**
     * 收益查询
     * @return
     */
    @GetMapping("/earnings")
    public JsonResult findEarnings() {
        return new JsonResult("success");
    }

    /**
     * 开始
     * @return
     */
    @PostMapping("/start")
    public JsonResult startMining(@RequestBody MiningParam param) {
        return new JsonResult(underwayService.start(param));
    }

    /**
     * 停止
     * @return
     */
    @PostMapping("/stop")
    public JsonResult stopMining() {
        return new JsonResult(underwayService.stop());
    }

}
