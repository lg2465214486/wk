package com.example.wk.mapper;

import com.example.wk.entity.WkUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lg
 * @since 2023-11-20
 */
public interface WkUserMapper extends BaseMapper<WkUser> {

    @Select("select uuid from wk_user;")
    List<String> findExistUuid();

    @Select("select * from wk_user where uuid=#{uuid}")
    WkUser selectByUuid(String uuid);
}
