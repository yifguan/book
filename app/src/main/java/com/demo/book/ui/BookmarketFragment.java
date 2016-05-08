/**
 * Copyright (c) 2016 Beijing Sankuai Inc.
 * <p/>
 * The right to copy, distribute, modify, or otherwise make use
 * of this software may be licensed only pursuant to the terms
 * of an applicable Beijing Sankuai license agreement.
 */
package com.demo.book.ui;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.demo.book.R;

import java.util.Timer;

public class BookmarketFragment extends BaseFragment {

    private View mFragmentView;
    private boolean isPrepared;
    private boolean mHasLoadedOnce;

    private ListView listView;

    private Timer autoUpdate;
    private Handler mHandler;
    private static int index;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mFragmentView == null) {
            mFragmentView = inflater.inflate(R.layout.fragment_bookmarket, container, false);
            initDatas();
            initViews();
            isPrepared = true;
            lazyLoad();
        }
        ViewGroup parent = (ViewGroup) mFragmentView.getParent();
        if (parent != null) {
            parent.removeView(mFragmentView);
        }
        return mFragmentView;
    }

    void initDatas() {
        mHandler = new Handler();

    }

    void initViews() {
    }

    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible || mHasLoadedOnce) {
            return;
        }
    }
}
