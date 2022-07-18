package com.xftxyz.smms.utils;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;

public class RandomUtil {
    private static Random random = new Random();

    // 生成[min, max)之间的随机数
    public static int getInt(int min, int max) {
        return random.nextInt(max - min) + min;
    }

    // 给定范围获得随机颜色
    public static Color getColor(int fc, int bc) {
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int[] rgb = new int[3];
        for (int i = 0; i < 3; i++) {
            rgb[i] = RandomUtil.getInt(fc, bc);
        }
        return new Color(rgb[0], rgb[1], rgb[2]);
    }

    public static Font getFont() {
        // 字体名称
        String[] fonts = { "宋体", "华文楷体", "黑体", "微软雅黑" };

        // 字体样式
        int[] fontStyles = { Font.HANGING_BASELINE, Font.ITALIC, Font.LAYOUT_LEFT_TO_RIGHT,
                Font.LAYOUT_NO_LIMIT_CONTEXT, Font.LAYOUT_NO_START_CONTEXT,
                Font.LAYOUT_RIGHT_TO_LEFT, Font.CENTER_BASELINE, Font.PLAIN, Font.ROMAN_BASELINE, Font.TRUETYPE_FONT,
                Font.TYPE1_FONT, Font.BOLD };
        return new Font(RandomUtil.choice(fonts), RandomUtil.choice(fontStyles), RandomUtil.getInt(21, 31));
    }

    // 随机选择一项
    public static <T> T choice(T[] arr) {
        return arr[getInt(0, arr.length)];
    }

    public static int choice(int[] arr) {
        return arr[getInt(0, arr.length)];
    }

    public static char choice(char[] arr) {
        return arr[getInt(0, arr.length)];
    }

    // 随机选择n个字符拼接并返回
    public static String choice(String arr, int n) {
        StringBuilder sb = new StringBuilder();
        char[] charArray = arr.toCharArray();
        for (int i = 0; i < n; i++) {
            sb.append(choice(charArray));
        }
        return sb.toString();
    }

}
