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
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.demo.book.R;
import com.demo.book.adapter.BookInfoAdapter;
import com.demo.book.bean.AVBookInfo;
import com.demo.book.presenter.BookPresenter;

import java.util.List;

public class BookmarketFragment extends BaseFragment implements BookPresenter.UIView {
    private View mFragmentView;
    private boolean isPrepared;
    private boolean mHasLoadedOnce;

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
        if (mFragmentView == null) {
            mFragmentView = inflater.inflate(R.layout.fragment_bookmarket, container, false);
            mBookPresenter = new BookPresenter(getActivity(), this);
            initDatas();
            initViews();
            isPrepared = true;
            lazyLoad();
        }
        ViewGroup parent = (ViewGroup) mFragmentView.getParent();
        if (parent != null) {
            parent.removeView(mFragmentView);
        }

        swipeRefreshLayout = (SwipeRefreshLayout) mFragmentView.findViewById(R.id.bookmarketswipe);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //Todo: refresh data here
                mBookPresenter.getAllBook();
            }
        });

        bookInfoAdapter = new BookInfoAdapter(getActivity());
        bookList = (ListView) mFragmentView.findViewById(R.id.bookmarketlist);
        bookList.setAdapter(bookInfoAdapter);
        if (bookinfolist != null) {
            bookInfoAdapter.setList(bookinfolist);
        }

        return mFragmentView;
    }

    void initDatas() {
        mBookPresenter.getAllBook();
    }

    void initViews() {
    }

    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible || mHasLoadedOnce) {
            return;
        }
    }

    @Override
    public void updateList(List<AVBookInfo> list) {
        bookInfoAdapter.setList(list);
        bookInfoAdapter.notifyDataSetChanged();
        this.bookinfolist = list;
        swipeRefreshLayout.setRefreshing(false);
    }
}
