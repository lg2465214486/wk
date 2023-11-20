package com.example.wk.util;

import java.util.*;

public class MyTest {
    public static void main(String[] args) {
        List<String> uuids = Arrays.asList("121212123");
        String uuid;
        do {
            int i = new Random().nextInt(9999999);
            while (i < 1000001) {
                i = new Random().nextInt(9999999);
            }
            uuid = i + "";
        } while (uuids.contains(uuid));
        System.out.println(uuid);
    }
}
