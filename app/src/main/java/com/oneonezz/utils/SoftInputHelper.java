package com.oneonezz.utils;

import android.app.Activity;
import android.app.Application;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

public class SoftInputHelper implements LifecycleObserver,
        Application.ActivityLifecycleCallbacks,
        ViewTreeObserver.OnGlobalLayoutListener {

    private static final String TAG = SoftInputHelper.class.getSimpleName();

    int bottom;

    boolean visible;

    Rect rect;

    OnSoftInputListener onSoftInputListener;

    FragmentActivity context;

    public SoftInputHelper(FragmentActivity context) {
        this.context = context;

        this.bottom = 0;
        this.visible = false;

        this.rect = new Rect();

        context.getLifecycle().addObserver(this);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate() {
        context.getApplication().registerActivityLifecycleCallbacks(this);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart() {
        ViewTreeObserver observer = getViewTreeObserver(context);
        if (observer != null && observer.isAlive()) {
            observer.addOnGlobalLayoutListener(this);
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop() {
        ViewTreeObserver observer = getViewTreeObserver(context);
        if (observer != null && observer.isAlive()) {
            observer.removeOnGlobalLayoutListener(this);
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        context.getApplication().unregisterActivityLifecycleCallbacks(this);

        context.getLifecycle().removeObserver(this);
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {
        this.updateBottom(activity);
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }

    @Override
    public void onGlobalLayout() {

        {
            this.updateBottom(context);
        }

        View target = getView(context);
        if (target != null) {

            target.getGlobalVisibleRect(rect);
            boolean result = rect.bottom < bottom;

            if (this.visible ^ result) {
                this.visible = result;

                if (onSoftInputListener != null) {
                    onSoftInputListener.onSoftInputChanged(this, visible);
                }
            }

        }
    }

    public int getBottom() {
        return bottom;
    }

    public void setOnSoftInputListener(OnSoftInputListener listener) {
        this.onSoftInputListener = listener;
    }

    void updateBottom(Activity activity) {
        View view = getView(activity);
        if (view != null) {
            view.getGlobalVisibleRect(rect);

            bottom = (bottom < view.getBottom())? view.getBottom(): bottom;
            bottom = (bottom < rect.bottom)? rect.bottom: bottom;
        }

        Log.v(TAG, "bottom = " + bottom);
    }

    View getView(Activity activity) {
        View view = activity.getWindow().getDecorView().findViewById(android.R.id.content);
        return view;
    }

    ViewTreeObserver getViewTreeObserver(Activity activity) {
        View view = getView(activity);
        if (view == null) {
            return null;
        }

        return view.getViewTreeObserver();
    }

    /**
     *
     */
    public interface OnSoftInputListener {

        void onSoftInputChanged(SoftInputHelper helper, boolean visible);

    }
}