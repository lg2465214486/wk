package com.example.wk.service;

import com.example.wk.entity.WkUnderway;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.wk.pojo.MiningParam;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lg
 * @since 2023-11-20
 */
public interface IWkUnderwayService extends IService<WkUnderway> {

    String start(MiningParam param);

    String stop();

    String findEarnings();
}
