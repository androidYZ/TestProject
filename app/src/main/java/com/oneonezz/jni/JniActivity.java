package com.oneonezz.jni;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.oneonezz.R;
import com.oneonezz.utils.L;

public class JniActivity extends AppCompatActivity {
    // 加载native-lib，不加lib前缀
    static {
        System.loadLibrary("native-lib");
    }

    public native String stringFromJNI();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jni);

        //Java调c
        L.i("oo- onCreate -> : "+stringFromJNI());
    }

    /** c调用的方法 */
    public int add_number(int x, int y) {
        L.i("oo- add_number -> : "+x+"  :  "+y);
        return x + y;
    }
}
