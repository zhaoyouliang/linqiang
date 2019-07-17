package com.linqiang.warehouse.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.linqiang.warehouse.BaseActivity;
import com.linqiang.warehouse.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReceivingActivity extends BaseActivity {
    @BindView(R.id.back)
    ImageView mBack;
    @BindView(R.id.mid_title)
    TextView mTitle;
    @BindView(R.id.submit)
    Button mSubmit;
    private Bundle bundle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiving);
        ButterKnife.bind(this);
        mTitle.setText("入库管理");
        bundle = new Bundle();
        bundle.putInt("outType", 0);
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }

    @OnClick({R.id.largeRec, R.id.exitRec, R.id.otherRec})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.largeRec:
                bundle.putInt("enterType", 1);
                openActivity(InOutProductActivity.class, bundle);
                break;
            case R.id.exitRec:
                bundle.putInt("enterType", 2);
                openActivity(InOutProductActivity.class, bundle);
                break;
            case R.id.otherRec:
                bundle.putInt("enterType", 3);
                openActivity(InOutProductActivity.class, bundle);
                break;
        }
    }
}
