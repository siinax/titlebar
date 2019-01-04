package com.siinax.titlebarlibrary;

import android.util.Log;


public class Loga {

    public static void i(String tag, String content) {
        if (Constant.IS_DEBUG) {
            String log = content + "  :   [" + getTraceInfo()+"]";
            Log.i(tag, log);
        }
    }

    public static void e(String tag, String content) {
        if (Constant.IS_DEBUG) {
            String log = content + "  :   [" + getTraceInfo()+"]";
            Log.e(tag, log);
        }
    }

    public static void e(String content) {

        if (Constant.IS_DEBUG) {
            String log = content + "  :   [" + getTraceInfo()+"]";
            Log.e("----------", log);
        }
    }

    public static void d(String tag, String content) {
        if (Constant.IS_DEBUG) {
            String log = content + "  :   [" + getTraceInfo()+"]";
            Log.d(tag, log);
        }
    }

    public static void v(String tag, String content) {
        if (Constant.IS_DEBUG) {
            String log = content + "  :   [" + getTraceInfo()+"]";
            Log.v(tag, log);
        }
    }

    public static void w(String tag, String content) {
        if (Constant.IS_DEBUG) {
            String log = content + "  :   [" + getTraceInfo()+"]";
            Log.w(tag, log);
        }
    }

    public static void w(String tag, String content, Exception e) {
        if (Constant.IS_DEBUG) {
            String log = content + "  :   [" + getTraceInfo()+"]";
            Log.w(tag, log + "--" + e.toString());
        }
    }

    public static void e(String tag, String content, Exception e) {
        if (Constant.IS_DEBUG) {
            String log = content + "  :   [" + getTraceInfo()+"]";
            Log.e(tag, log + "--" + e.toString());
        }
    }

    public static void syso(String content) {
        if (Constant.IS_DEBUG) {
            String log = content + "  :   [" + getTraceInfo()+"]";
            System.out.println(log);
        }
    }

    /**
     * 获取堆栈信息
     */
    private static String getTraceInfo() {
        StringBuffer sb = new StringBuffer();
        StackTraceElement[] stacks = new Throwable().getStackTrace();
        String className = stacks[2].getClassName();
        int index = className.lastIndexOf('.');
        if (index >= 0) {
            className = className.substring(index + 1, className.length());
        }
        String methodName = stacks[2].getMethodName();
        int lineNumber = stacks[2].getLineNumber();
        sb.append(className).append("->").append(methodName).append("()->").append(lineNumber);
        return "位置 "+sb.toString();
    }

}
