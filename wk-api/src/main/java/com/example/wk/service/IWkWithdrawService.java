package com.example.wk.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.wk.entity.WkWithdraw;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.wk.pojo.ListParam;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lg
 * @since 2023-11-20
 */
public interface IWkWithdrawService extends IService<WkWithdraw> {

    Page<WkWithdraw> findWithdrawList(ListParam param);
}
