package com.oneonezz.opengl;

import android.annotation.TargetApi;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.util.Log;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

@TargetApi(Build.VERSION_CODES.FROYO)
public class MyRenderer2 implements GLSurfaceView.Renderer {
    private static final String TAG = MyRenderer2.class.getSimpleName();

    private static final String VERTEX_SHADER = "attribute vec4 vPosition;\n"
            + "void main() {\n"
            + "  gl_Position = vPosition;\n"
            + "  gl_PointSize = 10.0;\n"
            + "}";
    private static final String FRAGMENT_SHADER = "precision mediump float;\n"
            + "void main() {\n"
            + "  gl_FragColor = vec4(1,0,1,1);\n"
            + "}";

    private static final float[] VERTEX = {
            0, 0f, 0.0f
    };
    private FloatBuffer mVertexBuffer;
    private int mProgram;
    private int mPositionHandle;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        Log.e(TAG, "onSurfaceCreated");
        loadVertex();
        loadProgram();
    }

    /**
     * 加载顶点数据
     */
    private void loadVertex() {
        mVertexBuffer = ByteBuffer.allocateDirect(VERTEX.length * 4)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer()
                .put(VERTEX);
        mVertexBuffer.position(0); // Android里OpenGL使用Buffer作为数据，而不是直接使用数组，至于buffer怎么使用以后会有专门的说明
    }

    /**
     * 加载程序
     */
    private void loadProgram() {
        mProgram = GLES20.glCreateProgram(); // 创建一个Program对象，此对象非java里的对象，而是OpenGL里的对象，返回一个int值作为该对象的引用
        int vertexShader = loadShader(GLES20.GL_VERTEX_SHADER, VERTEX_SHADER); // 加载顶点着色器，对顶点做处理
        int fragmentShader = loadShader(GLES20.GL_FRAGMENT_SHADER, FRAGMENT_SHADER); // 加载颜色着色器，为什么叫颜色着色器，直译过来应该是片着色器呀？因为主要是作颜色变换
        GLES20.glAttachShader(mProgram, vertexShader); // 将着色器绑定到程序对象上
        GLES20.glAttachShader(mProgram, fragmentShader);
        GLES20.glLinkProgram(mProgram); // 连接，编译程序，为什么编译呢，你一会看到那个C语言大妈就知道了。

        mPositionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition"); // 从着色器程序的到属性的位置，从而可以向该属性设置值
    }


    static int loadShader(int type, String shaderCode) {
        int shader = GLES20.glCreateShader(type);   // 创建一个着色器对象
        GLES20.glShaderSource(shader, shaderCode); // 设置着色器对象的源码， 是好像是C语言，但只是像而已，它叫ALSL
        GLES20.glCompileShader(shader); // 编译，是的，既然设置的是源码，就需要编译了
        return shader;
    }


    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES20.glViewport(0,0,width/2,height/2);
        Log.e(TAG, "onSurfaceChanged");
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        Log.e(TAG, "onDrawFrame");
        GLES20.glClearColor(0f, 0f, 0f, 1f);
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

        GLES20.glUseProgram(mProgram); // 使用程序，  还记的状态机吗？在调用这一句后，OpenGL相关的绘制操作就会基于这个Program

        GLES20.glEnableVertexAttribArray(mPositionHandle); // 刚才的顶点位置属性，先使能
        GLES20.glVertexAttribPointer(mPositionHandle, VERTEX.length, GLES20.GL_FLOAT, false,
                VERTEX.length * 4 , mVertexBuffer);  // 然后向这个属性设置数据，个参数什么意思呢？

        GLES20.glDrawArrays(GLES20.GL_POINTS, 0, 1); // 这里是真正绘制的方法，GLES20.GL_POINTS表示绘制方式为绘制离散的点，而还有其他方式，比如最常用，绘制三角形，我们传三个顶点数据，就会绘制出一个三角形

        GLES20.glDisableVertexAttribArray(mPositionHandle); // 使顶点属性不可用，这也是，状态机的操作

        GLES20.glUseProgram(0); // 还原程序，不在使用mProgram
    }
}