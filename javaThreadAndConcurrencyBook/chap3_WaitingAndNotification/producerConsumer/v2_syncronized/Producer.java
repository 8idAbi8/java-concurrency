package javaThreadAndConcurrencyBook.chap3_WaitingAndNotification.producerConsumer.v2_syncronized;

public class Producer extends Thread {
	private final Shared s;

	Producer(Shared s) {
		this.s = s;
	}

	@Override
	public void run() 	{
		
		for (char ch = 'A'; ch <= 'Z'; ch++) {
			synchronized (s) {
				s.setSharedChar(ch);  // un altra synchronized (s)
				System.out.println(ch + " produced by producer.");
			}
		}
	}
}
