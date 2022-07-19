package com.xftxyz.smms.utils;

import java.io.PrintStream;

public class Debug {
    public static final PrintStream pw = null;
    // public static final PrintStream pw = null;

    public static void log(String message) {
        if (pw != null){
            pw.println(message);
        }
    }
}
