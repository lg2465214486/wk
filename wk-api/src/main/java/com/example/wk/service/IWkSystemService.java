package com.example.wk.service;

import com.example.wk.entity.WkSystem;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.wk.pojo.ExamineParam;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lg
 * @since 2023-11-20
 */
public interface IWkSystemService extends IService<WkSystem> {

    String editKeyValue(String key, String value);

    String topUpExamine(ExamineParam param);

    String withdrawExamine(ExamineParam param);
}
