package com.xftxyz.smms.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Properties;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.xftxyz.smms.converter.RoleConverter;
import com.xftxyz.smms.converter.TimestampConverter;

import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class FileUtil {

    private static ClassLoader classLoader;

    private static FileChooser fileChooser;
    static {
        classLoader = FileUtil.class.getClassLoader();
        fileChooser = new FileChooser();
        // xlsx文件类型
        fileChooser.getExtensionFilters().add(new ExtensionFilter("Excel文件", "*.xlsx"));
        fileChooser.setSelectedExtensionFilter(fileChooser.getExtensionFilters().get(0));
        fileChooser.setTitle("保存文件");
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home")));
    }

    public static File showSaveDialog() {
        File file = fileChooser.showSaveDialog(null);
        if (file == null) {
            return null;
        }
        return file;
    }

    // 写入excel
    public static <T> void writeExcel(File file, Class<T> head, Collection<T> data) {
        ExcelWriterBuilder ewb = EasyExcel.write(file);
        ewb.head(head);
        ewb.registerConverter(new RoleConverter());
        ewb.registerConverter(new TimestampConverter());
        ewb.sheet("Sheet1").doWrite(data);
    }

    public static Properties getProperties(String fileName) throws IOException {
        Properties pros = new Properties();
        InputStream in = classLoader.getResourceAsStream(fileName);
        pros.load(in);
        in.close();
        return pros;
    }

    // 获取图片
    public static Image getImage(String fileName) {
        InputStream res = classLoader.getResourceAsStream(fileName);
        // System.out.println(res);
        return new Image(res);
    }
}
