package com.demo.thread;

/**
 * @author wuchuang
 * @create 2019-06-03 16:36
 */
public class RunnableDemo implements Runnable {
    private int ticket = 20;
    private Object obj = "aaa";
    private String name;

//    public RunnableDemo(String name) {
//        this.name = name;
//    }

    @Override
    public void run() {
        try {
            while (0 < ticket){
                synchronized (obj){
                    System.out.println(Thread.currentThread().getName()+"卖出一张票，剩余"+ticket);
                    ticket--;
                }

                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
//测试环境
class Demo1{
    public static void main(String[] args) {
        RunnableDemo runnableDemo1 = new RunnableDemo();

        Thread thread1 = new Thread(runnableDemo1,"柜台1--");
        Thread thread2 = new Thread(runnableDemo1,"柜台2--");
        Thread thread3 = new Thread(runnableDemo1,"柜台3--");

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
