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
import com.demo.book.bean.BookInfo;
import com.demo.book.cons.LeancloudConfig;
import com.demo.book.utils.LogUtil;

public class BookApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        LogUtil.d("test for upload");
        AVObject.registerSubclass(BookInfo.class);
        AVOSCloud.initialize(this, LeancloudConfig.APP_ID, LeancloudConfig.APP_KEY);
    }
}
