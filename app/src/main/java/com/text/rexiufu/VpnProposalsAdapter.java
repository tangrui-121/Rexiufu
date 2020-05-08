package com.text.rexiufu;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class VpnProposalsAdapter extends BaseAdapter {
    private Context ctx;


    private List<String> dataList;

    public VpnProposalsAdapter(Context ctx, List<String> dataList) {
        this.ctx = ctx;
        this.dataList = dataList;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public String getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(ctx, R.layout.item_pop, null);
            new ViewHolder(convertView);
        }
        ViewHolder holder = (ViewHolder) convertView.getTag();
        holder.name.setText(getItem(position));
        return convertView;
    }

    class ViewHolder {
        TextView name;

        public ViewHolder(View convertView) {
            name = convertView.findViewById(R.id.text_pop);
            convertView.setTag(this);
        }
    }
}
