package com.text.rexiufu.behavior;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.text.rexiufu.R;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends BaseAdapter {
    private List<String> aaa = new ArrayList<>();
    private LayoutInflater inflater;

    public ListAdapter(List<String> mStudentDataList, Context context) {
        this.aaa = mStudentDataList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return aaa == null ? 0 : aaa.size();  //判断有说个Item
    }

    @Override
    public Object getItem(int position) {
        return aaa.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //加载布局为一个视图
        View view = inflater.inflate(R.layout.listview_item, null);
        //在view 视图中查找 组件
        TextView tv_name = view.findViewById(R.id.text_name);
        //为Item 里面的组件设置相应的数据
        tv_name.setText(aaa.get(position));
        //返回含有数据的view
        return view;
    }
}