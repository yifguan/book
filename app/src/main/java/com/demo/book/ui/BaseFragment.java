/**
 * Copyright (c) 2016 Beijing Sankuai Inc.
 * <p/>
 * The right to copy, distribute, modify, or otherwise make use
 * of this software may be licensed only pursuant to the terms
 * of an applicable Beijing Sankuai license agreement.
 */
package com.demo.book.ui;

import android.app.Fragment;
import android.os.Build;

import com.demo.book.utils.LogUtil;

public abstract class BaseFragment extends Fragment {
    protected boolean isVisible;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1) {
            if (getUserVisibleHint()) {
                isVisible = true;
                onVisible();
                LogUtil.d("visible");
            } else {
                isVisible = false;
                onInvisible();
                LogUtil.d("invisible");
            }
        }
    }

    protected void onVisible() {
        lazyload();
    }

    protected void onInvisible() {

    }

    protected abstract void lazyload();
}