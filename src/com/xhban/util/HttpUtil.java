package com.xhban.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.xhban.listener.DownloadFinishListener;
import com.xhban.listener.RequestFinishListener;

public class HttpUtil {

	/**
	 * 获取图片地址
	 * 
	 * @param address
	 * @param listener
	 */
	public static void doGet(String address, RequestFinishListener listener) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				HttpURLConnection conn = null;
				InputStream in = null;
				BufferedReader reader = null;
				StringBuffer result = new StringBuffer();
				try {
					URL url = new URL(address);
					conn = (HttpURLConnection) url.openConnection();
					conn.setReadTimeout(5000);
					conn.setConnectTimeout(3000);
					conn.connect();
					in = conn.getInputStream();
					reader = new BufferedReader(new InputStreamReader(in));
					String line = "";
					while ((line = reader.readLine()) != null) {
						result.append(line);
					}
					if (listener != null) {
						listener.finish(result.toString());
					}
					reader.close();
					in.close();
				} catch (Exception e) {
					listener.error(e);
				} finally {
					if (conn != null) {
						conn.disconnect();
					}
				}

			}
		}).start();
	}

	/**
	 * 下载图片
	 * 
	 * @param address图片网址
	 * @param path保存路径
	 * @param name文件名
	 * @throws IOException
	 */
	public static void download(String address, String dir, String name, DownloadFinishListener listener) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				HttpURLConnection conn = null;
				FileOutputStream out = null;
				InputStream in = null;
				File saveFile = null;
				try {
					saveFile = new File(dir + "\\" + name);
					if (saveFile.exists()) {
						saveFile.delete();
					}
					out = new FileOutputStream(saveFile);
					URL url = new URL(address);
					conn = (HttpURLConnection) url.openConnection();
					conn.setReadTimeout(5000);
					conn.setConnectTimeout(3000);
					conn.connect();
					in = conn.getInputStream();
					int len = -1;
					byte[] buffer = new byte[1024];
					while ((len = in.read(buffer)) != -1) {
						out.write(buffer, 0, len);
					}
					listener.finish();
					out.close();
					in.close();
				} catch (Exception ex) {
					// 删除未正确下载的图片
					if (saveFile != null) {
						saveFile.delete();
					}
					listener.error(ex);
				} finally {
					if (conn != null) {
						conn.disconnect();
					}
				}
			}
		}).start();
	}
}
