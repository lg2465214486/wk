package com.example.wk.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.wk.config.AdminSession;
import com.example.wk.config.JsonResult;
import com.example.wk.entity.WkUser;
import com.example.wk.mapper.WkUserMapper;
import com.example.wk.pojo.LoginParam;
import com.example.wk.pojo.ListParam;
import com.example.wk.service.IWkUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.wk.util.MyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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

    @Override
    public JsonResult userLogin(LoginParam login) {
        WkUser wkUser = userMapper.selectOne(Wrappers.lambdaQuery(WkUser.class).eq(WkUser::getUuid, login.getLoginName()));
        if (null == wkUser)
            return new JsonResult(500, "用户名密码错误");
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
    public JsonResult addUser(WkUser param) {
        if (null == param.getUserName() || null == param.getUserEmail() || null == param.getPhone() || null == param.getPwd())
            throw new RuntimeException("not null !!");
        List<String> uuids = userMapper.findExistUuid();
        String uuid = MyUtil.getUUID(uuids);

        param.setUuid(uuid);
        param.setIsStop(false);
        param.setBtc(BigDecimal.ZERO);
        param.setEth(BigDecimal.ZERO);
        param.setUstd(BigDecimal.ZERO);
        param.setCreatedDate(LocalDateTime.now());
        param.setUpdatedDate(LocalDateTime.now());
        int insert = userMapper.insert(param);
        if (insert != 1)
            return new JsonResult(500, "error");
        return new JsonResult("success");
    }
}
