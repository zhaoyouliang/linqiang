package com.linqiang.warehouse.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.linqiang.warehouse.R;
import com.linqiang.warehouse.bean.GoodsInfo;

import java.util.List;

public class GoodsInfoAdapter extends BaseAdapter {
    private Context context;
    private List<GoodsInfo> goodsInfos;

    public GoodsInfoAdapter(Context context, List<GoodsInfo> goodsInfos) {
        this.context = context;
        this.goodsInfos = goodsInfos;
    }

    @Override
    public int getCount() {
        return goodsInfos.size();
    }

    @Override
    public Object getItem(int position) {
        return goodsInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_detail_view, null);
            holder = new ViewHolder();
            holder.textView = convertView.findViewById(R.id.tv_goods_detail);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Log.e("goodsInfos", goodsInfos.get(position).getGoodsNumber() + "");
        holder.textView.setText(goodsInfos.get(position).getGoodsNumber()+"    "+ goodsInfos.get(position).getGoodsSize());
        holder.editSum.setText(0);
        return convertView;
    }

    class ViewHolder {
        public TextView textView;
        public EditText editSum;
    }
}

