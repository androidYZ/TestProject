package com.oneonezz.ootest;

/**
 * Created by ${Justin} on 2019/9/26.
 */
public class Parent {

    public Parent(){
        onBaseInit();
    }

    protected void onBaseInit()
    {
        System.out.println("NiYouXi->onBaseInit: Parent");
    }
}
