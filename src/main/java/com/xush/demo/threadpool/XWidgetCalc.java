package com.xush.demo.threadpool;

public class XWidgetCalc extends Thread {
	@Override
	public void run() {
		calc();
		System.out.println(Thread.currentThread().getName() + "正在执行。。。");
	}

	public void calc() {
		try {
			this.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.print(this.getId() + "-" + this.getName() + ": ");
	}

}
