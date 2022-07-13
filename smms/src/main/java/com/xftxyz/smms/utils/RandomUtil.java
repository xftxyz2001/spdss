package com.xftxyz.smms.utils;

import java.util.Random;

public class RandomUtil {
    private static Random random = new Random();

    // 生成[min, max)之间的随机数
    public static int getInt(int min, int max) {
        return random.nextInt(max - min) + min;
    }

    // 随机选择一项
    public static <T> T choice(T[] arr) {
        return arr[getInt(0, arr.length)];
    }

    public static int choice(int[] arr) {
        return arr[getInt(0, arr.length)];
    }

    // 随机选择n个字符拼接并返回
    public static String choice(String[] arr, int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(choice(arr));
        }
        return sb.toString();
    }

}
