/**
 * Copyright (c) 2016 Beijing Sankuai Inc.
 *
 * The right to copy, distribute, modify, or otherwise make use
 * of this software may be licensed only pursuant to the terms
 * of an applicable Beijing Sankuai license agreement.
 */
package com.demo.book.ui.account;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.demo.book.R;
import com.demo.book.presenter.LoginPresenter;
import com.demo.book.ui.MainActivity;
import com.demo.book.utils.ExitAppUtils;


public class LoginActivity extends Activity implements LoginPresenter.ILoginView, View.OnClickListener {

    private Button btnLogin;
    private Button btnSignup;
    private EditText etUsername;
    private EditText etPassword;
    private LoginPresenter mLoginPresenter;
    private ProgressDialog loginProgress;

    public static void actionStart(Context context) {
        context.startActivity(new Intent(context, LoginActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        mLoginPresenter = new LoginPresenter(this,this);
        btnLogin = (Button) findViewById(R.id.sign_in_button);
        btnSignup = (Button) findViewById(R.id.sign_up_button);
        etUsername = (EditText) findViewById(R.id.username_edit);
        etPassword = (EditText) findViewById(R.id.password_edit);

        btnLogin.setOnClickListener(this);
        btnSignup.setOnClickListener(this);

        loginProgress = new ProgressDialog(this);
    }

    @Override
    protected void onDestroy() {
        mLoginPresenter = null;
        super.onDestroy();
    }

    @Override
    public void showProgress() {
        loginProgress.show();
    }

    @Override
    public void hideProgress() {
        loginProgress.dismiss();
    }

    @Override
    public void gotoMainUI() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button:
                mLoginPresenter.validateCredentials(etUsername.getText().toString(), etPassword.getText().toString());
                break;
            case R.id.sign_up_button:
                SignupActivity.actionStart(this);
                break;
        }
    }

    private long firstTime = 0;
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        switch(keyCode)
        {
            case KeyEvent.KEYCODE_BACK:
                long secondTime = System.currentTimeMillis();
                if (secondTime - firstTime > 2000) {                                         //如果两次按键时间间隔大于2秒，则不退出
                    Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                    firstTime = secondTime;//更新firstTime
                    return true;
                } else {                                                    //两次按键小于2秒时，退出应用
                    ExitAppUtils.getInstance().exit();
                }
                break;
        }
        return super.onKeyUp(keyCode, event);
    }
}
