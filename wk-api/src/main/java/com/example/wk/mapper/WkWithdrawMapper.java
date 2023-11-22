package com.example.wk.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.wk.entity.WkWithdraw;
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
public interface WkWithdrawMapper extends BaseMapper<WkWithdraw> {

    Page<WkWithdraw> selectPageByParam(Page<WkWithdraw> page, ListParam param);
}
