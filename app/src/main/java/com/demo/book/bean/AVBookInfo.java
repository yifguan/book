/**
 * Copyright (c) 2016 Beijing Sankuai Inc.
 * <p/>
 * The right to copy, distribute, modify, or otherwise make use
 * of this software may be licensed only pursuant to the terms
 * of an applicable Beijing Sankuai license agreement.
 */
package com.demo.book.bean;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;

@AVClassName("AVBookInfo")
public class AVBookInfo extends AVObject {
    public static final Creator CREATOR = AVObjectCreator.instance;

    public static final String OWNER = "owner";   //书籍所有者
//    public static final String Phone = "";
    public static final String STATUS = "status";  //书籍的状态
    public static final String NAME = "name";
    public static final String AUTHOR = "author";
    public static final String PUBLISHER = "publisher";
    public static final String SUMMARY = "summary"; //书籍简介
    public static final String IMAGE = "image";   //书籍照片

    public AVBookInfo() {}

    public String getOwner() {
        return getString(OWNER);
    }

    public void setOwner(String owner) {
        put(OWNER, owner);
    }

    public Boolean getStatus() {
        return getBoolean(STATUS);
    }

    public void setStatus(Boolean status) {
       put(STATUS, status);
    }

    public String getName() {
        return getString(NAME);
    }

    public void setName(String name) {
        put(NAME, name);
    }

    public String getAuthor() {
        return getString(AUTHOR);
    }

    public void setAuthor(String author) {
        put(AUTHOR, author);
    }

    public String getPublisher() {
        return getString(PUBLISHER);
    }

    public void setPublisher(String publisher) {
        put(PUBLISHER, publisher);
    }

    public String getSummary() {
        return getString(SUMMARY);
    }

    public void setSummary(String summary) {
        put(SUMMARY, summary);
    }

    public AVFile getImage() {
        return getAVFile(IMAGE);
    }

    public void setImage(AVFile image) {
        put(IMAGE, image);
    }
}