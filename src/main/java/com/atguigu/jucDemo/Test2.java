package com.atguigu.jucDemo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareData{
    private int number= 1;
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();
    public void print5(int total){
        lock.lock();
        try {
            while (number!=1){
                c1.await();
            }
            for (int i = 1; i <=5; i++) {
                System.out.println(Thread.currentThread().getName()
                +"\t"+total+"\t"+i);
            }
            number = 2;
            c2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void print10(int total){
        lock.lock();
        try {
            while (number!=2){
                c2.await();
            }
            for (int i = 1; i <=10 ; i++) {
                System.out.println(Thread.currentThread().getName()
                        +"\t"+total+"\t"+i);
            }
            number=3;
            c3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void print15(int total){
        lock.lock();
        try {
            while (number!=3){
                c3.await();
            }
            for (int i = 1; i <=15 ; i++) {
                System.out.println(Thread.currentThread().getName()
                        +"\t"+total+"\t"+i);
            }
            number=1;
            c1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}



public class Test2 {
    public static void main(String[] args) {
        ShareData shareData = new ShareData();
        new Thread(()->{
            for (int i = 1; i <=10 ; i++) {
            shareData.print5(i);
            }
        },"aa").start();
        new Thread(()->{
            for (int i = 1; i <=10 ; i++) {
                shareData.print10(i);
            }
        },"bb").start();
        new Thread(()->{
            for (int i = 1; i <=10 ; i++) {
                shareData.print15(i);
            }
        },"cc").start();
    }
}
