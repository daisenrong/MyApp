package com.dsr.tuling;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2015/12/13.
 */
public class Textadapter extends BaseAdapter {
    private List<ListData> lists;
    private Context context;
    private RelativeLayout layout;

    public Textadapter(List<ListData> lists, Context context) {
        this.lists = lists;
        this.context = context;
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        if (lists.get(position).getFlag() == ListData.RECEIVER) {
            layout = (RelativeLayout) inflater.inflate(R.layout.left_item, null);
        }
        if (lists.get(position).getFlag() == ListData.SEND) {
            layout = (RelativeLayout) inflater.inflate(R.layout.right_item, null);
        }
        TextView tv= (TextView) layout.findViewById(R.id.tv);
        tv.setText(lists.get(position).getContent());



        return layout;
    }
}
