package com.http;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author wuchuang
 * @create 2019-05-17 23:49
 */
public class RunnableTest implements Runnable{

    private String name;
    private Object myself;
    private Object parent;
//    private Map<String,String> map = new HashMap<>();
    private List<String> list = new ArrayList<>();
    private int countFlag;
    private int countNum;

    public RunnableTest(List<String> list1){
        list.addAll(list1);
        countFlag = list1.size();
    }

    @Override
    public void run() {

        try {
            while (countFlag>0){
                System.out.println(list.get(countFlag-1));
                synchronized (this){
                    countFlag--;
                    countNum++;
                }
                Thread.sleep(1000);
            }
            System.out.println(countNum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class demoMain{
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = new Date();
        System.out.println(sdf.format(date1));

        List<String> list = new ArrayList<>();
        getList(list);

        RunnableTest runnableTest = new RunnableTest(list);
//        runnableTest.run();
        Thread thread1 = new Thread(runnableTest);
        thread1.start();

        Thread thread2 = new Thread(runnableTest);
        thread2.start();

        Thread thread3 = new Thread(runnableTest);
        thread3.start();

        Thread thread4 = new Thread(runnableTest);
        thread4.start();

        Thread thread5 = new Thread(runnableTest);
        thread5.start();

        Thread thread6 = new Thread(runnableTest);
        thread6.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
            thread5.join();
            thread6.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("主线程结束");

        Date date2 = new Date();
        System.out.println(sdf.format(date2));
        long l = date2.getTime() - date1.getTime();
        System.out.println(l/1000);
    }

    public static void getList(List<String> list){
        for (int i = 0;i<10;i++){
            list.add("测试数据"+i);
        }
    }

}
