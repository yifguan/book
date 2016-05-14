/**
 * Copyright (c) 2016 Beijing Sankuai Inc.
 * <p/>
 * The right to copy, distribute, modify, or otherwise make use
 * of this software may be licensed only pursuant to the terms
 * of an applicable Beijing Sankuai license agreement.
 */
package com.demo.book;

import android.app.Application;
import android.content.Context;

import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;
import com.demo.book.bean.AVBookInfo;
import com.demo.book.cons.LeancloudConfig;

public class BookApplication extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = getApplicationContext();
        AVObject.registerSubclass(AVBookInfo.class);
        AVOSCloud.initialize(this, LeancloudConfig.APP_ID, LeancloudConfig.APP_KEY);
    }

    public static Context getContext() {
        return mContext;
    }
}
