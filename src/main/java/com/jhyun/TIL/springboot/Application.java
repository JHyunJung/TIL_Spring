package com.jhyun.TIL.springboot;

import com.jhyun.TIL.springboot.thread.CustomThread;
import com.jhyun.TIL.springboot.thread.ShareThread;
import com.jhyun.TIL.springboot.thread.Task;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Application {
    public static void main(String[] args){

//        SpringApplication.run(Application.class, args);

        ShareThread shareThread = new ShareThread();
        Thread thread1 = new Thread(()->{
            shareThread.setValue(100);
        });

        Thread thread2 = new Thread(()->{
            shareThread.setValue(10);
        });

        thread1.setName("스레드 1");
        thread2.setName("스레드 2");
        thread1.start();
        thread2.start();



    }
}
