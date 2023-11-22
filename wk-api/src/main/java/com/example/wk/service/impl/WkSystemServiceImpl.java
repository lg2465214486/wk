package com.example.wk.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.wk.entity.WkSystem;
import com.example.wk.entity.WkTopUp;
import com.example.wk.entity.WkWithdraw;
import com.example.wk.mapper.WkSystemMapper;
import com.example.wk.mapper.WkTopUpMapper;
import com.example.wk.mapper.WkWithdrawMapper;
import com.example.wk.pojo.ExamineParam;
import com.example.wk.service.IWkSystemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lg
 * @since 2023-11-20
 */
@Service
public class WkSystemServiceImpl extends ServiceImpl<WkSystemMapper, WkSystem> implements IWkSystemService {

    @Autowired
    private WkSystemMapper systemMapper;
    @Autowired
    private WkTopUpMapper topUpMapper;
    @Autowired
    private WkWithdrawMapper withdrawMapper;

    @Transactional
    @Override
    public String editKeyValue(String key, String value) {
        WkSystem sys = systemMapper.selectOne(Wrappers.lambdaQuery(WkSystem.class).eq(WkSystem::getSysKey, key));
        if (null == sys) {
            sys = new WkSystem();
            sys.setSysKey(key);
            sys.setSysValue(value);
            systemMapper.insert(sys);
            return "success";
        }
        sys.setSysValue(value);
        systemMapper.updateById(sys);
        return "success";
    }

    @Transactional
    @Override
    public String topUpExamine(ExamineParam param) {
        WkTopUp wkTopUp = topUpMapper.selectById(param.getId());
        wkTopUp.setUpdatedDate(LocalDateTime.now());
        wkTopUp.setStatus(param.getStatus());
        topUpMapper.updateById(wkTopUp);
        return "success";
    }

    @Transactional
    @Override
    public String withdrawExamine(ExamineParam param) {
        WkWithdraw wkWithdraw = withdrawMapper.selectById(param.getId());
        wkWithdraw.setUpdatedDate(LocalDateTime.now());
        wkWithdraw.setStatus(param.getStatus());
        withdrawMapper.updateById(wkWithdraw);
        return "success";
    }
}
