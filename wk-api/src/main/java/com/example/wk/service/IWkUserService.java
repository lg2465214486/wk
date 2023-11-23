package com.example.wk.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.wk.config.JsonResult;
import com.example.wk.entity.WkUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.wk.pojo.*;
import com.example.wk.pojo.dto.DealDetail;
import com.example.wk.pojo.dto.UserInfo;

import java.util.List;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lg
 * @since 2023-11-20
 */
public interface IWkUserService extends IService<WkUser> {

    JsonResult userLogin(LoginParam login);

    Page<WkUser> findUserList(ListParam param);

    JsonResult addUser(UserParam param);

    List<DealDetail> getTransactionRecordById(Integer id);

    UserInfo getUserInfo();

    JsonResult editUser(UserParam param);

    String topUp(MoneyOptionParam param);

    String withdraw(MoneyOptionParam param) throws Exception;

    String conversion(ConversionParam param) throws Exception;
}
