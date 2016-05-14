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
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SaveCallback;
import com.demo.book.R;
import com.demo.book.bean.AVBookInfo;
import com.demo.book.service.StatusService;
import com.demo.book.utils.LogUtil;

public class AddBookcaseActivity extends BaseActivity{

    private AVBookInfo bookInfo;
    private EditText nameEdit, authorEdit, publisherEdit, summaryEdit;
    private CheckBox statusChk;
    private AVUser loginUser;
    private Context mContext;


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
        loginUser = StatusService.getInstance().getUser();
        mContext = this;
    }

    private void initView() {
        nameEdit = (EditText) findViewById(R.id.booknameedit);
        authorEdit = (EditText) findViewById(R.id.bookauthoredit);
        publisherEdit = (EditText) findViewById(R.id.bookpublisheredit);
        summaryEdit = (EditText) findViewById(R.id.booksummaryedit);
        statusChk = (CheckBox) findViewById(R.id.bookstatuscheckbox);

        findViewById(R.id.back_img).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.sub_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookInfo.setName(nameEdit.getText().toString());
                bookInfo.setAuthor(authorEdit.getText().toString());
                bookInfo.setOwner(loginUser.getUsername());
                bookInfo.setPhone(loginUser.getMobilePhoneNumber());
                bookInfo.setPublisher(publisherEdit.getText().toString());
                bookInfo.setSummary(summaryEdit.getText().toString());
                bookInfo.setStatus(statusChk.isChecked());

                bookInfo.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(AVException e) {
                        if (e == null) {
                            LogUtil.d("save compelte");
                            Toast.makeText(mContext, "保存成功", Toast.LENGTH_SHORT);
                            finish();
                        } else {
                            LogUtil.d("error " + e.getCode() + "in " + e.getCause());
                            Toast.makeText(mContext, "保存出错，请稍后重试", Toast.LENGTH_SHORT);
                        }
                    }
                });
            }
        });
    }
}
