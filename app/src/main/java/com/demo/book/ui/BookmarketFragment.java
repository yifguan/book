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
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.avos.avoscloud.AVUser;
import com.demo.book.R;
import com.demo.book.adapter.BookInfoAdapter;
import com.demo.book.bean.AVBookInfo;
import com.demo.book.presenter.BookPresenter;
import com.demo.book.service.StatusService;

import java.util.List;

public class BookmarketFragment extends BaseFragment implements BookPresenter.UIView, AdapterView.OnItemClickListener {
    private View mFragmentView;
    private boolean isPrepared;

    private SwipeRefreshLayout swipeRefreshLayout;
    private BookPresenter mBookPresenter;
    private BookInfoAdapter bookInfoAdapter;
    private ListView bookList;
    private List<AVBookInfo> bookinfolist;
    private AVUser avUser;
    private StatusService statusService;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mFragmentView == null) {
            mFragmentView = inflater.inflate(R.layout.fragment_bookmarket, container, false);
            mBookPresenter = new BookPresenter(getActivity(), this);
            bookInfoAdapter = new BookInfoAdapter(getActivity());
            statusService = StatusService.getInstance();
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
                mBookPresenter.getAllBookButOwner(avUser.getUsername());
            }
        });

        bookInfoAdapter = new BookInfoAdapter(getActivity());
        bookList = (ListView) mFragmentView.findViewById(R.id.bookmarketlist);
        bookList.setAdapter(bookInfoAdapter);
        if (bookinfolist != null)
            bookInfoAdapter.setList(bookinfolist);
        bookList.setOnItemClickListener(this);

        return mFragmentView;
    }

    void initDatas() {
        avUser = statusService.getUser();
        mBookPresenter.getAllBookButOwner(avUser.getUsername());
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
    public void updateList(List<AVBookInfo> list) {
        bookInfoAdapter.setList(list);
        bookInfoAdapter.notifyDataSetChanged();
        this.bookinfolist = list;
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void logout() {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ListView listView = (ListView) parent;
        AVBookInfo bookInfo = (AVBookInfo) listView.getItemAtPosition(position);
        Bundle bundle = new Bundle();
        bundle.putParcelable("bookinfo", bookInfo);
        Intent intent = new Intent(getActivity(), BookDetailActivity.class);
        intent.putExtra("bundle", bundle);
        startActivityForResult(intent, 0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 0) {
            mBookPresenter.getAllBookButOwner(avUser.getUsername());
        }
    }
}
