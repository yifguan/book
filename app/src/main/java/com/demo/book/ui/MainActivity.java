/**
 * Copyright (c) 2016 Beijing Sankuai Inc.
 * <p/>
 * The right to copy, distribute, modify, or otherwise make use
 * of this software may be licensed only pursuant to the terms
 * of an applicable Beijing Sankuai license agreement.
 */
package com.demo.book.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.avos.avoscloud.AVAnalytics;
import com.demo.book.R;
import com.demo.book.utils.ExitAppUtils;

public class MainActivity extends FragmentActivity {
    private FragmentTabHost mTabHost;
    private LayoutInflater layoutInflater;

    final class MainTap{
        String tabTxt;
        Class fragClass;
        int imgId;
        int pressColor;
        int unpressColor;
        View view;

        public MainTap(String tabTxt, Class fragClass, int imgId, int pressColor, int unpressColor) {
            this.tabTxt = tabTxt;
            this.imgId = imgId;
            this.fragClass = fragClass;
            this.pressColor = pressColor;
            this.unpressColor = unpressColor;
        }

        public void setView(View v) {
            view = v;
        }
    }

    private MainTap[] mTabIds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        ExitAppUtils.getInstance().addActivity(this);
        initView();
        AVAnalytics.trackAppOpened(getIntent());

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ExitAppUtils.getInstance().delActivity(this);
    }

    private void initView(){
        mTabIds = new MainTap[] {
                new MainTap(getString(R.string.tab_bookcase),BookcaseFragment.class,
                        R.drawable.tab_bookcase_normal, getResources().getColor(R.color.tab_press), getResources().getColor(R.color.tab_unpress)),
                new MainTap(getString(R.string.tab_bookmarket),BookmarketFragment.class,
                        R.drawable.tab_bookmarket_normal, getResources().getColor(R.color.tab_press), getResources().getColor(R.color.tab_unpress)),
        };

        layoutInflater = LayoutInflater.from(this);
        mTabHost = (FragmentTabHost)findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        mTabHost.getTabWidget().setDividerDrawable(null);
        final int count = mTabIds.length;

        for(int i = 0; i < count; i++){
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(mTabIds[i].tabTxt).setIndicator(getTabItemView(i));
            mTabHost.addTab(tabSpec, mTabIds[i].fragClass, null);
        }

        for(int i =0;i<count;i++) {                          //初始化底部tabbar为蓝色，其他tabbar为灰色；
            if (i == 0) {
                ((ImageView) mTabIds[i].view.findViewById(R.id.imageview)).setColorFilter(mTabIds[i].pressColor);
                ((TextView) mTabIds[i].view.findViewById(R.id.textview)).setTextColor(mTabIds[i].pressColor);
            }else {
                ((ImageView) mTabIds[i].view.findViewById(R.id.imageview)).setColorFilter(mTabIds[i].unpressColor);
                ((TextView) mTabIds[i].view.findViewById(R.id.textview)).setTextColor(mTabIds[i].unpressColor);
            }
        }
        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                for (int i = 0; i < count; i++) {
                    if (!mTabIds[i].tabTxt.equals(tabId)) {
                        ((ImageView) mTabIds[i].view.findViewById(R.id.imageview)).setColorFilter(mTabIds[i].unpressColor);
                        ((TextView) mTabIds[i].view.findViewById(R.id.textview)).setTextColor(mTabIds[i].unpressColor);
                    } else {
                        ((ImageView) mTabIds[i].view.findViewById(R.id.imageview)).setColorFilter(mTabIds[i].pressColor);
                        ((TextView) mTabIds[i].view.findViewById(R.id.textview)).setTextColor(mTabIds[i].pressColor);
                    }
                }
            }
        });
    }

    private View getTabItemView(int index){
        View view = layoutInflater.inflate(R.layout.tab_item_view, null);

        ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
        imageView.setImageResource(mTabIds[index].imgId);

        TextView textView = (TextView) view.findViewById(R.id.textview);
        textView.setText(mTabIds[index].tabTxt);
        mTabIds[index].setView(view);
        return view;
    }

    @Override
    public void onBackPressed()
    {
        moveTaskToBack(true);     //退回到桌面，将程序放到后台，并不finish；
    }



}
