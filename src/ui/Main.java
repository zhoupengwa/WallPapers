package ui;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.xhban.util.DownUtil;

public class Main {
	public static void main(String[] args) {
		downloadTimer();
	}
	public static void downloadTimer() {
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				DownUtil.downWallPaper();
			}
		};
		// 设置执行时间
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);// 每天
		calendar.set(year, month, day, 9, 00, 00);
		// 定制每天的09:10:00执行，不重复执行
		Date date = calendar.getTime();
		Timer timer = new Timer();
		timer.schedule(task, date);
		// 每天的date时刻执行，每隔2s重复执行
		// int peroid = 2 * 1000;
		// timer.schedule(task, date, peroid);
	}

}
