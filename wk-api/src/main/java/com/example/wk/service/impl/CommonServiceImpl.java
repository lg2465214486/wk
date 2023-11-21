package com.example.wk.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.wk.entity.WkSystem;
import com.example.wk.mapper.WkSystemMapper;
import com.example.wk.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * wenhui.xiang
 * 2023/11/21 9:45 下午
 */

@Service
public class CommonServiceImpl implements CommonService {

    @Autowired
    private WkSystemMapper systemMapper;

    @Override
    public String getValueByKey(String key) {
        WkSystem v = systemMapper.selectOne(Wrappers.lambdaQuery(WkSystem.class).eq(WkSystem::getKey, key));
        return null == v ? "" : v.getSysValue();
    }
}
