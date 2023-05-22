package javaThreadAndConcurrencyBook.chap3_WaitingAndNotification.producerConsumer.v3_Locks;

import java.util.concurrent.locks.Lock;

public class Producer extends Thread {
	private final Shared s;	
	private final Lock lock;
	
	Producer(Shared s) {
		this.s = s;
		this.lock = s.getLock();
		
		this.start();
	}
	
	@Override
	public void run() {
		
		for (char ch = 'A'; ch <= 'Z'; ch++) {
			
			lock.lock();
			
			s.setSharedChar(ch);
			System.out.println(ch + " produced by producer.");
			
			lock.unlock();
		}
	}
}
