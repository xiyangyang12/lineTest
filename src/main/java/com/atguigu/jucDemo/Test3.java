package com.atguigu.jucDemo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class TestDemo{
    private int number = 1;
    private int count = 1;
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();


}

public class Test3 {
    public static void main(String[] args) {

    }
}
