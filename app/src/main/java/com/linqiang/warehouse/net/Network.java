package com.linqiang.warehouse.net;

import android.content.Context;

import com.linqiang.warehouse.util.NetUtils;
import com.linqiang.warehouse.util.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

public class Network {
    public static void request(Context context, String data, StringCallback callback) {
        if (NetUtils.isConnected(context)) {
            OkHttpUtils
                    .postString()
                    .url(Constant.url)
                    .content(data)
                    .mediaType(Constant.JSON)
                    .build()
                    .execute(callback);
        } else {
            ToastUtils.show(context, "请检查网络连接");
        }
    }
}
