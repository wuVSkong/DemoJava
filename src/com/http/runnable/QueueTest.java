package com.http.runnable;

/**
 * @author wuchuang
 * @create 2019-05-28 17:58
 */
public class QueueTest implements ProducerIntef {
    @Override
    public void toDO() {
        try {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
       try {
            for (int i = 0;i<10;i++){
                QueueConsumer.put(new QueueTest());
//                new QueueTest().toDO();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
