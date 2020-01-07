package com.oneonezz;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.airbnb.lottie.LottieAnimationView;
import com.example.aptannotation.BindView;
import com.example.aptannotation.RuntimeBind;
import com.just.agentweb.PermissionInterceptor;
import com.oneonezz.jni.JniActivity;
import com.oneonezz.opengl.GLSurfaceViewActivity;
import com.oneonezz.three.greendao.AdvertBean;
import com.oneonezz.three.matisse.GifSizeFilter;
import com.oneonezz.three.matisse.Glide4Engine;
import com.oneonezz.ui.LineDividerTextView;
import com.oneonezz.ui.MoveImageView;
import com.oneonezz.utils.FileUtils;
import com.oneonezz.utils.L;
import com.yuyh.library.imgsel.ISNav;
import com.yuyh.library.imgsel.config.ISListConfig;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.filter.Filter;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import jcifs.UniAddress;
import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;
import jcifs.smb.SmbSession;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int REQUEST_CODE_CHOOSE = 100;
    private MoveImageView move;

    //设置权限
    private String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.INTERNET,
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.CAMERA};
    private static final int PERMISSIONS = 1000;     //请求码
    private ImageView img_click;

    @BindView(value = R.id.img)
    public ImageView img;

    @RuntimeBind(value = R.id.hellow)
    public TextView hellow;
    private ImageView img_click2;
    private LineDividerTextView dividerTextView;

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    ImageView moveView;

    private static int getDrawableWidth(Drawable d) {
        int width = d.getIntrinsicWidth();
        if (width <= 0) width = d.getMinimumWidth();
        if (width <= 0) width = d.getBounds().width();
        return width;
    }

    private static int getDrawableHeight(Drawable d) {
        int height = d.getIntrinsicHeight();
        if (height <= 0) height = d.getMinimumHeight();
        if (height <= 0) height = d.getBounds().height();
        return height;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        return super.dispatchKeyEvent(event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final LottieAnimationView lottieAnimationView = findViewById(R.id.lottieAnimationView);
        lottieAnimationView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (lottieAnimationView.isAnimating()) {
                    lottieAnimationView.cancelAnimation();
                } else {
                    lottieAnimationView.playAnimation();
                }
            }
        });


        img_click = (ImageView) findViewById(R.id.img_click);
        img_click2 = (ImageView) findViewById(R.id.img_click2);
        TextView textView3 = findViewById(R.id.textView3);

        findViewById(R.id.move).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FileUtils.readObjectFromFile("object");

            }
        });

        img_click2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                AdvertBean aBean = new AdvertBean();
                List<AdvertBean.AdvertTypeBean> temp = new ArrayList<>();
                AdvertBean.AdvertTypeBean typeBean = new AdvertBean.AdvertTypeBean();
                typeBean.setIs_usermsg("123");
                temp.add(typeBean);

                AdvertBean.AdvertTypeBean typeBean1 = new AdvertBean.AdvertTypeBean();
                typeBean1.setIs_usermsg("000");
                temp.add(typeBean1);
//                aBean.setInfo(temp);
                FileUtils.writeObjectToFile("object", temp);
            }
        });

        img_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FileUtils.readObjectFromFile("object");
            }
        });

