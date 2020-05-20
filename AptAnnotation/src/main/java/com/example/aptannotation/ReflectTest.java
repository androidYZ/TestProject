package com.example.aptannotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by ${Justin} on 2020/3/11.
 */
public class ReflectTest {

    public int publicField;
    protected int protectedField;
    int normalField;
    private int price;
    private int privateField;

    public static void main(String[] args) throws Exception {
        //正常的调用
        ReflectTest apple = new ReflectTest();
        apple.setPrice(5);
        System.out.println("ReflectTest Price:" + apple.getPrice());
        //使用反射调用
        Class clz = Class.forName("com.oneonezz.yzapp.ReflectTest");
        Method setPriceMethod = clz.getMethod("setPrice", int.class);
        Constructor appleConstructor = clz.getConstructor();
        Object appleObj = appleConstructor.newInstance();
        setPriceMethod.invoke(appleObj, 14);
        Method getPriceMethod = clz.getMethod("getPrice");
        System.out.println("ReflectTest Price:" + getPriceMethod.invoke(appleObj));
        Field[] fields = clz.getFields();
        for (Field field : fields) {
            System.out.println(field.getName());
        }
        System.out.println("T.T->main: -----------------------------------");
        Field[] fieldPrivate = clz.getDeclaredFields();
        for (Field field : fieldPrivate) {
            System.out.println(field.getName());
        }
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
