package javaThreadAndConcurrencyBook.chap3_WaitingAndNotification.producerConsumer.v1_basic;

public class Producer extends Thread {
	
	private final Shared s;
	
	Producer(Shared s) {
		this.s = s;
	}
	
	@Override
	public void run() {
		
		for (char ch = 'A'; ch <= 'Z'; ch++) {
			s.setSharedChar(ch);			// synchronized(s)
			System.out.println(ch + " produced by producer.");
		}
	}
}
