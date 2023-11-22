package com.example.wk.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.wk.config.AdminSession;
import com.example.wk.entity.WkUnderway;
import com.example.wk.entity.WkUser;
import com.example.wk.mapper.WkUnderwayMapper;
import com.example.wk.pojo.MiningParam;
import com.example.wk.service.IWkUnderwayService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lg
 * @since 2023-11-20
 */
@Service
public class WkUnderwayServiceImpl extends ServiceImpl<WkUnderwayMapper, WkUnderway> implements IWkUnderwayService {

    @Autowired
    private WkUnderwayMapper underwayMapper;

    @Transactional
    @Override
    public String start(MiningParam param) {
        WkUser u = AdminSession.getInstance().admin();
        WkUnderway wkUnderway = new WkUnderway();
        wkUnderway.setUserId(u.getId());
        wkUnderway.setMoneyQuantity(new BigDecimal(param.getMoneyQuantity()));
        wkUnderway.setEarnings(BigDecimal.ZERO);
        wkUnderway.setStatus(1);
        wkUnderway.setStartDate(LocalDateTime.now());
        wkUnderway.setEndDate(null);
        wkUnderway.setCreatedDate(LocalDateTime.now());
        wkUnderway.setUpdatedDate(LocalDateTime.now());
        underwayMapper.insert(wkUnderway);
        return "success";
    }

    @Transactional
    @Override
    public String stop() {
        WkUser u = AdminSession.getInstance().admin();
        List<WkUnderway> underways = underwayMapper.selectList(Wrappers.lambdaQuery(WkUnderway.class)
                .eq(WkUnderway::getUserId, u.getId()).eq(WkUnderway::getStatus, 1));
        for (WkUnderway underway : underways) {
            LocalDateTime now = LocalDateTime.now();
            underway.setStatus(2);
            //todo: 收益计算
            underway.setEarnings(BigDecimal.ZERO);
            underway.setEndDate(now);
            underway.setUpdatedDate(now);
            underwayMapper.updateById(underway);
        }
        return "success";
    }
}
