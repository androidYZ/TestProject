package com.oneonezz.annotation;

import android.app.Activity;

import com.example.aptannotation.RuntimeBind;

import java.lang.reflect.Field;

public class RuntimeAnnotationProcessor {
    public static void bind2(Activity activity){
        if (activity==null) return;
      Field[] fields =   activity.getClass().getDeclaredFields();
        if (fields == null || fields.length == 0) return;
        for(int i=0;i<fields.length;i++){
            if (fields[i].isAnnotationPresent(RuntimeBind.class)){
                int resId  = fields[i].getAnnotation(RuntimeBind.class).value();
                fields[i].setAccessible(true);
                try {
                    fields[i].set(activity,activity.findViewById(resId));
                    fields[i].setAccessible(false);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    fields[i].setAccessible(false);
                }
            }
        }
    }
}