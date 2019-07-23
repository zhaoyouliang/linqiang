package com.linqiang.warehouse.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.device.ScanManager;
import android.device.scanner.configuration.PropertyID;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.linqiang.warehouse.BaseActivity;
import com.linqiang.warehouse.R;
import com.linqiang.warehouse.adapter.GoodsAdapter;
import com.linqiang.warehouse.bean.request.SearchResultReq;
import com.linqiang.warehouse.bean.response.SearchResultResp;
import com.linqiang.warehouse.net.Network;
import com.linqiang.warehouse.util.ToastUtils;
import com.linqiang.warehouse.view.SearchView;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

public class SearchResultActivity extends BaseActivity {
    @BindView(R.id.search_view)
    SearchView searchView;
    @BindView(R.id.title)
    View title;
    @BindView(R.id.listView)
    ListView listView;
    private String barcodeStr;
    private ImageView goodsImg;
    private GoodsAdapter adapter;
    private TextView goodsId, goodsName, goodsSex;
    private List<SearchResultResp.DataBean.AdmFindAdmInfoByProductCodeBean.WarehouseAdmUnitsBean> warehouseAdmUnits;
    private View headView;
    private final static String SCAN_ACTION = ScanManager.ACTION_DECODE;//default action
    private Vibrator mVibrator;
    private ScanManager mScanManager;
    private SoundPool soundpool = null;
    private int soundid;
    private boolean isScaning = false;
    private BroadcastReceiver mScanReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            isScaning = false;
            soundpool.play(soundid, 1, 1, 0, 0, 1);
            mVibrator.vibrate(100);

            byte[] barcode = intent.getByteArrayExtra(ScanManager.DECODE_DATA_TAG);
            int barcodelen = intent.getIntExtra(ScanManager.BARCODE_LENGTH_TAG, 0);
            byte temp = intent.getByteExtra(ScanManager.BARCODE_TYPE_TAG, (byte) 0);
            Log.i("debug", "----codetype--" + temp);
            barcodeStr = new String(barcode, 0, barcodelen);
            Log.e("barCode ", barcodeStr);
            searchView.setText(barcodeStr != null ? barcodeStr : "");
            Bundle bundle = new Bundle();
            bundle.putString("barcodeStr", barcodeStr);
            openActivity(SearchResultActivity.class, bundle);
            finish();
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_detail);
        ButterKnife.bind(this);
        mVibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        barcodeStr = getIntent().getExtras().getString("barcodeStr");
        searchView.setText(barcodeStr);
        requestSearchResult();
        headView = getLayoutInflater().inflate(R.layout.item_goods_header, null);
        goodsId = headView.findViewById(R.id.goodsId);
        goodsName = headView.findViewById(R.id.goodsName);
        goodsSex = headView.findViewById(R.id.goodsSex);
        goodsImg = headView.findViewById(R.id.img);
    }

    private void initScan() {
        mScanManager = new ScanManager();
        mScanManager.openScanner();
        mScanManager.switchOutputMode(0);
        soundpool = new SoundPool(1, AudioManager.STREAM_NOTIFICATION, 100); // MODE_RINGTONE
        soundid = soundpool.load("/etc/Scan_new.ogg", 1);
    }

    private void requestSearchResult() {
        SearchResultReq.VariablesBean variablesBean = new SearchResultReq.VariablesBean();
        variablesBean.setSkuCode(barcodeStr);
        variablesBean.setToken("97A0FC12B70423A202602D986E5296CD");
        SearchResultReq searchResultReq = new SearchResultReq();
        searchResultReq.setVariables(variablesBean);
        searchResultReq.setQuery("query ($skuCode: String, $token: String) { admFindAdmInfoByProductCode(skuCode: $skuCode, token: $token) { productInfo { code gender title picUrl } warehouseAdmUnits {quantity skuCode storageNo} }}");
        String data = new Gson().toJson(searchResultReq);
        Log.e("searchResultReq", data);
        Network.request(this, data, new StringCallback() {

            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("response failed", e.toString());

            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("response success", response);
                Gson gson = new Gson();
                SearchResultResp resultResp = gson.fromJson(response, SearchResultResp.class);
                if (resultResp.getData().getAdmFindAdmInfoByProductCode() == null){
                    ToastUtils.show(SearchResultActivity.this,"不存在该商品");
                    return;
                }
                SearchResultResp.DataBean.AdmFindAdmInfoByProductCodeBean.ProductInfoBean productInfo = resultResp.getData().getAdmFindAdmInfoByProductCode().getProductInfo();
                warehouseAdmUnits = resultResp.getData().getAdmFindAdmInfoByProductCode().getWarehouseAdmUnits();

//                Log.e("warehouseAdmUnits___",warehouseAdmUnits.get(0).toString());
                String gender = null;
                if (productInfo.getGender().equals("N")) {
                    gender = "中性";
                } else if (productInfo.getGender().equals("B")) {
                    gender = "男性";
                } else if (productInfo.getGender().equals("G")) {
                    gender = "女性";
                }
                goodsId.setText("货号: " + productInfo.getCode());
                goodsName.setText("名称: " + productInfo.getTitle());
                goodsSex.setText("性别: " + gender);
                Glide.with(SearchResultActivity.this).load(productInfo.getPicUrl()).into(goodsImg);
                adapter = new GoodsAdapter(SearchResultActivity.this, warehouseAdmUnits);
                listView.addHeaderView(headView);
                listView.setAdapter(adapter);
                //这里的Listview有一种多item的情况，就是货号一样，但是放的库位一样，这时候需要合并，效果图到时候我给你
            }
        });
    }

    public void back(View view) {
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mScanReceiver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initScan();
        IntentFilter filter = new IntentFilter();
        int[] idbuf = new int[]{PropertyID.WEDGE_INTENT_ACTION_NAME, PropertyID.WEDGE_INTENT_DATA_STRING_TAG};
        String[] value_buf = mScanManager.getParameterString(idbuf);
        if (value_buf != null && value_buf[0] != null && !value_buf[0].equals("")) {
            filter.addAction(value_buf[0]);
        } else {
            filter.addAction(SCAN_ACTION);
        }
        registerReceiver(mScanReceiver, filter);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }
}
