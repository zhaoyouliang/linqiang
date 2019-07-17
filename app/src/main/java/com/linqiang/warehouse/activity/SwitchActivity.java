package com.linqiang.warehouse.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.linqiang.warehouse.BaseActivity;
import com.linqiang.warehouse.app.App;

public class SwitchActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (App.getInstance().isLogin()){
            startActivity(new Intent(this,MainActivity.class));
            finish();
        }else {
            startActivity(new Intent(this,LoginActivity.class));
            finish();
        }
    }
}
