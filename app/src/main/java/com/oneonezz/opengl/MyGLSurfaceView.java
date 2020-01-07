package com.oneonezz.opengl;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

import com.oneonezz.utils.L;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by ${Justin} on 2019/11/20.
 */
public class MyGLSurfaceView extends GLSurfaceView implements GLSurfaceView.Renderer{
    public MyGLSurfaceView(Context context) {
        super(context);
    }

    public MyGLSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        /** 此方法会在Surface第一次创建或重建时调用。
         * 在GLSurfaceView attatch到父View的后，此方法会被调用。从这个回调方法名我们可以大概了解这个方法的用处，即在OpenGL surface被创建时的回调。 */
        L.i("oo- onSurfaceCreated -> : ");
    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int i, int i1) {
        /** 此方法在Surface大小变化时调用，例如横屏转为竖屏、GLSurfaceView大小变化等。在Surface第一次创建时也会调用 */
        L.i("oo- onSurfaceChanged -> : ");
    }

    @Override
    public void onDrawFrame(GL10 gl10) {
        /** 此方法在渲染一帧图像时调用。
         * 在任意时间调用GLSurfaceView的 requestRender()方法后，GLSurfaceView会优先执行已保存在GL线程队列中的Runnable，
         * 然后调用此onDrawFrame(GL10 glUnused)方法渲染图像。GL线程队列中的所有Runnable和 onDrawFrame方法的调用都执行在GL线程中。 */

        /** GLSurfaceView.setRenderMode(int renderMode)可以设置是连续渲染还是按需渲染。 */
        L.i("oo- onDrawFrame -> : ");
    }
}
