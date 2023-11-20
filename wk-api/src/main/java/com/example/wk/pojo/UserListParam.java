package com.example.wk.pojo;

import lombok.Data;

@Data
public class UserListParam {
    String keywords;
    long pageNo;

    long pageSize;
}
