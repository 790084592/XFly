package com.xush.demo.action;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

/**
 * 服务器启动时，创建数据库用户的相关监控线程
 * 用于检验失效实现，做出对应的失效处理
 * @author xush
 * @since 2019年7月12日
 */
@Component
public class SecurityDbUserManager {

	
	//24小时的毫秒数
	public static final long ONE_DAY = 24 * 60 * 60 * 1000;
	
	//监控线程的运行时间
	public static final String CHECK_TIME = "16:20:00";
	
	/**
	 * 初始化监控线程
	 * @throws ParseException 
	 */
	@PostConstruct
	public void init() throws ParseException {
		//当前时间距00:00:00 的毫秒数，即线程初次运行时需要延迟的时间。
		long initDelay  = getTimeMillis(CHECK_TIME) - System.currentTimeMillis();
		initDelay = initDelay > 0 ? initDelay : ONE_DAY + initDelay;
		//创建一个周期定时线程，延迟initDelay毫秒后运行，周期为ONE_DAY
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		executor.scheduleAtFixedRate(new EchoServer(), 0, 1000*60, TimeUnit.MILLISECONDS);
	}
	
	/**
	 * 获取指定时间对应的毫秒数
	 * @param time
	 * @return
	 * @throws ParseException
	 */
	private static long getTimeMillis(String time) throws ParseException {
			DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
			DateFormat dayFormat = new SimpleDateFormat("yy-MM-dd");
			Date curDate = dateFormat.parse(dayFormat.format(new Date()) + " " + time);
			return curDate.getTime();
	}
	
	/**
	 * 监控线程
	 * @author xush
	 * @since 2019年7月12日
	 */
	class EchoServer implements Runnable {
		@Override
		public void run() {
			System.out.println("+++++++++++++++++");
			System.out.println("test----ok-----success");
			System.out.println("+++++++++++++++++++");
		}
	}

}
