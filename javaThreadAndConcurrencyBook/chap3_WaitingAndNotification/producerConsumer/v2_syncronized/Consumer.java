package javaThreadAndConcurrencyBook.chap3_WaitingAndNotification.producerConsumer.v2_syncronized;

public class Consumer extends Thread {
	private final Shared s;

	Consumer(Shared s) {
		this.s = s;
	}

	@Override
	public void run() {
		char ch;
		
		do {
			synchronized (s) {
				ch = s.getSharedChar();		// synchronized (s)
				System.out.println(ch + " consumed by consumer.");
			}
		}
		while (ch != 'Z');
	}
}
