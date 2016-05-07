package com.demo.book.utils;

import android.util.Log;

/**
 * Created by zhoumao on 16/2/18.
 */
public class LogUtil {
    static String tag = "Stock";
    public static void d(String message){
        if(message ==  null){}
        else Log.d(tag, message);
    }

    public static void d(Object object){
        Log.d(tag, object.toString());
    }

    public static void d(String tag,String message){
        Log.d(tag, message);
    }

    public static void d(String tag,Object object){
        Log.d(tag, object.toString());
    }
}
