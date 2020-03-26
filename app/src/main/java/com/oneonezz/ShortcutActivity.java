package com.oneonezz;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.pm.ShortcutManagerCompat;

import com.oneonezz.utils.ToastUtil;

import pub.devrel.easypermissions.EasyPermissions;

public class ShortcutActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mImageView2;
    /**
     * This is first child view
     */
    private Button mBt1;
    /**
     * This is second child view
     */
    private Button mBt2;
    /**
     * This is second child view
     */
    private Button mBt3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shortcut);
        initView();
    }

    private void initView() {
        mImageView2 = (ImageView) findViewById(R.id.imageView2);
        mImageView2.setOnClickListener(this);
        mBt1 = (Button) findViewById(R.id.bt1);
        mBt1.setOnClickListener(this);
        mBt2 = (Button) findViewById(R.id.bt2);
        mBt2.setOnClickListener(this);
        mBt3 = (Button) findViewById(R.id.bt3);
        mBt3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.imageView2:
                checkShortcutPermission();
                break;
            case R.id.bt1:
                ToastUtil.show("11");
                break;
            case R.id.bt2:
                ToastUtil.show("22");
                break;
            case R.id.bt3:
                ToastUtil.show("33");
                break;
        }
    }

    private void checkShortcutPermission() {
        String[] mPermissionList = new String[]{
                Manifest.permission.INSTALL_SHORTCUT
                , Manifest.permission.UNINSTALL_SHORTCUT
        };
        if (EasyPermissions.hasPermissions(this, mPermissionList)) {
            addShortcut("");
        } else {
            //未同意过,或者说是拒绝了，再次申请权限
            EasyPermissions.requestPermissions(this, "保存链接需要权限", 1, mPermissionList);
        }
    }

    //添加快捷方式
    private void addShortcut(String url) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            if (!ShortcutManagerCompat.isRequestPinShortcutSupported(this)) {
                {
                    ToastUtil.show("不支持创建快捷方式");
                    return;
                }
            }

            Uri uri = Uri.parse("url");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);

            ShortcutManager scm = (ShortcutManager) getSystemService(SHORTCUT_SERVICE);
            ShortcutInfo si = new ShortcutInfo.Builder(this, "dataroam")
                    .setIcon(Icon.createWithResource(this, R.drawable.ic_launcher_background))
                    .setShortLabel("下载连接")
                    .setIntent(intent)
                    .build();
//            assert scm != null;
            if (scm == null) {
                ToastUtil.show("scm == null");
                return;
            }
            scm.requestPinShortcut(si, null);
        } else {
            ToastUtil.show("其它版本");

            Uri uri = Uri.parse("url");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);

            Intent shortcutInfoIntent = new Intent(Intent.ACTION_CREATE_SHORTCUT);//设置网络页面intent
            // 不允许重复创建
            shortcutInfoIntent.putExtra("duplicate", false);// 经测试不是根据快捷方式的名字判断重复的
            // 名字
            shortcutInfoIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, "下载连接");
            // 图标
            shortcutInfoIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE,
                    Intent.ShortcutIconResource.fromContext(this, R.drawable.ic_launcher_background));
            shortcutInfoIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, intent);

            // 发送广播
            sendBroadcast(shortcutInfoIntent);
        }

    }

    public void CreateShotCut(final Context context, final Class clazz,
                              final String name, final String image) {

        Intent shortcutIntent = new Intent(Intent.ACTION_MAIN);

        shortcutIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        shortcutIntent.setClass(context, clazz);
        /**
         * 设置这条属性，可以使点击快捷方式后关闭其他的任务栈的其他activity，然后创建指定的acticity
         */
        shortcutIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);


        Intent shortcut = new Intent(Intent.ACTION_CREATE_SHORTCUT);

        shortcut.putExtra("duplicate", false);

        shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);

        shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, name);


        Parcelable icon = Intent.ShortcutIconResource.fromContext(
                getApplicationContext(), R.drawable.ic_launcher_background);
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, icon);
        shortcut.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
        context.sendBroadcast(shortcut);
    }
}
