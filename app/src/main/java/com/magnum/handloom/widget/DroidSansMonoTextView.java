package com.magnum.handloom.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Administrator on 10/7/2017.
 */

public class DroidSansMonoTextView extends TextView {

    public DroidSansMonoTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public DroidSansMonoTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DroidSansMonoTextView(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "DroidSansMono.ttf");
            setTypeface(tf);
        }
    }

}
