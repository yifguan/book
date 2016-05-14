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
import android.os.Parcel;
import android.util.Base64;

import com.avos.avoscloud.AVUser;
import com.demo.book.BookApplication;
import com.demo.book.cons.Cons;
import com.demo.book.utils.ParcelableUtil;

public class StatusService {
    private boolean isLoginin = false;
    private AVUser avUser = null;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Context mContext;
    private static StatusService mStatusService;

    private StatusService(Context context) {
        mContext = context;
        preferences = mContext.getSharedPreferences(Cons.ACTIVITY_STATUS_NAME, 0);
        editor = preferences.edit();
        avUser = getUser();
    }


    public boolean isLoginin() {
        isLoginin = preferences.getBoolean(Cons.LOGIN_PREFERENCE_STATE, false);
        return isLoginin;
    }

    public AVUser getUser() {
        String parcelable = preferences.getString(Cons.LOGIN_PREFERENCE_USER, null);
        if (parcelable != null) {
            Parcel parcel = ParcelableUtil.unmarshall(Base64.decode(parcelable, 0));
            avUser = (AVUser) AVUser.CREATOR.createFromParcel(parcel);
        } else {
            avUser = new AVUser();
        }
        return avUser;
    }

    public void setLoginin(boolean loginin, AVUser user) {
        isLoginin = loginin;
        avUser = user;
        editor.putBoolean(Cons.LOGIN_PREFERENCE_STATE, isLoginin)
                .commit();
        if (user != null) {
            byte[] bytes = ParcelableUtil.marshall(avUser);
            editor.putString(Cons.LOGIN_PREFERENCE_USER, Base64.encodeToString(bytes, 0))
                    .commit();
        } else {
            editor.putString(Cons.LOGIN_PREFERENCE_USER, null)
                    .commit();
        }

    }

    public static StatusService getInstance() {
        if (mStatusService == null) {
            mStatusService = new StatusService(BookApplication.getContext());
        }

        return mStatusService;
    }
}
