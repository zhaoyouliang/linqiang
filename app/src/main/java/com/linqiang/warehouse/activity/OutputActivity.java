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

public class OutputActivity extends BaseActivity {
    @BindView(R.id.mid_title)
    TextView mTitle;
    @BindView(R.id.retailOutput)
    Button retailOutput;
    @BindView(R.id.largeOutput)
    Button largeOutput;
    @BindView(R.id.transferOutput)
    Button transferOutput;
    @BindView(R.id.scrapOutput)
    Button scrapOutput;
    @BindView(R.id.otherOutput)
    Button otherOutput;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.submit)
    Button submit;
    private Bundle bundle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output);
        ButterKnife.bind(this);
        mTitle.setText("出库管理");
        bundle = new Bundle();
        bundle.putInt("enterType", 0);
    }


    @OnClick({R.id.back, R.id.retailOutput, R.id.largeOutput, R.id.transferOutput, R.id.scrapOutput, R.id.otherOutput})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.retailOutput:
                bundle.putInt("outType", 1);
                openActivity(InOutProductActivity.class,bundle);
                break;
            case R.id.largeOutput:
                bundle.putInt("outType", 2);
                openActivity(InOutProductActivity.class,bundle);
                break;
            case R.id.transferOutput:
                bundle.putInt("outType", 3);
                openActivity(InOutProductActivity.class,bundle);
                break;
            case R.id.scrapOutput:
                bundle.putInt("outType", 4);
                openActivity(InOutProductActivity.class,bundle);
                break;
            case R.id.otherOutput:
                bundle.putInt("outType", 5);
                openActivity(InOutProductActivity.class,bundle);
                break;
        }
    }
}
