package com.oneonezz.three.agentweb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.just.agentweb.AgentWeb;
import com.just.agentweb.DefaultWebClient;
import com.oneonezz.R;

public class WebBaiduActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_baidu);


        AgentWeb.with(this)
                .setAgentWebParent((ViewGroup) findViewById(R.id.root), new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
//                .setOpenOtherPageWays(openOtherPageView)
                .setMainFrameErrorView(R.layout.agentweb_error_page, -1)
                .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
                .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.ASK)//打开其他应用时，弹窗咨询用户是否前往其他应用
                .interceptUnkownUrl() //拦截找不到相关页面的Scheme
                .createAgentWeb()
                .ready()
//                .go("https://5dyqp.com/dy/com.douyingame.ios/index.html?shareName=5dyqp.com");
//                .go("https://9989977.com/");
                .go("http://www.baidu.com/");
    }
}
