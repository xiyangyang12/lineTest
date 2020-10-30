package com.atguigu.jucDemo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareTicket{
    private int number = 0;
    private Lock lock = new ReentrantLock();
    Condition cd = lock.newCondition();

    public void incre() throws Exception {
        lock.lock();
        try {
            while (number!=0){
                cd.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            cd.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void dncre() throws Exception{
        lock.lock();
        try {
            while (number!=1){
                cd.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            cd.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}


public class Test1 {
    public static void main(String[] args) {
        ShareTicket se = new ShareTicket();
        new Thread(()->{
            for (int i = 1; i <=10 ; i++) {
                try {
                    se.incre();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"aa").start();

        new Thread(()->{
            for (int i = 1; i <=10 ; i++) {
                try {
                    se.dncre();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"bb").start();
    }
}

