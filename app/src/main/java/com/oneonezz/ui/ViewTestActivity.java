package com.oneonezz.ui;

import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Rect;
import android.graphics.Shader;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.oneonezz.R;
import com.oneonezz.utils.L;

public class ViewTestActivity extends AppCompatActivity {

    private TextView graTest;
    private TextView graTest2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_test);

        graTest = (TextView) findViewById(R.id.graTest);
        graTest2 = (TextView) findViewById(R.id.graTest2);

        invalidateTv(graTest, "你好你好", 50, "#FF0000", "#FF0000");
    }

    public TextView invalidateTv(TextView tv, String s, int tv_size, String start_color, String end_color) {
        tv.setTextSize(tv_size);
        tv.setText(s);
        int spec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        tv.measure(spec, spec);
        int measuredWidthTicketNum = tv.getMeasuredWidth();
        //核心代码
        LinearGradient mLinearGradient = new LinearGradient(0, 0, measuredWidthTicketNum, 0,
                Color.parseColor(start_color),
                Color.parseColor(end_color),
                Shader.TileMode.REPEAT);
        tv.getPaint().setShader(mLinearGradient);
        tv.invalidate();
        return tv;
    }

}
