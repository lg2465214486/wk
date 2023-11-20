package com.example.wk.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
public class WkUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 登录私钥
     */
    private String uuid;

    /**
     * 密码
     */
    private String pwd;

    /**
     * 账户名
     */
    private String userName;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 邮箱
     */
    private String userEmail;

    private BigDecimal ustd;

    private BigDecimal btc;

    private BigDecimal eth;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    /**
     * 是否禁用  0：否  1：是
     */
    private Boolean isStop;


}
