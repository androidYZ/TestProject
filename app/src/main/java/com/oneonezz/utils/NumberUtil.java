package com.oneonezz.utils;

/**
 * Created by ${Justin} on 2019/10/29.
 */
public class NumberUtil {
    public static void main(String[] args) {
        for (int i=1; i<100; i++){
            int alpha = (int) Math.round(i/100.0 * 255);
            String hex = Integer.toHexString(alpha).toUpperCase();
            if (hex.length() == 1) hex = "0" + hex;

            String color = "<color name=\"black_" + i + "\">#" + hex + "000000" + "</color>";
            System.out.println(color);
        }
    }
}
