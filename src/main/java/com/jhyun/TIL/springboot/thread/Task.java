package com.jhyun.TIL.springboot.thread;

import org.springframework.web.bind.annotation.PathVariable;

public class Task implements Runnable{

    @Override
    public void run() {
        int sum = 0;
        for (int index = 0; index < 10; index++){
            sum += index;
            System.out.println("index = " + sum);
        }

        System.out.println(Thread.currentThread() + "최종 합 : " + sum);
    }

}
