package com.demo.thread;

/**
 * java静态代理   多线程thread 实现接口Runnable 同样是静态代理的原理
 * @author wuchuang
 * @create 2019-05-29 22:04
 */
public class StaticProxy {
    public static void main(String[] args) {
        Marry you = new You();
        WeeddingCompany weeddingCompany = new WeeddingCompany(you);
        weeddingCompany.marry();
    }
}

interface Marry{
    public abstract void marry();
}

class You implements Marry{

    @Override
    public void marry() {
        System.out.println("you and 嫦娥结婚");
    }
}

class WeeddingCompany implements Marry{

    private Marry you;

    public WeeddingCompany(Marry you) {
        this.you = you;
    }

    private void before(){
        System.out.println("布置房间！");
    }

    private void after(){
        System.out.println("闹婚现场！");
    }

    @Override
    public void marry() {
        before();
        you.marry();
        after();
    }
}
