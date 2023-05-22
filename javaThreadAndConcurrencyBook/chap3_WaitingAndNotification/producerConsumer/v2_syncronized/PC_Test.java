package javaThreadAndConcurrencyBook.chap3_WaitingAndNotification.producerConsumer.v2_syncronized;

public class PC_Test {
	public static void main(String[] args) {
		
		Shared s = new Shared();
		
		new Producer(s).start();
		new Consumer(s).start();
	}
}
