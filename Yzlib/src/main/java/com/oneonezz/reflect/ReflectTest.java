package com.oneonezz.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by ${Justin} on 2020/4/13.
 */
public class ReflectTest {

    private int testFeild = 10;

    private void printFeild() {
        System.out.println("T.T->printFeild: " + testFeild);
    }

    private void printFeild(String temp) {
        System.out.println("T.T->printFeild: " + temp);
    }

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        ReflectTest reflectTest = new ReflectTest();
        reflectTest.testFeild = 200;
        Class<? extends ReflectTest> mClass = reflectTest.getClass();
        Method method = mClass.getDeclaredMethod("printFeild", null);
        if (method != null) {
            method.setAccessible(true);
            method.invoke(reflectTest, null);
        }
        System.out.println("T.T->main: 1111");

        Class<ReflectTest> mTempClass = ReflectTest.class;
        ReflectTest tempReflat = mTempClass.newInstance();
        Method methodTemp = mTempClass.getDeclaredMethod("printFeild", null);
        if (methodTemp != null) {
            methodTemp.setAccessible(true);
            methodTemp.invoke(tempReflat, null);
        }
        System.out.println("T.T->main: 2222");

        Class mTempClass2 = Class.forName("com.oneonezz.reflect.ReflectTest");
        ReflectTest tempReflat2 = (ReflectTest) mTempClass2.newInstance();
        tempReflat2.testFeild = 222;
        Method methodTemp2 = mTempClass2.getDeclaredMethod("printFeild", null);
        Method methodTemp3 = mTempClass2.getDeclaredMethod("printFeild", String.class);
        methodTemp3.setAccessible(true);
        methodTemp3.invoke(tempReflat2, "");

        if (methodTemp2 != null) {
            methodTemp2.setAccessible(true);
            methodTemp2.invoke(tempReflat2, null);
        }
        System.out.println("T.T->main: 3333");
    }


}
