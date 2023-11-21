package com.example.wk.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.wk.entity.WkTopUp;
import com.example.wk.mapper.WkTopUpMapper;
import com.example.wk.pojo.ListParam;
import com.example.wk.service.IWkTopUpService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class WkTopUpServiceImpl extends ServiceImpl<WkTopUpMapper, WkTopUp> implements IWkTopUpService {

    @Override
    public Page<WkTopUp> findTopUpList(ListParam param) {
        return null;
    }
}
