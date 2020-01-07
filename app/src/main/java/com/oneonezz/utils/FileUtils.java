package com.oneonezz.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;

import com.oneonezz.TApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;

public class FileUtils {

    public static final String TAG = "FileLog";

    // 日志存到sd卡的文件名key
    public static final String SP_LOGNAME_KEY = "SP_LOGNAME_KEY";

    //文件夹名字
    public static final String DIR_LOG_NAME = "TTZD_LOG";

    //Log文件名字
    public static final String FILE_NAME = "App_Log";

    //临时存放文件名
    private static String fileName;

    //内部存储 /data/data/<application package>/files目录
    public static final String INNER_PATH = TApplication.sInstance.getFilesDir().getAbsolutePath();

    //序列化文件存放的目录
    public static final String OBJECT_PATH = INNER_PATH + "/object/";


    /**
     * 将日志写到文件上
     *
     * @param log
     */
    public synchronized static void writeLogToFile(String log) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int writeSdCardPermission = TApplication.getInstance()
                    .checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (writeSdCardPermission != PackageManager.PERMISSION_GRANTED) {
                return;
            }
        }

        FileWriter fileWriter = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
            String time = format.format(System.currentTimeMillis());
            String sdDir = Environment.getExternalStorageDirectory() + "/0000/";
            File dir = new File(sdDir, DIR_LOG_NAME);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            if (fileName == null) {
                fileName = SpUtil.getInstance().getStringValue(SP_LOGNAME_KEY, FILE_NAME);
            }

            File logFile = new File(dir, fileName + ".txt");

            if (!logFile.exists()) {
                logFile.createNewFile();
                fileWriter = new FileWriter(logFile, true);
                SpUtil.getInstance().setStringValue(SP_LOGNAME_KEY, fileName);

            } else {
                if (logFile.length() > 1024 * 1024 * 20) {
                    fileName = FILE_NAME + "_" + time;
                    logFile = new File(dir, fileName + ".txt");
                    if (!logFile.exists())
                        logFile.createNewFile();
                    SpUtil.getInstance().setStringValue(SP_LOGNAME_KEY, fileName);
                }
                fileWriter = new FileWriter(logFile, true);
            }

            if (!log.endsWith("\n")) {
                fileWriter.write(time + " : " + log + "\n");
            } else {
                fileWriter.write(time + " : " + log);
            }
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 不关的话在LG V10上会出现FileNotFoundException
                // open failed: EMFILE (Too many open files)
                if (fileWriter != null) {
                    fileWriter.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public static void writeObjectToFile(String fileName, Object obj) {
        File dir = new File(OBJECT_PATH);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        File file = new File(dir, fileName);

        FileOutputStream out;
        try {
            out = new FileOutputStream(file);
            ObjectOutputStream objOut = new ObjectOutputStream(out);
            objOut.writeObject(obj);
            objOut.flush();
            objOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object readObjectFromFile(String fileName) {
        File dir = new File(OBJECT_PATH);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        Object temp = null;
        FileInputStream in;
        File file = new File(dir, fileName);
        try {
            in = new FileInputStream(file);
            ObjectInputStream objIn = new ObjectInputStream(in);
            temp = objIn.readObject();
            objIn.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return temp;
    }

    /**
     * 用户数据保存目录
     *
     * @param context
     * @return
     */
    public static String getDiskFilesDir(Context context) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalFilesDir(null).getPath();
        } else {
            cachePath = context.getFilesDir().getPath();
        }

        if (TextUtils.isEmpty(cachePath)) {

        }

        return cachePath;
    }

}