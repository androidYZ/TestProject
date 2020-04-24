package com.oneonezz;

/**
 * Created by ${Justin} on 2019/10/28.
 */
public class TTclass {

    private ThreadLocal<Boolean> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {

    }


    private void init() {
        threadLocal.set(true);
        System.out.println("NiYouXi->init: " + threadLocal.get() + " : " + Thread.currentThread().getName());
        new Thread("Thread1") {
            @Override
            public void run() {
                threadLocal.set(false);
                System.out.println("NiYouXi->run: " + threadLocal.get() + " : " + Thread.currentThread().getName());
            }
        }.start();

        new Thread("Thread2") {
            @Override
            public void run() {

                for(;;){
                    print();
                }

            }

            private void print() {
                System.out.println("NiYouXi->run: " + threadLocal.get() + " : " + Thread.currentThread().getName());
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }


}
