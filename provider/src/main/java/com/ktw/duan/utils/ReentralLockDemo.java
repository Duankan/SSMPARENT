package com.ktw.duan.utils;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 重入锁：逼格满满，完全可以替代synchronized  ，
 * 可中断响应、锁申请等待限时、公平锁
 */
public class ReentralLockDemo implements Runnable {
    private static ReentrantLock lock1 = new ReentrantLock();
    private static ReentrantLock lock2 = new ReentrantLock();
    int lock;
    private static int i = 0;

    public ReentralLockDemo(int lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        /**锁申请等待时限**/
        try {
            if(lock1.tryLock(1, TimeUnit.SECONDS)){
                Thread.sleep(2000);
                lock1.unlock();
            }
            else {
                System.out.println(Thread.currentThread().getName()+"获取锁失败！");
            }
        }
        catch (Exception e){
            if(lock1.isHeldByCurrentThread()) lock1.unlock();
            e.printStackTrace();
        }

        /**可中断响应锁**/
//        try {
//            if (lock == 1) {
//                lock1.lockInterruptibly();//可以响应中断的方式加锁
//                System.out.println("<1>当前线程："+Thread.currentThread().getName()+" 获得锁");
//                Thread.sleep(1000);
//                lock2.lockInterruptibly();
//                System.out.println("---------------------");
//            } else {
//                lock2.lockInterruptibly();//可以响应中断的方式加锁
//                System.out.println("<2>当前线程："+Thread.currentThread().getName()+" 获得锁");
//                Thread.sleep(1000);
//                lock1.lockInterruptibly();
//                System.out.println("---------------------");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (lock1.isHeldByCurrentThread()) lock1.unlock();  // 注意判断方式
//            if (lock2.isHeldByCurrentThread()) lock2.unlock();
//            System.err.println(Thread.currentThread().getName() + "退出！");
//        }

        /**线程的重入锁枷锁**/
//        for (int j=0;j<50;j++){
//            lock.lock();
//            System.out.println("当前线程："+Thread.currentThread().getName()+i);
//            i++;
//            lock.unlock();
//        }
    }

    public static void main(String[] args) {
        ReentralLockDemo demo = new ReentralLockDemo(1);
        ReentralLockDemo demo2 = new ReentralLockDemo(2);
        Thread t1 = new Thread(demo, "one ");
        Thread t2 = new Thread(demo, "two ");
        t1.start();
        t2.start();
        //中断T2线程，释放锁
//        try {
//            Thread.sleep(1000);
//            t2.interrupt();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        //main线程会等到t1,t2执行完后再执行以后的流程
//        t1.join();
//        t2.join();
//        System.out.println(i);
    }
}
