package com.example.wk.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.wk.config.AdminSession;
import com.example.wk.entity.WkSystem;
import com.example.wk.entity.WkTopUp;
import com.example.wk.entity.WkUser;
import com.example.wk.entity.WkWithdraw;
import com.example.wk.mapper.WkSystemMapper;
import com.example.wk.mapper.WkTopUpMapper;
import com.example.wk.mapper.WkUserMapper;
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
    @Autowired
    private WkUserMapper userMapper;

    @Transactional(rollbackFor = Exception.class)
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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String topUpExamine(ExamineParam param) {
        WkTopUp wkTopUp = topUpMapper.selectById(param.getId());
        wkTopUp.setUpdatedDate(LocalDateTime.now());
        wkTopUp.setStatus(param.getStatus());
        topUpMapper.updateById(wkTopUp);
        return "success";
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String withdrawExamine(ExamineParam param) {
        WkWithdraw wkWithdraw = withdrawMapper.selectById(param.getId());
        wkWithdraw.setUpdatedDate(LocalDateTime.now());
        wkWithdraw.setStatus(param.getStatus());
        withdrawMapper.updateById(wkWithdraw);
        WkUser wkUser = userMapper.selectById(wkWithdraw.getUserId());
        if (param.getStatus() == 3)
            wkUser.setUstd(wkUser.getUstd().add(wkWithdraw.getSales()));
            userMapper.updateById(wkUser);
        if (StrUtil.isNotEmpty(wkUser.getToken()))
            AdminSession.getInstance().updateAdmin(wkUser.getToken(),wkUser);
        return "success";
    }
}
