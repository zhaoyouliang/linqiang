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
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.linqiang.warehouse.BaseActivity;
import com.linqiang.warehouse.R;
import com.linqiang.warehouse.util.SPUtils;
import com.linqiang.warehouse.view.SearchView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    @BindView(R.id.header)
    ImageView header;
    @BindView(R.id.receive)
    Button receive;
    @BindView(R.id.output)
    Button output;
    @BindView(R.id.transfer)
    Button transfer;
    private final static String SCAN_ACTION = ScanManager.ACTION_DECODE;//default action
    @BindView(R.id.search_view)
    SearchView searchView;
    @BindView(R.id.exitLogin)
    Button exitLogin;
    private Vibrator mVibrator;
    private ScanManager mScanManager;
    private SoundPool soundpool = null;
    private int soundid;
    private String barcodeStr;
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
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mVibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
    }

    private void initScan() {
        mScanManager = new ScanManager();
        mScanManager.openScanner();
        mScanManager.switchOutputMode(0);
        soundpool = new SoundPool(1, AudioManager.STREAM_NOTIFICATION, 100); // MODE_RINGTONE
        soundid = soundpool.load("/etc/Scan_new.ogg", 1);
    }


    @OnClick({R.id.receive, R.id.output, R.id.transfer, R.id.exitLogin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.receive:
                openActivity(ReceivingActivity.class);
                break;
            case R.id.output:
                openActivity(OutputActivity.class);
                break;
            case R.id.transfer:
                break;
            case R.id.exitLogin:
                SPUtils.clear(MainActivity.this);
                openActivity(LoginActivity.class);
                finish();
                break;
        }
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
