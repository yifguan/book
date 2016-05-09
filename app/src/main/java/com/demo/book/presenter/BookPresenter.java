/**
 * Copyright (c) 2016 Beijing Sankuai Inc.
 * <p/>
 * The right to copy, distribute, modify, or otherwise make use
 * of this software may be licensed only pursuant to the terms
 * of an applicable Beijing Sankuai license agreement.
 */
package com.demo.book.presenter;

import android.content.Context;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.demo.book.bean.AVBookInfo;

import java.util.List;

public class BookPresenter {
    private Context mContext;
    private UIView mUIView;
    List<AVBookInfo> bookList;

    public BookPresenter(Context context, UIView view){
        this.mContext = context;
        this.mUIView = view;
    }

    public void getBookFromOwner(String owner) {
        AVQuery<AVBookInfo> query = AVObject.getQuery(AVBookInfo.class);
        query.whereEqualTo(AVBookInfo.OWNER, owner);
        query.findInBackground(new FindCallback<AVBookInfo>() {
            @Override
            public void done(List<AVBookInfo> results, AVException e) {
                for (AVBookInfo a : results) {
                    //LogUtil.d("book: " + a);
                }
                mUIView.updateList(results);
            }
        });
    }

    public void getAllBook() {
        AVQuery<AVBookInfo> query = AVObject.getQuery(AVBookInfo.class);
        query.findInBackground(new FindCallback<AVBookInfo>() {
            @Override
            public void done(List<AVBookInfo> results, AVException e) {
                for (AVBookInfo a : results) {
                    //LogUtil.d("book: " + a);
                }
                mUIView.updateList(results);
            }
        });
    }

    public interface UIView{
        void updateList(List<AVBookInfo> list);
    }
}
