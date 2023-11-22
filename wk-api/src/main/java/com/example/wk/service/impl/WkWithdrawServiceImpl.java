package com.example.wk.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.wk.entity.WkWithdraw;
import com.example.wk.mapper.WkWithdrawMapper;
import com.example.wk.pojo.ListParam;
import com.example.wk.service.IWkWithdrawService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lg
 * @since 2023-11-20
 */
@Service
public class WkWithdrawServiceImpl extends ServiceImpl<WkWithdrawMapper, WkWithdraw> implements IWkWithdrawService {

    @Autowired
    private WkWithdrawMapper withdrawMapper;

    @Override
    public Page<WkWithdraw> findWithdrawList(ListParam param) {
        Page<WkWithdraw> page = new Page<>();
        page.setCurrent(param.getPageNo());
        page.setSize(param.getPageSize());
        return withdrawMapper.selectPageByParam(page, param);
    }
}
