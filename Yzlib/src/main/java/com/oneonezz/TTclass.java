package com.oneonezz;

/**
 * Created by ${Justin} on 2019/10/28.
 */
public class TTclass {

    private ThreadLocal<Boolean> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        CloneTest ct = new CloneTest("Hello");
        CloneTest ct2 = ct.clone();
        ct2.setMsg("Hi");
        System.out.println("NiYouXi->main: "+ct.getMsg()+"  ct2 = "+ct2.getMsg());
    }

    static class CloneTest implements Cloneable{
        String msg;

        public CloneTest(String msg) {
            this.msg = msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getMsg() {
            return msg;
        }

        @Override
        protected CloneTest clone() {
            CloneTest cloneTest = null;
            try {
                cloneTest = (CloneTest) super.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            return cloneTest;
        }
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
