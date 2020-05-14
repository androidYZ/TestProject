package com.oneonezz.httplib;

import java.io.UnsupportedEncodingException;

/**
 * Created by ${Justin} on 2020/5/13.
 */
public class Temp {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String ss = "jiantizi���ܼƱʻ�����0<br>";

        System.out.println("T.T->main: " + new String(ss.getBytes("gb2312"), "gbk"));
    }
}
