/**
 * Copyright (c) 2016 Beijing Sankuai Inc.
 * <p/>
 * The right to copy, distribute, modify, or otherwise make use
 * of this software may be licensed only pursuant to the terms
 * of an applicable Beijing Sankuai license agreement.
 */
package com.demo.book.service;

import android.content.Context;
import android.content.SharedPreferences;

import com.demo.book.cons.Cons;

public class StatusService {
    private boolean isLoginin = false;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Context mContext;

    public StatusService(Context context) {
        mContext = context;
        preferences = mContext.getSharedPreferences(Cons.ACTIVITY_STATUS_NAME, 0);
        editor = preferences.edit();
    }


    public boolean isLoginin() {
        isLoginin = preferences.getBoolean(Cons.LOGIN_PREFERENCE_STATE, false);
        return isLoginin;
    }

    public String getUserName(){
        return  preferences.getString(Cons.LOGIN_PREFERENCE_USERNAME,"");
    }

    public void setLoginin(boolean loginin,String username) {
        isLoginin = loginin;
        editor.putBoolean(Cons.LOGIN_PREFERENCE_STATE, isLoginin)
                .putString(Cons.LOGIN_PREFERENCE_USERNAME,username)
                .commit();
    }
}
