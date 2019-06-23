package com.cethik.irmp.modules.quartz.task;

import org.springframework.stereotype.Component;

/**
 * 测试任务
 *
 * @author daniel.yu
 * @date 2019年6月23日 上午11:07:36
 */
@Component("testTask")
public class TestTask {

	/**
	 * 测试方法
	 */
	public void test() {
		System.out.println("current timestapmp is : " + System.currentTimeMillis());
	}
	
}
