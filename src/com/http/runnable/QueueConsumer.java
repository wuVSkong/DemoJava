package com.http.runnable;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author th
 * 异步消息处理 2018-04-24
 */
public class QueueConsumer implements Runnable {

	private static final int MAX_FACTOR = 100000;
	// 任务队列
	private static volatile ArrayBlockingQueue<ProducerIntef> blockQueue = new ArrayBlockingQueue<ProducerIntef>(MAX_FACTOR);

	// 消费者最大数量
	private static final int MAX_SIZE = 10;

	// 创建消费者线程池
	private static ExecutorService threadPools = Executors.newFixedThreadPool(MAX_SIZE);

	static {
		for (int i = 0; i < MAX_SIZE; i++) {
			threadPools.execute(new QueueConsumer());
		}
	}

	@Override
	public void run() {
		while (true) {
			try {
				ProducerIntef p = blockQueue.take();
				if (p != null)
					p.toDO();
			} catch (Exception e) {
				e.printStackTrace();
				// TODO 失败的任务保存到数据库
				// 查询失败数据，分别1、5、10、30、60分钟查询出来 执行一次 ，最后任然失败的 手工干预
			}
		}
	}

	public static void put(ProducerIntef t) throws InterruptedException {
		blockQueue.put(t);
	}
}
