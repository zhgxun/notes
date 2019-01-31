package com.github.zhgxun.learn.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface Test {

    default List<Integer> allow() {
        new HashMap<Integer, Integer>() {
            {
                put(1, 2);
            }
        };
        return new ArrayList<Integer>() {
            {
                add(1);
                add(2);
            }
        };
    }
}
