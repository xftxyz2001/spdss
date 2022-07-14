package com.xftxyz.smms.type;

import java.util.HashMap;
import java.util.Map;

public enum Limits {
    // 管理员：
    // 经理：
    // 进货员：
    // 销售员：
    ADMIN(),
    MANAGER(),
    PURCHASER(),
    SALER();

    private Map<Operatings, Boolean> limits;

    // 初始化权限
    private Limits(Operatings... operatings) {
        limits = new HashMap<>();
        for (Operatings o : operatings) {
            limits.put(o, true);
        }
    }

    // 判断是否具有某项权限
    public boolean hasLimit(Operatings o) {
        return limits.getOrDefault(o, false);
    }
}
