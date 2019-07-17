package com.linqiang.warehouse.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.linqiang.warehouse.R;
import com.zhy.http.okhttp.callback.StringCallback;

public class IconEditText extends RelativeLayout {
    private Bitmap mImage;
    private EditText editText;
    private ImageView imageView;
    private String hintText;

    public IconEditText(Context context) {
        this(context, null);
    }

    public IconEditText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IconEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_icon_edittext, this);
        editText = view.findViewById(R.id.edit_text);
        imageView = view.findViewById(R.id.img_icon);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.IconEditText, defStyleAttr, 0);
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.IconEditText_hintText:
                    hintText = a.getString(attr);
                    break;
                case R.styleable.IconEditText_iconLeft:
                    mImage = BitmapFactory.decodeResource(getResources(), a.getResourceId(attr, 0));
                    break;
            }
        }
        a.recycle();

        editText.setHint(hintText);
        imageView.setImageBitmap(mImage);

    }

    public void setText(String text) {
        editText.setText(text);
    }
    public void setPwdText(String text) {
        editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
        editText.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
        editText.setText(text);
    }

    public String getText() {
        return editText.getText().toString();
    }
}
