package utils;

import java.util.concurrent.ThreadFactory;

public class OrionThreadFactory implements ThreadFactory {

	public Thread newThread(ResponseRunner runnable){
		return new Thread(runnable, "orion-response-thread");
	}
	
	@Override
	public Thread newThread(Runnable runnable) {
		return new Thread(runnable, "orion-response-thread");
	}

}
