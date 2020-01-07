package com.oneonezz.rxjava;

public class Watcher {

    //用于观察者更新状态
    public void update(String string) {

        System.out.println(Thread.currentThread().toString() + " : " + string);
    }

}