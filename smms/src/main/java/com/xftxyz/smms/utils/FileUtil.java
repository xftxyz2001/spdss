package com.xftxyz.smms.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javafx.scene.image.Image;

public class FileUtil {
    public static Properties getProperties(String fileName) throws IOException {
        Properties pros = new Properties();
        InputStream in = FileUtil.class.getClassLoader().getResourceAsStream(fileName);
        pros.load(in);
        in.close();
        return pros;
    }

    // 获取图片
    public static Image getImage(String fileName) {
        InputStream res = FileUtil.class.getClassLoader().getResourceAsStream(fileName);
        System.out.println(res);
        return new Image(res);
    }
}
