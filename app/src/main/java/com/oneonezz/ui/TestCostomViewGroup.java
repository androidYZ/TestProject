package com.oneonezz.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ${Justin} on 2020/3/20.
 */
public class TestCostomViewGroup extends ViewGroup {
    public TestCostomViewGroup(Context context) {
        super(context);
    }

    public TestCostomViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TestCostomViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int count = getChildCount();
        int currentHeight = 0;
        for (int i = 0 ; i < count ; i++){
            View view = getChildAt(i);
            int height = view.getMeasuredHeight();
            int width  = view.getMeasuredWidth();
            view.layout(left, currentHeight, left + width, currentHeight + height);
            currentHeight += height;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        measureChildren(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int width     = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode= MeasureSpec.getMode(heightMeasureSpec);
        int height    = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST){
            int groupWidth = getMaxWidth();
            int groupHeight= getTotalHeight();

            setMeasuredDimension(groupWidth, groupHeight);
        }else if (widthMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(getMaxWidth(), height);
        }else if (heightMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(width, getTotalHeight());
        }
    }


    private int getMaxWidth(){
        int count = getChildCount();
        int maxWidth = 0;
        for (int i = 0 ; i < count ; i ++){
            int currentWidth = getChildAt(i).getMeasuredWidth();
            if (maxWidth < currentWidth){
                maxWidth = currentWidth;
            }
        }
        return maxWidth;
    }

    private int getTotalHeight(){
        int count = getChildCount();
        int totalHeight = 0;
        for (int i = 0 ; i < count ; i++){
            totalHeight += getChildAt(i).getMeasuredHeight();
        }
        return totalHeight;
    }
}
