/**
 * Copyright (c) 2015 Beijing Sankuai Inc.
 * <p/>
 * The right to copy, distribute, modify, or otherwise make use
 * of this software may be licensed only pursuant to the terms
 * of an applicable Beijing Sankuai license agreement.
 */
package com.demo.book.presenter;

import android.os.Handler;
import android.os.Looper;

public class LoginInteractor {

    public void login(final String username, final String password, final OnLoginFinishedListner listner) {
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                if (username.equals("guan") && password.equals("guan")) {
                    listner.onSuccess();
                } else {
                    listner.onFailed();
                }
            }
        }, 1000);
    }

    public interface OnLoginFinishedListner{
        void onSuccess();
        void onFailed();
        void onVerification();
    }
}