//        startActivity(new Intent(MainActivity.this, JniActivity.class));


        System.setProperty("jcifs.smb.client.dfs.disabled", "true");
        System.setProperty("jcifs.smb.client.soTimeout", "1000000");
        System.setProperty("jcifs.smb.client.responseTimeout", "30000");


        new Thread(new Runnable() {
            @Override
            public void run() {
                readRemoteFile3();

//                readRometeFile2();
//                readRemoteFile();
//                readFile();
            }
        }).start();
    }

    private void readFile() {
        InputStream in = null;
        BufferedReader reader = null;
        try {
            //目标文件名
            String fileName = "测试.txt";
            String host = "192.168.0.119";//远程服务器的地址
            String username = "Administrator";//远程服务器的用户名
            String password = "0000";//远程服务器的密码
            String path = "/00_share/";//远程服务器共享文件夹名称
            String remoteUrl = "smb://" + username + ":" + password + "@" + host + path + (path.endsWith("/") ? "" : "/");//这是需要输入密码的url
//            String remoteUrl = "smb://" + host + path + (path.endsWith("/") ? "" : "/");//这是不需要输入密码的url

            //创建远程文件对象
            SmbFile remoteFile = new SmbFile(remoteUrl + "/" + fileName);
            remoteFile.connect();//建立连接
            //创建文件流
            in = new BufferedInputStream(new SmbFileInputStream(remoteFile));
            reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));//读取流,记得文本文档要设置格式哦，不然会出现乱码
            StringBuffer sb = new StringBuffer();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line.toString());
            }
            L.i("oo- readFile -> : " + sb.toString());
        } catch (Exception e) {
            L.e("oo- readFile -> : 下载远程文件出错: " + e.getLocalizedMessage());
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (Exception e) {
            }
        }
    }

    private void readRemoteFile() {
        String ip = "192.168.0.119";//pc地址
        String username = "administrator";//账户密码
        String password = "0000";
        UniAddress mDomain = null;
        try {
            //登录授权
            mDomain = UniAddress.getByName(ip);
            NtlmPasswordAuthentication mAuthentication = new NtlmPasswordAuthentication(ip, username, password);
            SmbSession.logon(mDomain, mAuthentication);
            //登录授权结束
            String rootPath = "smb://" + ip + "/";
            SmbFile mRootFolder;
            try {
                mRootFolder = new SmbFile(rootPath, mAuthentication);
                try {
                    SmbFile[] files;
                    files = mRootFolder.listFiles();
                    for (SmbFile smbfile : files) {
                        Log.e("文件名称----", smbfile.getCanonicalPath());//这个就能获取到共享文件夹了
                    }
                } catch (SmbException e) {
                    // ...
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SmbException e) {
            e.printStackTrace();
        }
    }

    private void readRometeFile2() {
        String URL = "smb://192.168.0.119/00_share/00.txt";
        try {


            NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication(null, "Administrator", "0000");

            SmbFile smbfile = new SmbFile(URL, auth);
            if (!smbfile.exists()) {
                System.out.println("no such folder");
            } else {
                SmbFile[] files = smbfile.listFiles();
                for (SmbFile f : files) {
                    L.i("oo- readRometeFile2 -> : " + f.getName());
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    private void readRemoteFile3() {
        String ip = "192.168.0.119";
        String username = "administrator";
        String password = "0000";

        UniAddress mDomain = null;
        NtlmPasswordAuthentication mAuthentication = null;
        try {
            mDomain = UniAddress.getByName(ip);
            mAuthentication = new NtlmPasswordAuthentication(ip, username, password);
            SmbSession.logon(mDomain, mAuthentication);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SmbException e) {
            e.printStackTrace();
        }

// 获取跟目录然后获取下面各个盘符
        String rootPath = "smb://" + ip + "/";
        SmbFile mRootFolder;
        // 匿名登录即无需登录
        try {
            mRootFolder = new SmbFile(rootPath, mAuthentication);
            SmbFile[] files;
            files = mRootFolder.listFiles();
            for (SmbFile smbfile : files) {
                System.out.println("T.T->readRemoteFile3: " + smbfile.getName());
            }
        } catch (SmbException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置View左右平移动画
     *
     * @param toLeft    是否从左到右还是从右到左
     * @param viewLeft
     * @param viewRight
     */
    private void startAnimator(boolean toLeft, View viewLeft, View viewRight) {
        ObjectAnimator animatorLeft = null;
        ObjectAnimator animatorRight = null;
        if (toLeft) {
            animatorLeft = ObjectAnimator.ofFloat(viewLeft, "translationX", viewLeft.getTranslationX(), -viewLeft.getWidth());
            animatorRight = ObjectAnimator.ofFloat(viewRight, "translationX", viewRight.getTranslationX(), 0);
        } else {
            animatorLeft = ObjectAnimator.ofFloat(viewLeft, "translationX", viewLeft.getTranslationX(), 0);
            animatorRight = ObjectAnimator.ofFloat(viewRight, "translationX", viewRight.getTranslationX(), viewRight.getWidth());
        }
        animatorLeft.setDuration(500);
        animatorLeft.start();
        animatorRight.setDuration(500);
        animatorRight.start();
    }

    public void startZoomAnim(View v, float startScale) {
        // Construct and run the parallel animation of the four translation and
        // scale properties (X, Y, SCALE_X, and SCALE_Y).
        AnimatorSet set = new AnimatorSet();
        set.play(ObjectAnimator.ofFloat(v, "scaleY", 1, 2));
//        set.play(
//                ObjectAnimator.ofFloat(v, "x", 100, 0))
//                .with(ObjectAnimator.ofFloat(v, "y", 100, 500))
//                .with(ObjectAnimator.ofFloat(v, "scaleX", startScale, 1f))
//                .with(ObjectAnimator.ofFloat(v, "scaleY", 2, 1f));

        set.setDuration(5000);
        set.setInterpolator(new DecelerateInterpolator());
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
//                mCurrentAnimator = null;
//                if (listener != null) {
//                    listener.onExpanded();
//                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {
//                mCurrentAnimator = null;
//                if (listener != null) {
//                    listener.onExpanded();
//                }
            }
        });
        set.start();
    }


    protected PermissionInterceptor mPermissionInterceptor = new PermissionInterceptor() {

        @Override
        public boolean intercept(String url, String[] permissions, String action) {
            L.i("oo- intercept -> : " + url);
            return false;
        }
    };

    public View tt() {
        Button v = null;
        return v;
    }

    private void userImageSelect() {
        // 自由配置选项
        ISListConfig config = new ISListConfig.Builder()
                // 是否多选, 默认true
                .multiSelect(false)
                // 是否记住上次选中记录, 仅当multiSelect为true的时候配置，默认为true
                .rememberSelected(false)
                // “确定”按钮背景色
                .btnBgColor(Color.GRAY)
                // “确定”按钮文字颜色
                .btnTextColor(Color.BLUE)
                // 使用沉浸式状态栏
                .statusBarColor(Color.parseColor("#3F51B5"))
                // 返回图标ResId
//                .backResId(android.support.v7.appcompat.R.drawable.abc_ic_ab_back_mtrl_am_alpha)
                // 标题
                .title("图片")
                // 标题文字颜色
                .titleColor(Color.WHITE)
                // TitleBar背景色
                .titleBgColor(Color.parseColor("#3F51B5"))
                // 裁剪大小。needCrop为true的时候配置
                .cropSize(1, 1, 200, 200)
                .needCrop(true)
                // 第一个是否显示相机，默认true
                .needCamera(false)
                // 最大选择图片数量，默认9
                .maxNum(9)
                .build();

// 跳转到图片选择器
        ISNav.getInstance().toListActivity(this, config, REQUEST_CODE_CHOOSE);
    }

    private void initPermission() {
        if (EasyPermissions.hasPermissions(MainActivity.this, permissions)) {
//            setSelPicture();
//            userImageSelect();
        } else {
            //没有打开相关权限、申请权限
            EasyPermissions.requestPermissions(MainActivity.this, "需要获取您的相册、照相使用权限", PERMISSIONS, permissions);
        }
    }


    private void setSelPicture() {
        Matisse.from(MainActivity.this)
                .choose(MimeType.of(MimeType.PNG, MimeType.JPEG))
                .countable(true)
                .maxSelectable(9)
                .capture(false)
                .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
//                .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                .thumbnailScale(0.85f)
                .imageEngine(new Glide4Engine())
                .forResult(REQUEST_CODE_CHOOSE);
    }

    List<Uri> mSelected;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            mSelected = Matisse.obtainResult(data);
            Log.d("Matisse", "mSelected: " + mSelected);
        }
    }

    private void checkPermission() {
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                Toast.makeText(this, "不再显示", Toast.LENGTH_SHORT).show();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 100);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        Log.i(TAG, "onRequestPermissionsResult: " + permissions);

        switch (requestCode) {
            case 100:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    boolean isNorShow = ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION);
                    if (isNorShow) {
                        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 100);
                    } else {

                    }
                }

                break;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        //框架要求必须这么写
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    //成功打开权限
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        Toast.makeText(this, "相关权限获取成功", Toast.LENGTH_SHORT).show();
    }

    //用户未同意权限
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        Log.d("**", "onPermissionsDenied: 拒绝权限");
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this)
                    .setRationale("没有该权限，此应用程序可能无法正常工作。打开应用设置屏幕以修改应用权限")
                    .setTitle("必需权限")
                    .build()
                    .show();
        }
    }


    private String judgeProvider(LocationManager locationManager) {
        List<String> prodiverlist = locationManager.getProviders(true);
        if (prodiverlist.contains(LocationManager.NETWORK_PROVIDER)) {
            return LocationManager.NETWORK_PROVIDER;//网络定位

        } else if (prodiverlist.contains(LocationManager.GPS_PROVIDER)) {
            return LocationManager.GPS_PROVIDER;//GPS定位
        } else {
            Toast.makeText(this, "没有可用的位置提供器", Toast.LENGTH_SHORT).show();
        }
        return null;
    }

    public Location beginLocatioon() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        //获得位置服务
        String provider = judgeProvider(locationManager);
        //有位置提供器的情况
        if (provider != null) {
            //为了压制getLastKnownLocation方法的警告
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                return null;
            }
            return locationManager.getLastKnownLocation(provider);
        } else {
            //不存在位置提供器的情况
            Toast.makeText(this, "不存在位置提供器的情况", Toast.LENGTH_SHORT).show();
        }
        return null;
    }

    @Override
    public void onClick(View view) {
        beginLocatioon();
        startActivity(new Intent(this, TabActivity.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        AgentWebConfig.clearDiskCache(this);
        L.i("oo- onDestroy -> : ");
    }
}
