package com.atguigu.jucDemo;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCachee{
    private volatile Map<String,Object> map = new HashMap<>();
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void put(String key,String value){
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"准备开始写"+key);
            Thread.sleep(300);
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"写完成"+key);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public void get(String key){
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"准备开始读"+key);
            Thread.sleep(300);
            Object value = map.get(key);

            System.out.println(Thread.currentThread().getName()+"读完成"+key);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }

    }
}


public class Test9 {
    public static void main(String[] args) throws InterruptedException {
        MyCache myCache = new MyCache();
        for (int i = 1; i <=5 ; i++) {
            String num = String.valueOf(i);
            new Thread(()->{
                myCache.put(num,num);
            },String.valueOf(i)).start();
        };


        TimeUnit.SECONDS.sleep(2);
        System.out.println("111888");

        for (int i = 1; i <=5 ; i++) {
            String num = String.valueOf(i);
            new Thread(()->{
                myCache.get(num);
            },String.valueOf(i)).start();
        }
    }
}
