package com.ktw.duan.utils;

public class ThreadDemo implements Runnable {
    private boolean flag;
    private static DeadLock deadLock;
    static {
        deadLock=new DeadLock();
    }
    public ThreadDemo(boolean flag){
        this.flag=flag;
    }
    @Override
    public void run() {
        if(flag){
            deadLock.mthod1();
        }
        else {
            deadLock.method2();
        }
    }
}
