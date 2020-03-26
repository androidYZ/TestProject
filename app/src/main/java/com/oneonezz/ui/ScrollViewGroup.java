package com.oneonezz.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Scroller;

public class ScrollViewGroup extends ViewGroup {
    //滚动计算辅助类
    private Scroller mScroller;
    //屏幕宽度
    private int screenWidth;
    //可以移动的最大距离
    private int mMaxDistance;
    //自定义手势监听类
    private ScrollTouchLisener mTouchLisener;
    //手势监听
    private GestureDetector mDetector;

    /**
     * 使用new关键字创建对象的时候调用
     */
    public ScrollViewGroup(Context context) {
        this(context, null);
    }

    /**
     * 在XML文件中使用的时候调用
     */
    public ScrollViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * 在xml文件中调用，并且使用了自定义属性的时候调用
     */
    public ScrollViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    /**
     * 初始化方法
     * 初始化滚动辅助类Scroller以及计算出屏幕宽度
     */
    private void init(Context context) {
        //初始化辅助类
        mScroller = new Scroller(context);
        //获取屏幕宽度
        WindowManager manager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        screenWidth = outMetrics.widthPixels;
        //手势指示器初始化
        mTouchLisener = new ScrollTouchLisener();
        mDetector = new GestureDetector(context, mTouchLisener);
    }

    /**
     * 滚动时需要重写的方法，用于控制滚动
     */
    @Override
    public void computeScroll() {
        //判断滚动时候停止
        if (mScroller.computeScrollOffset()) {
            //滚动到指定的位置
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            //这句话必须写，否则不能实时刷新
            postInvalidate();
        }
    }

    /**
     * 手指触屏事件监听
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDetector.onTouchEvent(event);
        if (event.getAction() == MotionEvent.ACTION_UP) {
            this.onUp(event);
        }
        return true;
    }

    /*
     *测量方法，测量父布局的宽度和高度
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //重新设置宽高
        this.setMeasuredDimension(measureWidth(widthMeasureSpec, heightMeasureSpec), measureHeight(widthMeasureSpec, heightMeasureSpec));
    }

    /**
     * 测量宽度
     */
    private int measureWidth(int widthMeasureSpec, int heightMeasureSpec) {
        // 宽度
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        //父控件的宽（wrap_content）
        int width = 0;
        int childCount = getChildCount();

        //重新测量子view的宽度，以及最大高度
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
            int childWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            width += childWidth;
        }
        return modeWidth == MeasureSpec.EXACTLY ? sizeWidth : width;
    }

    /**
     * 测量高度
     */
    private int measureHeight(int widthMeasureSpec, int heightMeasureSpec) {
        //高度
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
        //父控件的高（wrap_content）
        int height = 0;
        int childCount = getChildCount();

        //重新测量子view的宽度，以及最大高度
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
            int childHeight = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
            height += childHeight;
        }
        height = height / childCount;
        return modeHeight == MeasureSpec.EXACTLY ? sizeHeight : height;
    }

    /**
     * 给子布局设定位置
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childLeft = 0;//子View左边的间距
        int childWidth;//子View的宽度
        int height = getHeight();//屏幕的宽度
        int childCount = getChildCount();//子View的数量
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
            childWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            child.layout(childLeft, 0, childLeft + childWidth, height);
            childLeft += childWidth;
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    /*
     *按下事件 ACTION_DOWN
     */
    public boolean onDown(MotionEvent e) {
        //如果停止滚动则取消动画（即手指按下就停止滚动）
        if (!mScroller.isFinished()) {
            mScroller.abortAnimation();
        }
        return false;
    }
    /*
    *抬起事件 ACTION_UP
    */
    public boolean onUp(MotionEvent e) {
        //得到最后一个子View
        View lastChild = getChildAt(getChildCount() - 1);
        //获取滑动的最大滑动距离（最后一个Child的右边框的坐标减去屏幕的宽度）
        int finalyChild = (int) (lastChild.getX() + lastChild.getWidth() - screenWidth);
        mMaxDistance = finalyChild;
        //如果滑动的距离小于第一个控件的最左边（0）则回弹至（0,0）点
        if (getScrollX() < 0) {
            scrollTo(0, 0);
        }
        //如果滑动的距离大于最大可滑动距离则滑动到最后一个子View
        if (getScrollX() >= finalyChild)
            scrollTo(finalyChild, 0);
        //刷新界面
        invalidate();
        return false;
    }

    /*
     *ACTION_DOWN 、短按不移动
     */
    public void onShowPress(MotionEvent e) {
    }

    /*
     *短按ACTION_DOWN、ACTION_UP
     */
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    /*
     *ACTION_DOWN 、慢滑动
     */
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        //滚动
        scrollBy((int) distanceX, 0);
        return false;
    }

    // ACTION_DOWN 、长按不滑动
    public void onLongPress(MotionEvent e) {
    }

    /*
     *ACTION_DOWN 、快滑动、 ACTION_UP
     */
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        mScroller.fling(getScrollX(), 0, (int) -velocityX, 0, 0, mMaxDistance, 0, 0);
        return false;
    }

    /**
     * 自定义手势监听类
     */
    private class ScrollTouchLisener implements GestureDetector.OnGestureListener {

        //按下事件
        @Override
        public boolean onDown(MotionEvent e) {
            return ScrollViewGroup.this.onDown(e);
        }

        //单击事件
        @Override
        public void onShowPress(MotionEvent e) {
            ScrollViewGroup.this.onShowPress(e);
        }

        //手指抬起事件
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return ScrollViewGroup.this.onSingleTapUp(e);
        }

        //滚动事件
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return ScrollViewGroup.this.onScroll(e1, e2, distanceX, distanceY);
        }

        //长按事件
        @Override
        public void onLongPress(MotionEvent e) {
            ScrollViewGroup.this.onLongPress(e);
        }

        //滑动事件
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return ScrollViewGroup.this.onFling(e1, e2, velocityX, velocityY);
        }
    }

}