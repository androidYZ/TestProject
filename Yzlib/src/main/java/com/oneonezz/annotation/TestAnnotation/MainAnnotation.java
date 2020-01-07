package com.oneonezz.annotation.TestAnnotation;

/**
 * Created by ${Justin} on 2019/9/9.
 */
@TestAnnotation
public class MainAnnotation {
    public static void main(String[] args) {
        boolean hasAnnotation = MainAnnotation.class.isAnnotationPresent(TestAnnotation.class);

        if (hasAnnotation) {
            TestAnnotation testAnnotation = MainAnnotation.class.getAnnotation(TestAnnotation.class);

            System.out.println("id:" + testAnnotation.id());
            System.out.println("msg:" + testAnnotation.msg());
        }
    }
}
