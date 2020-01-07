package com.oneonezz.rxjava;

import java.util.ArrayList;
import java.util.List;

/**
 * 被观察者
 */
public class Watched{

    private List<Watcher > list = new ArrayList<>();

    //注册观察者
    public void registerWatcher(Watcher watcher) {
        list.add(watcher);
    }

    //移除观察者
    public void unregisterWatcher(Watcher watcher) {
        list.remove(watcher);
    }

    //清空观察者
    public void clearWatcher() {
        list.clear();
    }

    //通知观察者
    public void notifyWathers(String string) {
        for (Watcher watcher: list ) {
            watcher.update(string);
        }
    }
}