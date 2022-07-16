package com.xftxyz.smms.utils;

import java.io.PrintStream;

public class Debug {
    private static PrintStream pw = System.out;

    public static void log(String message) {
        if (pw != null){
            pw.println(message);
        }
    }
}
