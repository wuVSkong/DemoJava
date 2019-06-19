package com.demo.thread;

import com.http.RunnableTest;
import jdk.nashorn.internal.codegen.CompilerConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author wuchuang
 * @create 2019-05-31 22:06
 */
public class RunnableDemo1 implements Runnable {
    private long time;
    private boolean flag = true;
    private int step;
    private String name;
    private List<Integer> StrList;

    public RunnableDemo1(String name) {
        this.name = name;
    }

    public RunnableDemo1(long time, String name, List<Integer> strList) {
        this.time = time;
        this.name = name;
        StrList = strList;
    }

    @Override
    public void run() {
        try {
            while (flag){
                step++;
                Thread.sleep(time);
            }
            StrList.add(step);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class TestDemo{
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        List<Integer> list = new ArrayList<>();
        RunnableDemo1 rt1 = new RunnableDemo1(1000,"小兔子",list);
        RunnableDemo1 rt2 = new RunnableDemo1(500,"小乌龟",list);

        Thread thread1 = new Thread(rt1);
        Thread thread2 = new Thread(rt2);
        thread1.start();
        thread2.start();

        Thread.sleep(2000);
        rt1.setFlag(false);
        rt2.setFlag(false);

        System.out.println(rt1.getName() + "跑了" + rt1.getStep() + "步！");
        System.out.println(rt2.getName() + "跑了" + rt2.getStep() + "步！");

    }
}
