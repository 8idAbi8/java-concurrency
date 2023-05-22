package javaThreadAndConcurrencyBook.chap3_WaitingAndNotification.producerConsumer.v3_Locks;

public class PC_Test {
	public static void main(String[] args)
	{
		
//		Runnable r = () -> {
//			System.out.println("---   Start   --- \n");
//		};
//		new Thread(r).start();
		
		Shared s = new Shared();
		new Producer(s);  // start() in the constructor
		new Consumer(s).start();
	}
}
