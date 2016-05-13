/**
 * Copyright (c) 2016 Beijing Sankuai Inc.
 * <p/>
 * The right to copy, distribute, modify, or otherwise make use
 * of this software may be licensed only pursuant to the terms
 * of an applicable Beijing Sankuai license agreement.
 */
package com.demo.book;

import android.app.Application;

import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;
import com.demo.book.bean.AVBookInfo;
import com.demo.book.cons.LeancloudConfig;
import com.demo.book.utils.LogUtil;

public class BookApplication extends Application {
    private static boolean isLogin;
    private static String User = "guan";

    public static boolean isLogin() {
        return isLogin;
    }

    public static void setLogin(boolean login) {
        isLogin = login;
    }

    public static String getUser() {
        return User;
    }

    public static void setUser(String user) {
        User = user;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        LogUtil.d("test for upload");
        AVObject.registerSubclass(AVBookInfo.class);
        AVOSCloud.initialize(this, LeancloudConfig.APP_ID, LeancloudConfig.APP_KEY);
        //testForBookInfo();
    }

    public void testForBookInfo() {
        for (int i = 0; i < 10; i++) {
            AVBookInfo bookInfo = new AVBookInfo();
            bookInfo.setOwner("pang");
            bookInfo.setAuthor("pangyi");
            bookInfo.setName("kid " + i);
            bookInfo.setPublisher("in kid");
            bookInfo.setSummary("this book " + i + "is bad");
            bookInfo.setStatus(true);
            bookInfo.saveInBackground();
        }
    }
}
