package javaThreadAndConcurrencyBook.chap3_WaitingAndNotification.producerConsumer.v1_basic;

public class Consumer extends Thread {
	
	private final Shared s;
	
	Consumer(Shared s) {
		this.s = s;
	}

	@Override
	public void run() {		
		
		char ch;
				
		do {
			ch = s.getSharedChar();		// synchronized(s)
			System.out.println(ch + " consumed by consumer.");
		}
		while (ch != 'Z');
	}
}
