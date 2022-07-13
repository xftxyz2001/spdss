package com.xftxyz.smms.utils;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.QuadCurve2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

import javafx.scene.image.Image;

public class CodeUtil {

    private static final String codechars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    // 画随机码图
    private static BufferedImage draw(String value) {
        int width = 110, height = 40;
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = (Graphics2D) bi.getGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(RandomUtil.getColor(240, 255));
        g.fillRect(0, 0, width, height);
        g.drawRect(1, 1, width - 2, height - 2);
        for (int i = 0; i < 10; i++) {
            g.setColor(RandomUtil.getColor(150, 250));
            g.drawOval(RandomUtil.getInt(0, width), RandomUtil.getInt(0, 24),
                    RandomUtil.getInt(5, 10), RandomUtil.getInt(5, 10));
        }
        g.setFont(RandomUtil.getFont());
        g.setColor(RandomUtil.getColor(RandomUtil.getInt(19, 25), 254));
        for (int i = 0, len = value.length(); i < len; i++) {
            String rand = String.valueOf(value.charAt(i));
            int degree = RandomUtil.getInt(0, 23);
            degree = i % 2 == 0 ? -degree : degree;
            int x = RandomUtil.getInt(19, 25) * i;
            int y = RandomUtil.getInt(16, 25);
            g.rotate(Math.toRadians(degree), x, y);
            g.setColor(RandomUtil.getColor(48, 254));
            g.drawString(rand, x + 8, y + 10);
            g.rotate(-Math.toRadians(degree), x, y);
        }
        // 图片中间线
        g.setColor(RandomUtil.getColor(0, 200));
        // width是线宽, float型
        BasicStroke bs = new BasicStroke(2);
        g.setStroke(bs);
        // 画出曲线
        QuadCurve2D.Double curve = new QuadCurve2D.Double(0d,
                RandomUtil.getInt(4, height - 8), width / 2, height / 2, width,
                RandomUtil.getInt(4, height - 8));
        g.draw(curve);
        // 销毁图像
        g.dispose();
        return bi;
    }

    public static Map<String, Object> get() {
        String code = RandomUtil.choice(codechars, 4);
        BufferedImage bi = draw(code);
        Image img = null;
        try {
            img = ImageConverter.awt2jfx(bi);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Map.of("code", code, "image", img);

    }
}
