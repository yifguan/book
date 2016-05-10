/**
 * Copyright (c) 2016 Beijing Sankuai Inc.
 * <p/>
 * The right to copy, distribute, modify, or otherwise make use
 * of this software may be licensed only pursuant to the terms
 * of an applicable Beijing Sankuai license agreement.
 */
package com.demo.book.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.EditText;

import com.demo.book.R;
import com.demo.book.bean.AVBookInfo;

public class AddBookcaseActivity extends BaseActivity implements View.OnClickListener {

    private AVBookInfo bookInfo;
    private EditText nameEdit, authorEdit, publisherEdit, summaryEdit;
    private CheckBox statusChk;


    public static void startAddBookcaseActivity(Context context) {
        Intent intent = new Intent(context, AddBookcaseActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_addbookcase);
        initView();
        initData();
    }

    private void initData() {
        bookInfo = new AVBookInfo();
    }

    private void initView() {
        nameEdit = (EditText) findViewById(R.id.booknameedit);
        authorEdit = (EditText) findViewById(R.id.bookauthoredit);
        publisherEdit = (EditText) findViewById(R.id.bookpublisheredit);
        summaryEdit = (EditText) findViewById(R.id.booksummaryedit);
        statusChk = (CheckBox) findViewById(R.id.bookstatuscheckbox);
    }

    @Override
    public void onClick(View v) {
        bookInfo.setName(nameEdit.getText().toString());
        
    }
}
