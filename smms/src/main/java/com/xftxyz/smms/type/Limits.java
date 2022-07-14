package com.xftxyz.smms.type;

import java.util.HashSet;
import java.util.Set;

import com.xftxyz.smms.entity.User;

public enum Limits {
    // 管理员：
    // 经理：
    // 进货员：
    // 销售员：
    ADMIN(),
    MANAGER(),
    PURCHASER(),
    SALER();

    private Set<Operatings> limits;

    // 初始化权限
    private Limits(Operatings... operatings) {
        limits = new HashSet<>();
        for (Operatings o : operatings) {
            limits.add(o);
        }
    }

    // 判断是否具有某项权限
    public static boolean hasLimit(User u, Operatings o) {
        if (u == null) {
            return false;
        }
        String limits = u.getLimits();
        if (limits == null || limits.trim().equals("")) {
            return false;
        }
        Limits l = Limits.valueOf(limits);
        return l.limits.contains(o);
    }

}
