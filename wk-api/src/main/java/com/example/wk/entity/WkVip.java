package com.example.wk.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Author：luogan
 * DATE：2023-12-2023/12/19 20:40
 * Description：<描述>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class WkVip implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String vipName;

    private BigDecimal wkRate;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;
}
