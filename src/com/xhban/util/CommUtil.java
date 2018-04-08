package com.xhban.util;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class CommUtil {
	public static int getMax(int[] array) {
		Arrays.sort(array);
		return array[array.length - 1];
	}

	/**
	 * 日志记录
	 * 
	 * @param state日志状态分类
	 * @param message日志详情
	 */
	public static void saveLog(int state, String message) {
		FileOutputStream out = null;
		try {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String name = sdf.format(date);
			File saveDir = null;
			if (state == 1) {
				saveDir = new File("D:\\wallpapers\\logs");
			} else if (state == 0) {
				saveDir = new File("D:\\wallpapers\\errors");
			}
			if (!saveDir.exists()) {
				saveDir.mkdir();
			}
			File file = new File(saveDir + "\\" + name + ".txt");
			out = new FileOutputStream(file);
			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time = sdf.format(date);
			String log = "时间:" + time + ",  " + message;
			out.write(log.getBytes());
		} catch (Exception e) {
		}
	}
}
