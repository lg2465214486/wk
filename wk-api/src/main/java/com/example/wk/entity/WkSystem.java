package com.example.wk.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author lg
 * @since 2023-11-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class WkSystem implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "sys_key", type = IdType.NONE)
    private String sysKey;

    private String sysValue;


}
