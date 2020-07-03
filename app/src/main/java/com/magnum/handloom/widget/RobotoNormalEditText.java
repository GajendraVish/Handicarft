package com.magnum.handloom.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by Alka on 7/26/2016.
 */
public class RobotoNormalEditText extends EditText {

    public RobotoNormalEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public RobotoNormalEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RobotoNormalEditText(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "NotoSans-Regular.ttf");
            setTypeface(tf);
        }
    }

}
