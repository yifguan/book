/**
 * Copyright (c) 2016 Beijing Sankuai Inc.
 * <p/>
 * The right to copy, distribute, modify, or otherwise make use
 * of this software may be licensed only pursuant to the terms
 * of an applicable Beijing Sankuai license agreement.
 */
package com.demo.book.bean;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;

@AVClassName("BookInfo")
public class BookInfo extends AVObject{
    private String owner;   //书籍所有者
    private String status;  //书籍的状态
    private String name;
    private String author;
    private String publisher;
    private String summary; //书籍简介
    private String image;   //书籍照片

    public BookInfo(){}

    public BookInfo(String owner, String status, String name, String author, String publisher, String summary, String image) {
        this.owner = owner;
        this.status = status;
        this.name = name;
        this.author = author;
        this.publisher = publisher;
        this.summary = summary;
        this.image = image;
    }


}