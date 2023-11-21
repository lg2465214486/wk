package com.example.wk.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.wk.config.AdminSession;
import com.example.wk.config.JsonResult;
import com.example.wk.entity.WkTopUp;
import com.example.wk.entity.WkUser;
import com.example.wk.entity.WkWithdraw;
import com.example.wk.mapper.WkTopUpMapper;
import com.example.wk.mapper.WkUserMapper;
import com.example.wk.mapper.WkWithdrawMapper;
import com.example.wk.pojo.LoginParam;
import com.example.wk.pojo.ListParam;
import com.example.wk.pojo.UserParam;
import com.example.wk.pojo.dto.DealDetail;
import com.example.wk.pojo.dto.UserInfo;
import com.example.wk.service.IWkUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.wk.util.MyDateUtils;
import com.example.wk.util.MyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lg
 * @since 2023-11-20
 */
@Service
public class WkUserServiceImpl extends ServiceImpl<WkUserMapper, WkUser> implements IWkUserService {

    @Autowired
    private WkUserMapper userMapper;
    @Autowired
    private WkTopUpMapper topUpMapper;
    @Autowired
    private WkWithdrawMapper withdrawMapper;

    @Override
    public JsonResult userLogin(LoginParam login) {
        WkUser wkUser = userMapper.selectOne(Wrappers.lambdaQuery(WkUser.class).eq(WkUser::getUuid, login.getLoginName()));
        if (null == wkUser)
            return new JsonResult(500, "Username or password is incorrect.");
        String token = AdminSession.getInstance().setAdmin(wkUser);
        return new JsonResult(token);
    }

    @Override
    public Page<WkUser> findUserList(ListParam param) {
        Page<WkUser> page = new Page<>();
        page.setCurrent(param.getPageNo());
        page.setSize(param.getPageSize());
        LambdaQueryWrapper<WkUser> wrapper = Wrappers.lambdaQuery(WkUser.class);
        if (null != param.getLastKeywords())
            wrapper.like(WkUser::getUserEmail, param.getLastKeywords()).or().like(WkUser::getUserName, param.getLastKeywords());
        if (null != param.getFirstKeywords())
            wrapper.and(i -> i.like(WkUser::getUuid, param.getFirstKeywords()));
        Page<WkUser> wkUserPage = userMapper.selectPage(page, wrapper);
        return wkUserPage;
    }

    @Override
    public JsonResult addUser(UserParam param) {
        if (null == param.getUserName() || null == param.getUserEmail() || null == param.getPhone() || null == param.getPwd())
            throw new RuntimeException("not null !!");
        List<String> uuids = userMapper.findExistUuid();
        String uuid = MyUtil.getUUID(uuids);

        WkUser u = new WkUser();
        u.setUuid(uuid);
        u.setIsStop(false);
        u.setCreatedDate(LocalDateTime.now());
        u.setUpdatedDate(LocalDateTime.now());

        u.setUserEmail(param.getUserEmail());
        u.setUserName(param.getUserName());
        u.setPhone(param.getPhone());
        u.setPwd(param.getPwd());

        u.setBtc(null == param.getBtc()? BigDecimal.ZERO:new BigDecimal(param.getBtc()));
        u.setEth(null == param.getEth()? BigDecimal.ZERO:new BigDecimal(param.getEth()));
        u.setUstd(null == param.getUstd()? BigDecimal.ZERO:new BigDecimal(param.getUstd()));
        int insert = userMapper.insert(u);
        if (insert != 1)
            return new JsonResult(500, "error");
        return new JsonResult("success");
    }

    @Override
    public JsonResult editUser(UserParam param) {
        WkUser user = userMapper.selectOne(Wrappers.lambdaQuery(WkUser.class).eq(WkUser::getUuid, param.getUuid()));
        user.setUserName(param.getUserName());
        user.setUserEmail(param.getUserEmail());
        user.setPhone(param.getPhone());
        user.setPwd(param.getPwd());
        user.setIsStop(param.getIsStop());

        user.setEth(new BigDecimal(param.getEth()));
        user.setBtc(new BigDecimal(param.getBtc()));
        user.setUstd(new BigDecimal(param.getUstd()));

        userMapper.updateById(user);
        return new JsonResult("success");
    }

    @Override
    public List<DealDetail> getTransactionRecordById(Integer id) {
        List<DealDetail> details = new ArrayList<>();
        List<Object> dealList = new ArrayList<>();
        List<WkTopUp> topUps = topUpMapper.selectList(Wrappers.lambdaQuery(WkTopUp.class).eq(WkTopUp::getUserId, id));
        List<WkWithdraw> withdraws = withdrawMapper.selectList(Wrappers.lambdaQuery(WkWithdraw.class).eq(WkWithdraw::getUserId, id));
        dealList.addAll(topUps);
        dealList.addAll(withdraws);
        for (Object o : dealList) {
            DealDetail detail = new DealDetail();
            if (o instanceof WkTopUp) {
                WkTopUp v = (WkTopUp) o;
                detail.setType("充值");
                detail.setAmount(v.getSales().setScale(4, RoundingMode.HALF_UP).toPlainString());
                detail.setTime(MyDateUtils.dateTimeFormat(v.getCreatedDate()));
                detail.setLocalDateTime(v.getCreatedDate());
            } else {
                WkWithdraw v = (WkWithdraw) o;
                detail.setType("提现");
                detail.setAmount(v.getSales().setScale(4, RoundingMode.HALF_UP).toPlainString());
                detail.setTime(MyDateUtils.dateTimeFormat(v.getCreatedDate()));
                detail.setLocalDateTime(v.getCreatedDate());
            }
            details.add(detail);
        }
        Collections.sort(details);
        return details;
    }

    @Override
    public UserInfo getUserInfo() {
        UserInfo userInfo = new UserInfo();
        WkUser wkUser = userMapper.selectById(AdminSession.getInstance().admin().getId());
        userInfo.setUserEmail(wkUser.getUserEmail());
        userInfo.setPhone(wkUser.getPhone());
        userInfo.setUserName(wkUser.getUserName());
        userInfo.setUuid(wkUser.getUuid());

        userInfo.setEth(wkUser.getEth().setScale(4, RoundingMode.HALF_UP).toPlainString());
        userInfo.setUstd(wkUser.getUstd().setScale(4, RoundingMode.HALF_UP).toPlainString());
        userInfo.setBtc(wkUser.getBtc().setScale(4, RoundingMode.HALF_UP).toPlainString());
        return userInfo;
    }

}
