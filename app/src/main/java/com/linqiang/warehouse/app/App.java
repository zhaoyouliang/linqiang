package com.linqiang.warehouse.app;

import android.app.Application;
import android.util.Log;

import com.linqiang.warehouse.util.SPUtils;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;


public class App extends Application {
    private static App app;
    private boolean isLogin = false;
    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);
    }

    public static App getInstance() {
        return app;
    }

    public boolean isLogin() {

        if (SPUtils.contains(this, "isLogin")) {
            boolean isLogin = (Boolean) SPUtils.get(getApplicationContext(), "isLogin", false);
            return isLogin;
        } else{
            return false;
        }
    }

    public void setLogin(boolean isLogin) {
        SPUtils.put(this, "isLogin", isLogin);
        this.isLogin = isLogin;
    }
}
