package com.linqiang.warehouse.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.linqiang.warehouse.R;

import butterknife.BindView;
import butterknife.OnClick;

public class SearchView extends RelativeLayout {

    private EditText editCode;

    public SearchView(Context context) {
        this(context, null);
    }

    public SearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_searchview, this);
        editCode = view.findViewById(R.id.edit_code);
    }

    public void setText(String text){
        editCode.setText(text);
    }

}
