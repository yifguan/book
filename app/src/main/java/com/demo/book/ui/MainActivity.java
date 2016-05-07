/**
 * Copyright (c) 2016 Beijing Sankuai Inc.
 * <p/>
 * The right to copy, distribute, modify, or otherwise make use
 * of this software may be licensed only pursuant to the terms
 * of an applicable Beijing Sankuai license agreement.
 */
package com.demo.book.ui;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;

public class MainActivity extends FragmentActivity {
    private FragmentTabHost mTabHost;
    private LayoutInflater layoutInflater;

    final class MainTap{
        String tabTxt;
        Class flagClass;
        int imgId;
        int pressColor;
        int unpressColor;
        View view;

        public MainTap(String tabTxt, int imgId, int pressColor, int unpressColor, View view) {
            this.tabTxt = tabTxt;
            this.imgId = imgId;
            this.pressColor = pressColor;
            this.unpressColor = unpressColor;
            this.view = view;
        }

        public void setView(View v) {
            view = v;
        }
    }


}
