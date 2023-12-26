package com.example.wk.controller;

import com.example.wk.config.JsonResult;
import com.example.wk.config.JsonResultUtil;
import com.example.wk.entity.WkVip;
import com.example.wk.mapper.WkVipMapper;
import com.example.wk.pojo.ExamineParam;
import com.example.wk.pojo.SysEditParam;
import com.example.wk.pojo.ListParam;
import com.example.wk.pojo.UserParam;
import com.example.wk.service.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/qwertyuiop")
public class AdminController {


    @Autowired
    private IWkUserService userService;
    @Autowired
    private IWkSystemService systemService;
    @Autowired
    private IWkTopUpService topUpService;
    @Autowired
    private IWkUnderwayService underwayService;
    @Autowired
    private IWkWithdrawService withdrawService;
    @Autowired
    private CommonService commonService;
    /**
     * 用户列表
     * @return
     */
    @GetMapping("/user/all")
    public JsonResult userList(ListParam param) {
        return new JsonResult(userService.findUserList(param));
    }
    /**
     * 禁用
     * @return
     */
    @GetMapping("/user/stop")
    public JsonResult userStatus(String uuid, int isStop) {
        return new JsonResult(userService.stopOrOpenUser(uuid, isStop));
    }

    /**
     * 充值列表
     * @return
     */
    @GetMapping("/topUp/all")
    public JsonResult topUpList(ListParam param) {
        return new JsonResult(topUpService.findTopUpList(param));
    }

    /**
     * 提现列表
     * @return
     */
    @GetMapping("/withdraw/all")
    public JsonResult withdrawList(ListParam param) {
        return new JsonResult(withdrawService.findWithdrawList(param));
    }

    /**
     * 充值审批
     * @return
     */
    @GetMapping("/topUp/examine")
    public JsonResult topUpExamine(ExamineParam param) {
        return new JsonResult(systemService.topUpExamine(param));
    }

    /**
     * 提现审批
     * @return
     */
    @GetMapping("/withdraw/examine")
    public JsonResult withdrawExamine(ExamineParam param) {
        return new JsonResult(systemService.withdrawExamine(param));
    }

    /**
     * 新增用户
     * @param param
     * @return
     */
    @PostMapping("/user/add")
    public JsonResult addUser(@RequestBody UserParam param) {
        return userService.addUser(param);
    }

    /**
     * 修改用户
     * @param param
     * @return
     */
    @PostMapping("/user/edit")
    public JsonResult editUser(@RequestBody UserParam param) {
        return userService.editUser(param);
    }

    @Autowired
    private WkVipMapper wkVipMapper;

    /**
     * vip列表
     * @return
     */
    @GetMapping("/user/vipList")
    public JsonResult vipList() {
        return JsonResultUtil.success(wkVipMapper.selectList(null));
    }

    /**
     * vip列表
     * @return
     */
    @PostMapping("/user/vipEdit")
    public JsonResult vipEdit(@RequestBody VipEditParam vipEditParam) {
        WkVip wkVip = wkVipMapper.selectById(vipEditParam.getId());
        wkVip.setWkRate(vipEditParam.getVipRate());
        wkVipMapper.updateById(wkVip);
        return JsonResultUtil.success();
    }

    @Data
    public static class VipEditParam{
        Integer id;
        BigDecimal vipRate;
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
