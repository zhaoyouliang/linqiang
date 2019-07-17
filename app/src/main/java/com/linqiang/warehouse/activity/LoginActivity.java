package com.linqiang.warehouse.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.linqiang.warehouse.BaseActivity;
import com.linqiang.warehouse.R;
import com.linqiang.warehouse.app.App;
import com.linqiang.warehouse.bean.request.LoginReq;
import com.linqiang.warehouse.bean.response.LoginResp;
import com.linqiang.warehouse.net.Network;
import com.linqiang.warehouse.util.SPUtils;
import com.linqiang.warehouse.util.ToastUtils;
import com.linqiang.warehouse.view.IconEditText;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.account)
    IconEditText account;
    @BindView(R.id.password)
    IconEditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        account.setText("test");
        password.setPwdText("123456");
    }

    public void login(View view) {
        LoginReq.VariablesBean variablesBean = new LoginReq.VariablesBean();
        variablesBean.setWeChat(account.getText());
        variablesBean.setPassword(password.getText());
        LoginReq loginReq = new LoginReq();
        loginReq.setQuery("query ($password: String, $weChat: String) {\n  admAppLogin(password: $password, weChat: $weChat) {\n    name\n    sessionToken\n  }\n}\n");
        loginReq.setVariables(variablesBean);
        String data = new Gson().toJson(loginReq);
        Log.e("loginReq", data);

        Network.request(this, data, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("response failed", e.toString());

            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("response success", response);
                Gson gson = new Gson();
                LoginResp loginResp = gson.fromJson(response, LoginResp.class);
                LoginResp.DataBean.AdmAppLoginBean admAppLogin = loginResp.getData().getAdmAppLogin();
                if (loginResp != null && admAppLogin != null) {
                    SPUtils.put(getApplicationContext(), "SessionToken", admAppLogin.getSessionToken());
                    App.getInstance().setLogin(true);
                    openActivity(MainActivity.class);
                    finish();
                } else {
                    ToastUtils.show(getApplicationContext(), "登陆失败");
                }
            }
        });
    }


}
