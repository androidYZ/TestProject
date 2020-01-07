//
// Created by Administrator on 2019/12/9.
//
#include <stdlib.h>
#include <stdio.h>
#include <jni.h>
#include "com_oneonezz_jni_JniActivity.h"

JNIEXPORT jstring JNICALL Java_com_oneonezz_jni_JniActivity_stringFromJNI
        (JNIEnv *env, jobject instance) {
    return env->NewStringUTF("Hello C++");
}

JNIEXPORT void JNICALL Java_com_oneonezz_jni_JniActivity_add_number
        (JNIEnv *env, jobject) {

    //1.得到字节码
    //jclass      (*FindClass)(JNIEnv*, const char*);
    jclass jclass = (*env).FindClass("com/oneonezz/jni/JniActivity");

    //2.得到方法ID
    //jmethodID   (*GetMethodID)(JNIEnv*, jclass, const char*, const char*);
    jmethodID jmethodID1 = (*env).GetMethodID(jclass, "add_number",
                                              "(II)I");//倒数第一个参数：方法签名，倒数第二个参数：方法

    //3.实例化类
    //jobject     (*AllocObject)(JNIEnv*, jclass);
    jobject jobject1 = (*env).AllocObject(jclass);

    //调用方法
    //jint        (*CallIntMethod)(JNIEnv*, jobject, jmethodID, ...);
    jint result = (*env).CallIntMethod(jobject1, jmethodID1, 50, 30);


}