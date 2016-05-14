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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.DeleteCallback;
import com.avos.avoscloud.SaveCallback;
import com.demo.book.R;
import com.demo.book.bean.AVBookInfo;
import com.demo.book.service.StatusService;
import com.demo.book.utils.LogUtil;

public class BookDetailActivity extends BaseActivity implements View.OnClickListener {

    private AVBookInfo bookInfo;
    private AVUser loginUser;
    private Context mContext;
    private boolean isOwner;
    private EditText tvName;
    private EditText tvAuthor;
    private EditText tvPublisher;
    private EditText tvSummary;
    private TextView tvPhone;
    private CheckBox statusChk;
    private Button btnDel;
    private Button btnSave;

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, BookDetailActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_bookdetail);
        initData();
        initView();
        adjustView();
    }

    private void adjustView() {
        if (isOwner == false) {
            btnDel.setVisibility(View.INVISIBLE);
            statusChk.setClickable(false);
            tvName.setEnabled(false);
            tvAuthor.setEnabled(false);
            tvPublisher.setEnabled(false);
            tvSummary.setEnabled(false);
            btnSave.setVisibility(View.INVISIBLE);
        } else {
            btnDel.setOnClickListener(this);
            btnSave.setOnClickListener(this);
        }
    }

    private void initData() {
        Intent intent = getIntent();
        Bundle b = intent.getBundleExtra("bundle");
        bookInfo = b.getParcelable("bookinfo");
        loginUser = StatusService.getInstance().getUser();
        if(loginUser.getUsername().equals(bookInfo.getOwner())) {
            isOwner = true;
        } else {
            isOwner = false;
        }
        mContext = this;
    }

    private void initView() {
        tvName =  (EditText) findViewById(R.id.detail_bookname);
        tvAuthor =  (EditText)findViewById(R.id.detail_bookauthor);
        tvPublisher =  (EditText)findViewById(R.id.detail_bookpublisher);
        tvSummary = (EditText)findViewById(R.id.detail_booksummary);
        tvPhone = (TextView)findViewById(R.id.detail_owner_phone);
        statusChk = (CheckBox) findViewById(R.id.detail_bookstatuscheckbox);
        btnDel = (Button) findViewById(R.id.detail_del_btn);
        btnSave = (Button) findViewById(R.id.detail_save_btn);

        tvName.setText(bookInfo.getName());
        tvAuthor.setText(bookInfo.getAuthor());
        tvSummary.setText(bookInfo.getSummary());
        tvPhone.setText(bookInfo.getPhone());
        tvPublisher.setText(bookInfo.getPublisher());
        statusChk.setChecked(bookInfo.getStatus());

        findViewById(R.id.back_img).setOnClickListener(this);
    }

    private void saveBookInfo(){
        bookInfo.setName(tvName.getText().toString());
        bookInfo.setAuthor(tvAuthor.getText().toString());
        bookInfo.setOwner(loginUser.getUsername());
        bookInfo.setPublisher(tvPublisher.getText().toString());
        bookInfo.setSummary(tvSummary.getText().toString());
        bookInfo.setStatus(statusChk.isChecked());

        bookInfo.saveInBackground(new SaveCallback() {
            @Override
            public void done(AVException e) {
                if (e == null) {
                    LogUtil.d("save compelte");
                    showToast("保存成功");
                    finish();
                } else {
                    LogUtil.d("error " + e.getCode() + "in " + e.getCause());
                    showToast("保存出错，请稍后重试");
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.detail_del_btn:
                bookInfo.deleteInBackground(new DeleteCallback() {
                    @Override
                    public void done(AVException e) {
                        if (e == null) {
                            showToast("删除成功");
                            setResult(0);
                            finish();
                        } else {
                            LogUtil.d("del: " + e.getMessage());
                            showToast("删除失败，请稍后重试");
                        }
                    }
                });
                break;
            case R.id.detail_save_btn:
                saveBookInfo();
                break;
            case R.id.back_img:
                finish();
                break;
        }
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
