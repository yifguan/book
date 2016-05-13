/**
 * Copyright (c) 2015 Beijing Sankuai Inc.
 * <p/>
 * The right to copy, distribute, modify, or otherwise make use
 * of this software may be licensed only pursuant to the terms
 * of an applicable Beijing Sankuai license agreement.
 */
package com.demo.book.presenter;

import android.content.Context;

import com.demo.book.service.StatusService;
import com.demo.book.utils.LogUtil;


public class LoginPresenter implements LoginInteractor.OnLoginFinishedListner {

    ILoginView mLoginView;
    LoginInteractor mLoginInteractor;
    StatusService statusService;
    Context mContext;
    private String userName;

    public LoginPresenter(Context context,ILoginView view) {
        mContext = context;
        mLoginView = view;
        mLoginInteractor = new LoginInteractor();
        statusService = new StatusService(mContext);
    }

    public void validateCredentials(String username, String password) {
        if (mLoginInteractor != null) {
            mLoginInteractor.login(username, password, this);
            this.userName = username;
        }
        if (mLoginView != null) {
            mLoginView.showProgress();
        }
    }

    public void onDestroy() {
        mLoginView = null;
    }

    @Override
    public void onSuccess() {
        statusService.setLoginin(true,userName);
        mLoginView.gotoMainUI();
    }

    @Override
    public void onFailed() {
    }

    @Override
    public void onVerification() {
        LogUtil.d("onVerification");
    }


    public interface ILoginView {
        void showProgress();
        void hideProgress();
        void gotoMainUI();
    }
}
