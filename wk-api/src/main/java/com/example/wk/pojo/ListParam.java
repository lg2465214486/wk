package com.example.wk.pojo;

import lombok.Data;

@Data
public class ListParam {
    String firstKeywords;
    String lastKeywords;
    long pageNo;
    long pageSize;
}
