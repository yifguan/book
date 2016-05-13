/**
 * Copyright (c) 2016 Beijing Sankuai Inc.
 * <p/>
 * The right to copy, distribute, modify, or otherwise make use
 * of this software may be licensed only pursuant to the terms
 * of an applicable Beijing Sankuai license agreement.
 */
package com.demo.book.ui.account;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import com.demo.book.R;
import com.demo.book.ui.BaseActivity;

public class SignupActivity extends BaseActivity {

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, SignupActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_signup);
        initView();
    }

    private void initView() {

    }
}
