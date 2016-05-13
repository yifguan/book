/**
 * Copyright (c) 2016 Beijing Sankuai Inc.
 * <p/>
 * The right to copy, distribute, modify, or otherwise make use
 * of this software may be licensed only pursuant to the terms
 * of an applicable Beijing Sankuai license agreement.
 */
package com.demo.book.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.book.R;
import com.demo.book.bean.AVBookInfo;

import java.util.ArrayList;
import java.util.List;

public class BookInfoAdapter extends BaseAdapter {


    private Activity activity;
    private List<AVBookInfo> data = new ArrayList<AVBookInfo>();
    private static LayoutInflater inflater = null;

    public BookInfoAdapter(Activity a) {
        activity = a;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setList(List<AVBookInfo> list) {
        if (list == null || list.isEmpty()) {
            data = new ArrayList<>();
        } else {
            data = list;
        }
    }

    public void addList(AVBookInfo bean) {
        if (bean != null) {
            data.add(bean);
        }
    }

    public int getCount() {
        return data.size();
    }

    public Object getItem(int position) {
        return data.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (convertView == null)
            vi = inflater.inflate(R.layout.book_item_raw, null);

        TextView title = (TextView) vi.findViewById(R.id.title); // 标题
        TextView author = (TextView) vi.findViewById(R.id.author); // 歌手名
        TextView status = (TextView) vi.findViewById(R.id.status); // 时长
        ImageView thumb_image = (ImageView) vi.findViewById(R.id.list_image); // 缩略图

        AVBookInfo book = data.get(position);

        // 设置ListView的相关值
        title.setText(book.getName());
        author.setText(book.getAuthor());
        status.setText(book.getStatus() ? "已借出" : "正常");
        return vi;
    }
}
