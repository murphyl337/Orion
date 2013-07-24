package com.cengage.apprentice.app.utils;

import java.util.concurrent.ThreadFactory;

public class OrionThreadFactory implements ThreadFactory {

	public Thread newThread(Runnable runnable) {
		Thread responseThread = new Thread(runnable, "orion-response-thread" + Thread.getAllStackTraces().size());
		System.out.println("Creating a new thread: " + responseThread.getName());
		return responseThread;
	}

}
