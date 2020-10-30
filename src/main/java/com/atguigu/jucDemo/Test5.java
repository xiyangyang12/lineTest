package com.atguigu.jucDemo;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

public class Test5 {
    public static void main(String[] args) {
        List list = new CopyOnWriteArrayList();
        for (int i = 1; i <=3 ; i++) {
                new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,3));
                    System.out.println(list);
                },"aa").start();
        }
    }
}
