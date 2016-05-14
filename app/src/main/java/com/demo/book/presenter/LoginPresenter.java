/**
 * Copyright (c) 2015 Beijing Sankuai Inc.
 * <p/>
 * The right to copy, distribute, modify, or otherwise make use
 * of this software may be licensed only pursuant to the terms
 * of an applicable Beijing Sankuai license agreement.
 */
package com.demo.book.presenter;

import android.content.Context;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.demo.book.service.StatusService;
import com.demo.book.utils.LogUtil;


public class LoginPresenter {

    AVUser avUser;
    ILoginView mLoginView;
    StatusService statusService;
    Context mContext;

    public LoginPresenter(Context context,ILoginView view) {
        mContext = context;
        mLoginView = view;
        statusService = StatusService.getInstance();
    }

    public void validateCredentials(String username, String password) {
        avUser = statusService.getUser();
        avUser.logOut();
        avUser.logInInBackground(username, password, new LogInCallback<AVUser>() {
            @Override
            public void done(AVUser avUser, AVException e) {
                if (e != null) {
                    LogUtil.d("login " + e.getMessage());
                    statusService.setLoginin(false, null);
                    mLoginView.onFailed();
                } else {
                    statusService.setLoginin(true, avUser);
                    mLoginView.onSuccess();
                }
            }
        });
    }

    public void onDestroy() {
        mLoginView = null;
    }

    public interface ILoginView {
        void onSuccess();
        void onFailed();
    }
}
