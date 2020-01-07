package com.oneonezz.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

import androidx.annotation.Nullable;

/**
 * Created by Ronny on 2018/8/2.
 */

@SuppressLint("AppCompatCustomView")
public class MoveImageView extends ImageView {

    private int parentHeight;
    private int parentWidth;
    private float lastX, lastY;
    private int left;
    private int bottom;
    private int viewWidth;
    private int viewWHeight;

    public MoveImageView(Context context) {
        super(context);
    }

    public MoveImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MoveImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int TouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = event.getRawX();
                lastY = event.getRawY();
                ViewGroup.MarginLayoutParams layoutParamsTemp = (ViewGroup.MarginLayoutParams) getLayoutParams();
                left = layoutParamsTemp.leftMargin;
                bottom = layoutParamsTemp.bottomMargin;
                viewWidth = getMeasuredWidth();
                viewWHeight = getMeasuredHeight();
                break;
            case MotionEvent.ACTION_MOVE:
                float offsetX = event.getRawX() - lastX;//计算滑动的距离
                float offsetY = event.getRawY() - lastY;

                if (Math.abs(offsetX) < TouchSlop && Math.abs(offsetY) < TouchSlop) {
                    break;
                }

                ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) getLayoutParams();
                layoutParams.leftMargin = (int) (left + offsetX);
                layoutParams.bottomMargin = (int) (bottom - offsetY);

                if (layoutParams.leftMargin < 0) layoutParams.leftMargin = 0;
                if (layoutParams.leftMargin > (parentWidth - viewWidth))
                    layoutParams.leftMargin = (parentWidth - viewWidth);
                if (layoutParams.bottomMargin < 0) layoutParams.bottomMargin = 0;
                if (layoutParams.bottomMargin > parentHeight - viewWHeight)
                    layoutParams.bottomMargin = parentHeight - viewWHeight;
                setLayoutParams(layoutParams);

                MotionEvent cancelEvent = MotionEvent.obtain(event);
                cancelEvent.setAction(MotionEvent.ACTION_CANCEL | (event.getActionIndex() << MotionEvent.ACTION_POINTER_INDEX_SHIFT));
                onTouchEvent(cancelEvent);
                cancelEvent.recycle();
                return true;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        ViewParent parent = getParent();
        //获取父控件的矩阵
        if (parent instanceof ViewGroup) {
            ViewGroup parentView = (ViewGroup) parent;
            parentHeight = parentView.getHeight();
            parentWidth = parentView.getWidth();
        }
    }

}
