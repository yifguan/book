/**
 * Copyright (c) 2016 Beijing Sankuai Inc.
 * <p/>
 * The right to copy, distribute, modify, or otherwise make use
 * of this software may be licensed only pursuant to the terms
 * of an applicable Beijing Sankuai license agreement.
 */
package com.demo.book.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.demo.book.R;

public class LogoutDialog extends Dialog implements View.OnClickListener {
    private Context mContext;
    private Button btnComfirm;
    private Button btnCancel;
    private onClickListener mListener;
    public LogoutDialog(Context context, onClickListener listener) {
        super(context);
        mContext = context;
        mListener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_logout);
        btnComfirm = (Button) findViewById(R.id.btn_logout_comfirm);
        btnCancel = (Button) findViewById(R.id.btn_logout_cancel);

        btnCancel.setOnClickListener(this);
        btnComfirm.setOnClickListener(this);
        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                mListener.onCancel();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_logout_comfirm:
                mListener.onComfirm();
                dismiss();
                break;
            case R.id.btn_logout_cancel:
                mListener.onCancel();
                dismiss();
                break;
        }
    }

    public static abstract class onClickListener{
        public abstract void onComfirm();
        public abstract void onCancel();
    }
}