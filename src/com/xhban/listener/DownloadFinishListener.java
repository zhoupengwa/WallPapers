package com.xhban.listener;

public interface DownloadFinishListener {
	void finish();

	void error(Exception ex);
}
