package com.oneonezz.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.oneonezz.R;


/**
 * Created by ${Justin} on 2019/10/25.
 */
public class MyPercentConstraintLayout extends ConstraintLayout {
    private float percent;

    public MyPercentConstraintLayout(Context context) {
        this(context,null);
    }

    public MyPercentConstraintLayout(Context context, AttributeSet attrs) {
        this(context, attrs,1);
    }

    public MyPercentConstraintLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MyPercentConstraintLayout);
        percent = ta.getFloat(R.styleable.MyPercentConstraintLayout_percent, 1);
        ta.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = View.MeasureSpec.getSize(widthMeasureSpec);
        widthMeasureSpec = View.MeasureSpec.makeMeasureSpec((int) (width * percent), View.MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


}
