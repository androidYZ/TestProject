package com.oneonezz.generic;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${Justin} on 2019/11/5.
 */
public class GenericTest {


    //泛型类，是在实例化类的时候指明泛型的具体类型；泛型方法，是在调用方法的时候指明泛型的具体类型 。
    //泛型类型用于类的定义中，被称为泛型类。通过泛型可以完成对一组类的操作对外开放相同的接口

    //class 类名称 <泛型标识：可以随便写任意标识号，标识指定的泛型的类型>{
    //  private 泛型标识 /*（成员变量类型）*/ var;
    //  .....
    //
    //  }
    //}

    //泛型通配符 Generic<Ingeter>        同一种泛型可以对应多个版本（因为参数类型是不确定的），不同版本的泛型类实例是不兼容的。


    /**
     * 泛型方法的基本介绍
     *
     * @param tClass 传入的泛型实参
     * @return T 返回值为T类型
     * 说明：
     * 1）public 与 返回值中间<T>非常重要，可以理解为声明此方法为泛型方法。
     * 2）只有声明了<T>的方法才是泛型方法，泛型类中的使用了泛型的成员方法并不是泛型方法。
     * 3）<T>表明该方法将使用泛型类型T，此时才可以在方法中使用泛型类型T。
     * 4）与泛型类的定义一样，此处T可以随便写为任意标识，常见的如T、E、K、V等形式的参数常用于表示泛型。
     */
    public <T> T genericMethod(Class<T> tClass) throws InstantiationException, IllegalAccessException {
        T instance = tClass.newInstance();
        return instance;
    }

//    public <T extends BaseAdvertBean> T  getList(Class<T> tClass){
//
//    }

    public static <T extends BaseAdvertBean> T getList(BaseAdvertBean data) {

        return null;
    }

    public static void main(String[] args) {

    }

    private static <T extends BaseAdvertBean> List<T> getAdvertList(Class<T> tClass, String[] info) {
//        List<T> list = JSON.parseArray(JSON.parseObject(info[0]).getString("list"), tClass);
//        T list0 = list.get(0);

        List<T> list = new ArrayList<>();
        BaseAdvertBean base = new BaseAdvertBean();
        AdvertTypeBean bb = new AdvertTypeBean();
        bb.setAdvert_type("123");
        base.setAdvertTypeBean(bb);
        list.add((T) base);

        return list;
    }

//    public  <T extends BaseAdvertBean> List<T> getAdvertList(Class<T> tClass, String[] info) {
//        List<T> list = JSON.parseArray(JSON.parseObject(info[0]).getString("list"), tClass);
//
//
//        T list0 = list.get(0);
//        list0.setAdvertTypeBean(null);
//
//
//
//        T testTT = null;
//        try {
//            testTT = tClass.newInstance();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//        list.add(testTT);
//
//        return list;
//    }

    public static <T> List<T> getLiveList(Class<T> tClass, String[] info) {
        List<T> list = JSON.parseArray(JSON.parseObject(info[0]).getString("list"), tClass);
        return list;
    }
}
