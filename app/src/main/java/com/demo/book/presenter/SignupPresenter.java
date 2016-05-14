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
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SignUpCallback;
import com.demo.book.service.StatusService;
import com.demo.book.utils.LogUtil;

public class SignupPresenter {
    AVUser avUser;
    ISignupView mSignupView;
    StatusService statusService;
    Context mContext;
    private String userName;

    public SignupPresenter(Context context,ISignupView view) {
        mContext = context;
        mSignupView = view;
    }

    public void Signup(String username, String password, String phone) {
        avUser = new AVUser();
        avUser.setUsername(username);
        avUser.setPassword(password);
        avUser.setMobilePhoneNumber(phone);
        avUser.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(AVException e) {
                if (e != null) {
                    LogUtil.d("sign up:" + e.getMessage());
                    String reason;
                    switch (e.getCode()) {
                        case 127:
                            reason = "无效手机号";
                            break;
                        case 214:
                            reason = "手机号已经被注册";
                            break;
                        default:
                            reason = e.getMessage();
                    }
                    mSignupView.onFailed(reason);
                } else {
                    mSignupView.onSuccess();
                }
            }
        });
    }

    public void onDestroy() {
        mSignupView = null;
    }

    public interface ISignupView {
        void onSuccess();
        void onFailed(String reason);
    }
}
