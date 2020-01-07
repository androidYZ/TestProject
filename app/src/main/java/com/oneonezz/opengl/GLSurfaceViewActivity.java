package com.oneonezz.opengl;

import androidx.appcompat.app.AppCompatActivity;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.util.DisplayMetrics;

import com.oneonezz.R;
import com.oneonezz.opengl.camera.CameraV2;
import com.oneonezz.opengl.camera.CameraV2Renderer;
import com.oneonezz.utils.L;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class GLSurfaceViewActivity extends AppCompatActivity {

    private CameraV2 mCamera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glsurface_view);
        GLSurfaceView glSurfaceView = (GLSurfaceView) findViewById(R.id.id_gl_sv);
//        glSurfaceView.setRenderer(new GLSurfaceView.Renderer() {
//            @Override
//            public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
//                L.i("oo- onSurfaceCreated -> : ");
//                GLES20.glClearColor(0.1f, 0.1f, 0.1f, 1.0f);
//            }
//
//            @Override
//            public void onSurfaceChanged(GL10 gl10, int width, int height) {
//                L.i("oo- onSurfaceChanged -> : ");
//                GLES20.glViewport(0, 0, width, height);
//            }
//
//            @Override
//            public void onDrawFrame(GL10 gl10) {
//                L.i("oo- onDrawFrame -> : ");
//                // Redraw background color
//                GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
//            }
//        });

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        mCamera = new CameraV2(this);
        mCamera.setupCamera(dm.widthPixels, dm.heightPixels);
        if (!mCamera.openCamera()) {
            return;
        }
        CameraV2Renderer renderer = new CameraV2Renderer();
        renderer.init(glSurfaceView, mCamera,false,this);

        glSurfaceView.setEGLContextClientVersion(2);
        glSurfaceView.setRenderer(new MyRenderer());
//        glSurfaceView.setRenderer(renderer);
        glSurfaceView.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
    }
}
