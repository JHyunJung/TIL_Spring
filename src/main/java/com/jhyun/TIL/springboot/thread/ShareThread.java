package com.jhyun.TIL.springboot.thread;

public class ShareThread {
    private int value = 0;

    public synchronized void setValue(int value){
        this.value = value;
        try{
            Thread.sleep(2000);
        } catch (Exception e){

        }
        System.out.println(Thread.currentThread().getName() + "의 value 값은 " + this.value);
    }



    public int getValue(){
        return value;
    }

}
