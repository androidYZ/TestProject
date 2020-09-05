
package com.oneonezz;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.provider.CallLog.Calls;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

import androidx.core.content.PermissionChecker;

import java.util.Timer;

/**
 * This class will receive and process phone information, when phone state
 * changes.
 */
public class CallService extends PhoneStateListener {
    private static final String TAG = "CallService";
    private static final int MSG_NEED_UPDATE_MISSED_CALL = 100;
    private Context mContext = null;
    private int mLastState = TelephonyManager.CALL_STATE_IDLE;
    private String mIncomingNumber = null;
    private Timer mTimer = null;
    private MissedCallContentOberserver mMCOberserver = null;
    private ContentResolver mContentResolver = null;
    private boolean isHasCommingPhone = false;
    private PackageManager pm;
    public static boolean isDeviceGuaduan = false;
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {  // This Handler class should be static or leaks might occur (anonymous android.os.Handler)   private static Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_NEED_UPDATE_MISSED_CALL: {
                    // sendCallMessage();
                    String phoneNum = mIncomingNumber;

                    break;
                }
            }
        }
    };

    public CallService(Context context) {
        Log.i(TAG, "CallService(), CallService created!");
        mContext = context;
        mContentResolver = context.getContentResolver();
        mMCOberserver = new MissedCallContentOberserver(mHandler);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {   //6.0以上
            /////////////////////////////////////////////////////////////////////////////////////////////////////
            int writeSdCardPermission;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                writeSdCardPermission = mContext.checkSelfPermission(Manifest.permission.WRITE_CALL_LOG); // == PackageManager.PERMISSION_GRANTED;
            } else {
                writeSdCardPermission = PermissionChecker.checkSelfPermission(mContext, Manifest.permission.WRITE_CALL_LOG); //  == PermissionChecker.PERMISSION_GRANTED;
            }
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            if (writeSdCardPermission == PackageManager.PERMISSION_GRANTED) {
                mContentResolver.registerContentObserver(Calls.CONTENT_URI, false, mMCOberserver);  // todo --- 有权限才开启
//                Logg.e(TAG, "CallService: 权限申请成功------------------");
            } else {
//                Logg.e(TAG, "CallService: 权限未申请失败------------------");
            }
        } else {
            mContentResolver.registerContentObserver(Calls.CONTENT_URI, false, mMCOberserver);
        }
    }

    public void stopCallService() {
        Log.i(TAG, "StopCallService(), CallService stoped!");
        mContentResolver.unregisterContentObserver(mMCOberserver);
        mMCOberserver = null;
        mContentResolver = null;
    }

    public void onCallStateChanged(int state, String incomingNumber) {
        Log.i(TAG, "onCallStateChanged(), incomingNumber" + incomingNumber);
        Log.i(TAG, "state = " + state);
        mLastState = state;
    }

    private String getMessageContent(String sender) {
        StringBuilder content = new StringBuilder();
        content.append(": ");
        content.append(sender);
        content.append("\r\n");
        content.append("Missed Call Count:");
        content.append(getMissedCallCount());
        Log.i(TAG, "getMessageContent(), content=" + content);
        return content.toString();
    }

    private int getMissedCallCount() {
        // setup query spec, look for all Missed calls that are new.
        StringBuilder queryStr = new StringBuilder("type = ");
        queryStr.append(Calls.MISSED_TYPE);
        queryStr.append(" AND new = 1");
        Log.i(TAG, "getMissedCallCount(), query string=" + queryStr);

        // start the query
        int missedCallCount = 0;

        //先检查权限  <uses-permission android:name="android.permission.READ_CALL_LOG" />
//        pm = BTNotificationApplication.getInstance().getPackageManager();
        if (PackageManager.PERMISSION_GRANTED == pm.checkPermission("android.permission.READ_CALL_LOG", "com.rd.tengfei.bdnotification")) {
            Cursor cur = null;
            cur = mContext.getContentResolver().query(Calls.CONTENT_URI, new String[]{Calls._ID}, queryStr.toString(), null, Calls.DEFAULT_SORT_ORDER);
            if (cur != null) {
                missedCallCount = cur.getCount();
                cur.close();
            }
        }

        Log.i(TAG, "getMissedCallCount(), missed call count=" + missedCallCount);
        return missedCallCount;
    }

    private class MissedCallContentOberserver extends ContentObserver {
        private int mPreviousMissedCallCount;
        private Handler mHandler;

        public MissedCallContentOberserver(Handler handler) {
            super(handler);
            mPreviousMissedCallCount = 0;
            mHandler = handler;
        }

        public void onChange(boolean onSelf) {
            super.onChange(onSelf);
            Log.i(TAG, "DataBase State Changed");
            int missedCallCount = getMissedCallCount();
            if (missedCallCount == 0) {
            } else if (mPreviousMissedCallCount < missedCallCount) {
                Message msg = new Message();
                msg.what = MSG_NEED_UPDATE_MISSED_CALL;
                mHandler.sendMessage(msg);
            }
            mPreviousMissedCallCount = missedCallCount;
        }
    }
}
