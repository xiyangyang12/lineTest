package com.atguigu.jucDemo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ticket{
     private int number =30;
     private Lock lock = new ReentrantLock();
    public void sale(){
        lock.lock();
        try {
            if (number>0){
                 System.out.println(Thread.currentThread().getName());
             }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

//     public synchronized void sale(){
//         if (number>0){
//             System.out.println(Thread.currentThread().getName());
//         }
//     }
}
public class SaleTicket {
    public static void main(String[] args) throws Exception{
        Ticket ticket = new Ticket();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <=40 ; i++) {
                    ticket.sale();
                }
            }
        },"aa").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <=40 ; i++) {
                    ticket.sale();
                }
            }
        },"bb").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <=40 ; i++) {
                    ticket.sale();
                }
            }
        },"cc").start();
    }
}
