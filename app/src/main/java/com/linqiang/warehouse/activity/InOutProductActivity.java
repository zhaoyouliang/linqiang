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
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.linqiang.warehouse.BaseActivity;
import com.linqiang.warehouse.R;
import com.linqiang.warehouse.adapter.GoodsInfoAdapter;
import com.linqiang.warehouse.bean.request.ProductSubmitReq;
import com.linqiang.warehouse.bean.response.InOutBean;
import com.linqiang.warehouse.net.Network;
import com.linqiang.warehouse.util.ToastUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class InOutProductActivity extends BaseActivity {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.mid_title)
    TextView midTitle;
    @BindView(R.id.submit)
    Button submit;
    @BindView(R.id.lib_number)
    TextView libNumber;
    @BindView(R.id.listView)
    ListView listView;
    GoodsInfoAdapter adapter;
    private Vibrator mVibrator;
    private ScanManager mScanManager;
    private SoundPool soundpool = null;
    private int soundid;
    private String barcodeStr;
    private boolean isScaning = false;
    private final static String SCAN_ACTION = ScanManager.ACTION_DECODE;//default action
    private int enterType, outType;
    private String storageNo, goodsBarcode;
    private List<String> listGoodsBarcode = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inout_product);
        ButterKnife.bind(this);
        submit.setVisibility(View.VISIBLE);
        mVibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        enterType = getIntent().getExtras().getInt("enterType");
        outType = getIntent().getExtras().getInt("outType");
        setTitle();
        View headView = getLayoutInflater().inflate(R.layout.item_goods_sums, null);
        adapter = new GoodsInfoAdapter();
        listView.addHeaderView(headView);
        listView.setAdapter(adapter);
    }

    /**
     * 设置title
     */
    private void setTitle() {
        if (enterType == 0) {
            midTitle.setText("出库产品清单");
        } else {
            midTitle.setText("入库产品清单");

        }
    }

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
            if (barcodeStr.length() == 10) {
                storageNo = barcodeStr;
            } else if (barcodeStr.length() == 9 || barcodeStr.length() == 12) {
                if (!TextUtils.isEmpty(storageNo)) {
                    goodsBarcode = barcodeStr;
                    listGoodsBarcode.add(goodsBarcode);
                } else {
                    ToastUtils.show(InOutProductActivity.this, "请先扫描库位号");
                }
            } else {
                ToastUtils.show(InOutProductActivity.this, "编码错误");
            }
            if (storageNo != null)
                libNumber.setText("库位号: " + storageNo);
            Log.e("barCode ", barcodeStr);
        }
    };

    @OnClick({R.id.back, R.id.submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                break;
            case R.id.submit:
                if (enterType == 0 && outType == 1) {
                    //零售出库
                    requestSubmit(0, 1);
                } else if (enterType == 0 && outType == 2) {
                    //大货出库
                    requestSubmit(0, 2);
                } else if (enterType == 0 && outType == 3) {
                    //调拨出库
                    requestSubmit(0, 3);
                } else if (enterType == 0 && outType == 4) {
                    //报废出库
                    requestSubmit(0, 4);
                } else if (enterType == 0 && outType == 5) {
                    //其他出库
                    requestSubmit(0, 5);
                } else if (enterType == 1 && outType == 0) {
                    //大货入库
                    requestSubmit(1, 0);
                } else if (enterType == 2 && outType == 0) {
                    //调拨入库
                    requestSubmit(2, 0);
                } else if (enterType == 3 && outType == 0) {
                    requestSubmit(3, 0);
                    //其他入库
                }
                break;
        }
    }

    private void requestSubmit(int enterType, int outType) {
        ProductSubmitReq.VariablesBean variablesBean = new ProductSubmitReq.VariablesBean();
        ProductSubmitReq.VariablesBean.ProductStockUnitInfosBean unitInfosBean = new ProductSubmitReq.VariablesBean.ProductStockUnitInfosBean();
        variablesBean.setToken("97A0FC12B70423A202602D986E5296CD");
        variablesBean.setStorageNo("A01Z01C01");
        variablesBean.setEnterType(enterType);
        variablesBean.setOperationMan("测试员");
        variablesBean.setOutType(outType);

        unitInfosBean.setCode("K1N19001160");
        unitInfosBean.setQuantity(1000);
        unitInfosBean.setStatus(1);

        List<ProductSubmitReq.VariablesBean.ProductStockUnitInfosBean> productStockUnitInfos = new ArrayList<>();
        productStockUnitInfos.add(unitInfosBean);
        variablesBean.setProductStockUnitInfos(productStockUnitInfos);

        Log.e("variablesBean", variablesBean.toString());

        ProductSubmitReq submitReq = new ProductSubmitReq();
        submitReq.setVariables(variablesBean);
        submitReq.setQuery("mutation ($storageNo: String, $enterType: Int, $operationMan: String, $outType: Int, $productStockUnitInfos: [ProductStockUnitInfoInput], $token: String) {admEnterOrOutSkuUnitinfo(storageNo: $storageNo, enterType: $enterType, operationMan: $operationMan, outType: $outType, productStockUnitInfos: $productStockUnitInfos, token: $token) {message warehouseAdmUnits {skuCode  quantity    currentQuantity   }  }}");
        String data = new Gson().toJson(submitReq);
        Network.request(this, data, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("response failed", e.toString());

            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("response success", response);
                Gson gson = new Gson();
                InOutBean inOutBean = gson.fromJson(response, InOutBean.class);
                if (inOutBean.getData().getAdmEnterOrOutSkuUnitinfo().getMessage() == null) {
                    ToastUtils.show(InOutProductActivity.this, "出库成功");
                } else {
                    ToastUtils.show(InOutProductActivity.this, "出库失败," + inOutBean.getData().getAdmEnterOrOutSkuUnitinfo().getMessage());

                }
            }
        });
    }

    private void initScan() {
        mScanManager = new ScanManager();
        mScanManager.openScanner();
        mScanManager.switchOutputMode(0);
        soundpool = new SoundPool(1, AudioManager.STREAM_NOTIFICATION, 100); // MODE_RINGTONE
        soundid = soundpool.load("/etc/Scan_new.ogg", 1);
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
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mScanReceiver);
    }
}
