package com.example;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.excel.EasyExcel;

/**
 * Hello world!
 *
 */
public class App {
    private static List<Goods> data() {
        ArrayList<Goods> goods = new ArrayList<>();
        Goods goods1 = new Goods();
        goods1.setId(1);
        goods1.setName("asf");
        goods1.setNum(new BigDecimal(21));
        goods1.setPrice(new BigDecimal(6));
        goods1.setUnit("kg");
        goods1.setDescribe("asdfsadfsadf");
        goods.add(goods1);
        return goods;

    }

    public static void main(String[] args) {
        EasyExcel.write(new File("C:/Users/25810/Desktop/1.xlsx"), Goods.class).sheet("数据").doWrite(data());
    }
}
