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

import java.util.ArrayList;
import java.util.List;

public class GoodsInfoAdapter extends BaseAdapter {
    private Context context;
    private List<String> listGoodsBarcode = new ArrayList<>();

    /* public GoodsInfoAdapter(Context context, List<GoodsInfo> goodsInfos) {
         this.context = context;
         this.goodsInfos = goodsInfos;
     }*/
    public GoodsInfoAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return listGoodsBarcode == null ? 0 : listGoodsBarcode.size();
    }

    @Override
    public Object getItem(int position) {
        return listGoodsBarcode.get(position);
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
        String barCode = listGoodsBarcode.get(position);
        String goodsId = barCode.substring(0, barCode.length() - 3);
        String size = barCode.substring(barCode.length() - 3);
        Log.e("goodsInfos", goodsId + "---" + size);
        holder.textView.setText(goodsId + "    " + size);
        holder.editSum.setText("0");
        return convertView;
    }

    public void notifyData(List<String> listGoodsBarcode) {
        this.listGoodsBarcode = listGoodsBarcode;
        notifyDataSetChanged();
    }

    class ViewHolder {
        public TextView textView;
        public EditText editSum;
    }
}

