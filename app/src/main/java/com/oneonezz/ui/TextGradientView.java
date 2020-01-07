package com.oneonezz.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.oneonezz.R;

public class TextGradientView extends TextView {

    private Paint mPaint;
    private int mViewHeight = 0;
    private int mViewWidth = 0;
    private Rect mTextBound = new Rect();
    private int startColor;
    private int endColor;
    private boolean isLeftToRight;
    private boolean isTopToBottom;
    LinearGradient mLinearGradient;

    public TextGradientView(Context context) {
        super(context);
    }

    public TextGradientView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TextGradientView);
        initParams(context, typedArray);
        typedArray.recycle();
    }

    private void initParams(Context context, TypedArray typedArray) {
        startColor = typedArray.getColor(R.styleable.TextGradientView_start_color, Color.parseColor("#FF4A53D8"));
        endColor = typedArray.getColor(R.styleable.TextGradientView_end_color, Color.parseColor("#FFC94DD4"));
        isLeftToRight = typedArray.getBoolean(R.styleable.TextGradientView_left_to_right, true);
        isTopToBottom = typedArray.getBoolean(R.styleable.TextGradientView_top_to_bottom, false);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint = getPaint();
        String mTipText = getText().toString();
        mPaint.getTextBounds(mTipText, 0, mTipText.length(), mTextBound);
        mPaint.setShader(mLinearGradient);
        canvas.drawText(mTipText,
                (float) (getMeasuredWidth() / 2.0 - mTextBound.width() / 2.0),
                (float) (getMeasuredHeight() / 2.0 + mTextBound.height() / 2.0), mPaint);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (mViewHeight == 0) {
            mViewHeight = getMeasuredHeight();
            mViewWidth = getMeasuredWidth();
            if (mViewHeight > 0) {
                if (isTopToBottom) {
                    mLinearGradient = new LinearGradient(0, 0,
                            0, mViewHeight, new int[]{startColor, endColor}, null, Shader.TileMode.CLAMP);
                } else {
                    mLinearGradient = new LinearGradient(0, 0,
                            mViewWidth, 0, new int[]{startColor, endColor}, null, Shader.TileMode.CLAMP);
                }
            }
        }
    }


}