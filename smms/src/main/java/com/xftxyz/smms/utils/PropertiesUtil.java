package com.xftxyz.smms.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
    public static Properties getProperties(String fileName) throws IOException {
        Properties pros = new Properties();
        InputStream in = PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName);
        pros.load(in);
        in.close();
        return pros;
    }
}
