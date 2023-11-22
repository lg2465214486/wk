package com.example.wk.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.wk.entity.WkSystem;
import com.example.wk.mapper.WkSystemMapper;
import com.example.wk.service.IWkSystemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
