package javaThreadAndConcurrencyBook.chap3_WaitingAndNotification.producerConsumer.v3_Locks;

import java.util.concurrent.locks.Lock;

public class Consumer extends Thread {
	private final Shared s;	
	private final Lock lock;
	
	Consumer(Shared s) {
		this.s = s;
		this.lock = s.getLock();
	}

	@Override
	public void run() {
		char ch;
		
		do {
			lock.lock();
			
			ch = s.getSharedChar();
			System.out.println(ch + " consumed by consumer.");
		
			lock.unlock();		
		}
		while (ch != 'Z');
	}
}
