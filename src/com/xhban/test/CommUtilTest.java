package com.xhban.test;

import org.junit.Test;

import com.xhban.util.CommUtil;

public class CommUtilTest {
	@Test
	public void test1() {
		int[] a = { 1, 3, 4, 2, 59, 23, };
		System.out.println(CommUtil.getMax(a));
	}

	@Test
	public void test2() {
		try {
			CommUtil.saveLog(1,"œ¬‘ÿ≥…π¶");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
