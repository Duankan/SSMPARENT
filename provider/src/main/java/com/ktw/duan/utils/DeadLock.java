package com.ktw.duan.utils;

/**
 * 线程死锁：两个及两个以上的线程等待对方释放锁，并无限期的卡住
 */
public class DeadLock {
    public void mthod1(){
        synchronized (String.class){
            System.out.println("一线程获得string的锁，并锁住");
            synchronized (Integer.class){
                System.out.println("线程获得Integer的锁");
            }
        }
        System.out.println("该线程已经执行完程序，没有锁住！");
    }
    public void method2(){
        synchronized (Integer.class){
            System.out.println("一线程获得Integer的锁，并锁住");
            synchronized (String.class){
                System.out.println("线程获得Stringd的锁");
            }
        }
    }
}
