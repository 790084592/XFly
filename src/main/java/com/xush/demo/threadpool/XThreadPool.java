package com.xush.demo.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池管理类 1.实现线程池的单例创建
 * 
 * @author xush
 * @since 2019.06.11
 */
public class XThreadPool {
	// 创建一个可重用固定线程数的线程池
	ExecutorService pool = Executors.newFixedThreadPool(2);

	private XThreadPool() {
	}

	// 静态内部类实现单例模式
	private static class XThreadPoolInstance {
		private static final XThreadPool INSTANCE = new XThreadPool();
	}

	/**
	 * 获取线程池管理对象
	 * 
	 * @return
	 */
	public static XThreadPool getInstance() {
		return XThreadPoolInstance.INSTANCE;
	}

	public void addTask(String taskName) {
		// 创建线程
		Thread t = new XWidgetCalc();
		// 将线程放入池中进行执行
		pool.execute(t);
		int threadCount = ((ThreadPoolExecutor) pool).getActiveCount();
		System.out.println("====" + threadCount);
	}
}
