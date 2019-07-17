package com.linqiang.warehouse.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.linqiang.warehouse.R;
import com.linqiang.warehouse.bean.response.SearchResultResp;

import java.util.List;

public class GoodsAdapter extends BaseAdapter {

    private Context context;

    private List<SearchResultResp.DataBean.AdmFindAdmInfoByProductCodeBean.WarehouseAdmUnitsBean> warehouseAdmUnits;

    public GoodsAdapter(Context context,List<SearchResultResp.DataBean.AdmFindAdmInfoByProductCodeBean.WarehouseAdmUnitsBean> warehouseAdmUnits) {
        this.context = context;
        this.warehouseAdmUnits = warehouseAdmUnits;
    }

    @Override
    public int getCount() {
        Log.e("getCount",warehouseAdmUnits.size()+"");
        return  warehouseAdmUnits.size();
    }

    @Override
    public Object getItem(int position) {
        Log.e("getItem","getItem is called");
        return warehouseAdmUnits.get(position);
    }

    @Override
    public long getItemId(int position) {
        Log.e("getItemId","getItemId is called");
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        Log.e("getView","getView is called");
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_detail_view, null);
            holder = new ViewHolder();
            holder.textView = convertView.findViewById(R.id.tv_goods_detail);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Log.e("warehouseAdmUnits",warehouseAdmUnits.get(position).getQuantity()+"");
        int quantity = warehouseAdmUnits.get(position).getQuantity();
        String storageNo = warehouseAdmUnits.get(position).getStorageNo();
        String skuCode = warehouseAdmUnits.get(position).getSkuCode();
        holder.textView.setText(skuCode.substring(skuCode.length() - 3) + "           "+ quantity +"          "+ storageNo );
        return convertView;
    }
    class ViewHolder {
        public TextView textView;
    }
}


