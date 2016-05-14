/**
 * Copyright (c) 2016 Beijing Sankuai Inc.
 * <p/>
 * The right to copy, distribute, modify, or otherwise make use
 * of this software may be licensed only pursuant to the terms
 * of an applicable Beijing Sankuai license agreement.
 */
package com.demo.book.ui.account;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.demo.book.R;
import com.demo.book.presenter.SignupPresenter;
import com.demo.book.ui.BaseActivity;

public class SignupActivity extends BaseActivity implements SignupPresenter.ISignupView {

    private Button btnSubSignup;
    private EditText etUsername;
    private EditText etPassword;
    private EditText etPasswordConfirm;
    private EditText etPhone;
    private SignupPresenter signupPresenter;
    private ProgressDialog SignupProgress;

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
        signupPresenter = new SignupPresenter(this, this);
        btnSubSignup = (Button) findViewById(R.id.sub_signup_button);
        etUsername = (EditText) findViewById(R.id.signup_username_edit);
        etPassword = (EditText) findViewById(R.id.signup_password_edit);
        etPasswordConfirm = (EditText) findViewById(R.id.signup_password_comfirm_edit);
        etPhone = (EditText) findViewById(R.id.signup_phone_edit);

        SignupProgress = new ProgressDialog(this);
        SignupProgress.setMessage("正在注册");

        btnSubSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etUsername.getText().length() == 0) {
                    showToast("请输入用户名");
                } else if (etPassword.getText().length() == 0
                        || etPasswordConfirm.getText().length() == 0) {
                    showToast("请输入密码");
                } else if(etPasswordConfirm.getText().toString().equals(etPassword.getText().toString()) == false) {
                    showToast("两次输入密码不一致");
                } else if(etPhone.getText().length() == 0) {
                    showToast("请输入手机号码");
                } else {
                    signupPresenter.Signup(etUsername.getText().toString(),
                            etPassword.getText().toString(),
                            etPhone.getText().toString());
                }
            }
        });
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess() {
        showToast("注册成功");
        finish();
    }

    @Override
    public void onFailed(String reason) {
        showToast("注册失败:" + reason);
    }
}
