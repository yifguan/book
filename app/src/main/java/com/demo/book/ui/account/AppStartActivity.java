/**
 * Copyright (c) 2015 Beijing Sankuai Inc.
 * <p/>
 * The right to copy, distribute, modify, or otherwise make use
 * of this software may be licensed only pursuant to the terms
 * of an applicable Beijing Sankuai license agreement.
 */
package com.demo.book.ui.account;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.demo.book.service.StatusService;
import com.demo.book.ui.BaseActivity;
import com.demo.book.ui.MainActivity;

public class AppStartActivity extends BaseActivity {
    private boolean isLoginin = false;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isLoginin = StatusService.getInstance().isLoginin();
        if (isLoginin) {
            MainActivity.actionStart(this);
        } else {
            LoginActivity.actionStart(this);
        }
        this.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}