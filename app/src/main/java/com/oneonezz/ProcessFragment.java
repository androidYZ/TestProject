package com.oneonezz;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

/**
 * Created by cxf on 2018/9/29.
 * 处理 检查权限和 startActivityForResult 的回调的Fragment
 */

public class ProcessFragment extends Fragment {

    private Context mContext;
    private Runnable mPermissionCallback;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }


    /**
     * 检查是否具有多个权限
     *
     * @param permissions
     * @return true 有权限 false无权限
     */
    private boolean checkPermissions(String[] permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(mContext, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (isAllGranted(permissions, grantResults)) {
            if (mPermissionCallback != null) {
                mPermissionCallback.run();
            }
        }
        mPermissionCallback = null;
    }


    /**
     * 判断申请的权限有没有被允许
     */
    private boolean isAllGranted(String[] permissions, int[] grantResults) {
        for (int i = 0; i < grantResults.length; i++) {
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * 拒绝某项权限时候的提示
     */

    public void requestPermissions(String[] permissions, Runnable runnable) {
        if (runnable != null) {
            if (checkPermissions(permissions)) {
                runnable.run();
            } else {
                mPermissionCallback = runnable;
                requestPermissions(permissions, 0);
            }
        }
    }

    public void startActivityForResult(Intent intent) {
        super.startActivityForResult(intent, 0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    }

}
