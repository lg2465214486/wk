package com.example.wk.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.wk.config.AdminSession;
import com.example.wk.entity.WkUnderway;
import com.example.wk.entity.WkUser;
import com.example.wk.mapper.WkUnderwayMapper;
import com.example.wk.mapper.WkUserMapper;
import com.example.wk.pojo.MiningParam;
import com.example.wk.pojo.dto.Earnings;
import com.example.wk.service.IWkUnderwayService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
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
    @Autowired
    private WkUserMapper userMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String start(MiningParam param) throws Exception{
        AdminSession session = AdminSession.getInstance();
        WkUser user = userMapper.selectById(session.admin().getId());
        if (user.getUstd().compareTo(new BigDecimal(param.getMoneyQuantity())) < 0)
            throw new Exception("not sufficient funds");
        user.setUstd(user.getUstd().subtract(new BigDecimal(param.getMoneyQuantity())));
        List<WkUnderway> underways = underwayMapper.selectList(Wrappers.lambdaQuery(WkUnderway.class)
                .eq(WkUnderway::getUserId, user.getId()).eq(WkUnderway::getStatus, 1));
        for (WkUnderway underway : underways) {
            this.stopUnderwayByEntity(underway);
        }
        WkUnderway wkUnderway = new WkUnderway();
        wkUnderway.setUserId(user.getId());
        wkUnderway.setMoneyQuantity(new BigDecimal(param.getMoneyQuantity()));
        wkUnderway.setEarnings(BigDecimal.ZERO);
        wkUnderway.setStatus(1);
        wkUnderway.setStartDate(LocalDateTime.now());
        wkUnderway.setEndDate(null);
        wkUnderway.setCreatedDate(LocalDateTime.now());
        wkUnderway.setUpdatedDate(LocalDateTime.now());
        
        userMapper.updateById(user);
        session.updateAdmin(user);
        underwayMapper.insert(wkUnderway);
        return "success";
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String stop() {
        AdminSession session = AdminSession.getInstance();
        WkUser user = userMapper.selectById(session.admin().getId());
        List<WkUnderway> underways = underwayMapper.selectList(Wrappers.lambdaQuery(WkUnderway.class)
                .eq(WkUnderway::getUserId, user.getId()).eq(WkUnderway::getStatus, 1));
        BigDecimal earnings = BigDecimal.ZERO;
        for (WkUnderway underway : underways) {
            earnings = earnings.add(this.stopUnderwayByEntity(underway)).add(underway.getMoneyQuantity());
        }

        user.setUstd(user.getUstd().add(earnings));
        userMapper.updateById(user);
        session.updateAdmin(user);
        return "success";
    }

    private BigDecimal stopUnderwayById(Integer id) {
        WkUnderway underway = underwayMapper.selectById(id);
        return this.stopUnderwayByEntity(underway);
    }

    private BigDecimal stopUnderwayByEntity(WkUnderway underway) {
        LocalDateTime now = LocalDateTime.now();
        underway.setStatus(2);
        underway.setEarnings(this.earningsCoefficient(underway.getStartDate(), now, underway.getMoneyQuantity()));
        underway.setEndDate(now);
        underway.setUpdatedDate(now);
        underwayMapper.updateById(underway);
        return underway.getEarnings();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Earnings findEarnings() {
        WkUser u = AdminSession.getInstance().admin();
        WkUnderway underway = underwayMapper.selectOne(Wrappers.lambdaQuery(WkUnderway.class)
                .eq(WkUnderway::getUserId, u.getId())
                .eq(WkUnderway::getStatus, 1));
        if (null == underway)
            return null;
        underway.setEarnings(this.earningsCoefficient(underway.getStartDate(), LocalDateTime.now(), underway.getMoneyQuantity()));
        underwayMapper.updateById(underway);
        Earnings earnings = new Earnings();
        earnings.setEarnings(underway.getEarnings().setScale(4, RoundingMode.HALF_UP).toPlainString());
        earnings.setMoneyQuantity(underway.getMoneyQuantity().setScale(4, RoundingMode.HALF_UP).toPlainString());
        return earnings;
    }

    private BigDecimal earningsCoefficient(LocalDateTime start, LocalDateTime end, BigDecimal sales) {
        //获取秒数
        long nowSecond = start.toEpochSecond(ZoneOffset.ofHours(0));
        long endSecond = end.toEpochSecond(ZoneOffset.ofHours(0));
        long absSeconds = Math.abs(nowSecond - endSecond);
        BigDecimal daySales = sales.multiply(new BigDecimal("0.10")).setScale(4,BigDecimal.ROUND_HALF_UP);
        BigDecimal secondSales = daySales.divide(BigDecimal.valueOf(86400), 4,BigDecimal.ROUND_HALF_UP);
        return secondSales.multiply(BigDecimal.valueOf(absSeconds)).setScale(4,BigDecimal.ROUND_HALF_UP);
    }
}
