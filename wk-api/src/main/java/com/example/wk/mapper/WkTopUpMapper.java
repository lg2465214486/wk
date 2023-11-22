package com.example.wk.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.wk.entity.WkTopUp;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.wk.pojo.ListParam;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lg
 * @since 2023-11-20
 */
public interface WkTopUpMapper extends BaseMapper<WkTopUp> {

    Page<WkTopUp> selectPageByParam(Page<WkTopUp> page, ListParam param);
}
