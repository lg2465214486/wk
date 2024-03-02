package com.example.wk.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.wk.config.AdminSession;
import com.example.wk.entity.WkSystem;
import com.example.wk.entity.WkUser;
import com.example.wk.mapper.WkSystemMapper;
import com.example.wk.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * .
 * 2023/11/21 9:45 下午
 */

@Service
public class CommonServiceImpl implements CommonService {

    @Autowired
    private WkSystemMapper systemMapper;

    @Override
    public String getValueByKey(String key) {
        WkUser wkUser = AdminSession.getInstance().admin();
        WkSystem v = systemMapper.selectOne(Wrappers.lambdaQuery(WkSystem.class).eq(WkSystem::getSysKey, key));
        String value = null == v ? "" : v.getSysValue();
        if (ObjectUtil.isNotEmpty(wkUser) && !"admin".equals(wkUser.getUserType())){
            switch (key){
                case "phone":
                    if (StrUtil.isNotEmpty(wkUser.getBtcAds()))
                        value = wkUser.getUstdAds();
                    break;
                case "qrCode":
                    if (StrUtil.isNotEmpty(wkUser.getBtcAds()))
                        value = wkUser.getUstdQrCode();
                    break;
                case "btcPhone":
                    if (StrUtil.isNotEmpty(wkUser.getBtcAds()))
                        value = wkUser.getBtcAds();
                    break;
                case "btcQrCode":
                    if (StrUtil.isNotEmpty(wkUser.getBtcAds()))
                        value = wkUser.getBtcQrCode();
                    break;
                case "ethPhone":
                    if (StrUtil.isNotEmpty(wkUser.getBtcAds()))
                        value = wkUser.getEthAds();
                    break;
                case "ethQrCode":
                    if (StrUtil.isNotEmpty(wkUser.getBtcAds()))
                        value = wkUser.getEthQrCode();
                    break;
            }
        }
        return value;
    }
}
