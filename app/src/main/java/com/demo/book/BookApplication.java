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

public class BookApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AVOSCloud.initialize(this, "fN9leiIzSqLGtVLjlitB6q3S-gzGzoHsz", "I5OA40LjDpCGfRbHnJDUtSD4");
    }
}
