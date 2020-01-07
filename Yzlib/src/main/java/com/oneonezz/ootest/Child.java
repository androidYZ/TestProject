package com.oneonezz.ootest;

/**
 * Created by ${Justin} on 2019/9/26.
 */
public class Child extends Parent{

    public Child(){
        super();
    }

    @Override
    protected void onBaseInit() {
        System.out.println("NiYouXi->onBaseInit: Child");
    }
}
