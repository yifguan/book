/**
 * Copyright (c) 2016 Beijing Sankuai Inc.
 * <p/>
 * The right to copy, distribute, modify, or otherwise make use
 * of this software may be licensed only pursuant to the terms
 * of an applicable Beijing Sankuai license agreement.
 */
package com.demo.book.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.demo.book.R;
import com.demo.book.adapter.BookInfoAdapter;
import com.demo.book.bean.AVBookInfo;
import com.demo.book.presenter.BookPresenter;
import com.demo.book.ui.account.LoginActivity;
import com.demo.book.utils.LogUtil;

import java.util.List;

public class BookcaseFragment extends BaseFragment implements BookPresenter.UIView {
    public final static String TAG = "BookcaseFragment";

    private View mFragmentView;
    private boolean isPrepared;
    private boolean mHasLoadedOnce = false;

    private ListView listView;

    private Handler mHandler;
    private static int index;
    private SwipeRefreshLayout swipeRefreshLayout;
    private BookPresenter mBookPresenter;
    private BookInfoAdapter bookInfoAdapter;
    private ListView bookList;
    private List<AVBookInfo> bookinfolist;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LogUtil.d(TAG, "onCreateView");
        if (mFragmentView == null) {
            mFragmentView = inflater.inflate(R.layout.fragment_bookcase, container, false);
            mBookPresenter = new BookPresenter(getActivity(), this);
            bookInfoAdapter = new BookInfoAdapter(getActivity());
            initDatas();
            initViews();
            isPrepared = true;
            lazyLoad();
        }
        ViewGroup parent = (ViewGroup) mFragmentView.getParent();
        if (parent != null) {
            parent.removeView(mFragmentView);
        }

        swipeRefreshLayout = (SwipeRefreshLayout) mFragmentView.findViewById(R.id.bookcaseswipe);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //Todo: refresh data here
                mBookPresenter.getBookFromOwner("guan");
            }
        });

        bookList = (ListView) mFragmentView.findViewById(R.id.bookcaselist);
        bookList.setAdapter(bookInfoAdapter);
        if (bookinfolist != null)
            bookInfoAdapter.setList(bookinfolist);

        mFragmentView.findViewById(R.id.add_item).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddBookcaseActivity.startAddBookcaseActivity(getActivity());
            }
        });

        mFragmentView.findViewById(R.id.logoutimage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogoutDialog logoutDialog = new LogoutDialog(getActivity(), new LogoutDialog.onClickListener() {
                    @Override
                    public void onComfirm() {
                        mBookPresenter.logout();
                    }
                    @Override
                    public void onCancel() {
                    }
                });
                logoutDialog.show();
            }
        });

        return mFragmentView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.d(TAG, "onDestroy");
    }

    void initDatas() {
        mBookPresenter.getBookFromOwner("guan");
    }

    void initViews() {
    }

    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible) {
            return;
        }
    }

    @Override
    public void logout() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void updateList(List<AVBookInfo> list) {
        bookInfoAdapter.setList(list);
        bookInfoAdapter.notifyDataSetChanged();
        this.bookinfolist = list;
        swipeRefreshLayout.setRefreshing(false);
    }
}
