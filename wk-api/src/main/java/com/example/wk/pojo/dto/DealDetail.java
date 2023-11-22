package com.example.wk.pojo.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * wenhui.xiang
 * 2023/11/21 10:59 下午
 */

@Data
public class DealDetail implements Comparable<DealDetail>{
    String time;
    String type; // 1:充值 0:提现
    String amount;
    String status;

    LocalDateTime localDateTime;

    @Override
    public int compareTo(DealDetail d) {
        return this.localDateTime.compareTo(d.getLocalDateTime());
    }
}
