package com.example.wk.pojo;

import lombok.Data;


/**
 * wenhui.xiang
 * 2023/11/21 11:33 下午
 */

@Data
public class UserParam {
    /**
     * 登录私钥
     */
    String uuid;

    /**
     * 密码
     */
    String pwd;

    /**
     * 账户名
     */
    String userName;

    /**
     * 手机号码
     */
    String phone;

    /**
     * 邮箱
     */
    String userEmail;

    String ustd;

    String btc;

    String eth;

    Boolean isStop;

}