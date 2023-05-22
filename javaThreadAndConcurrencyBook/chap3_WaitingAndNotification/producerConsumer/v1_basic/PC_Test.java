package javaThreadAndConcurrencyBook.chap3_WaitingAndNotification.producerConsumer.v1_basic;

/*
 * The producer thread must wait until it’s notified that the previously produced 
 * data item has been consumed, and the consumer thread must wait until it’s notified 
 * that a new data item has been produced.
 * 
 * This application creates a Shared object and two threads that get a copy of 
 * the object’s reference. 
 * The producer calls the object’s setSharedChar() method to save each of 26 
 * uppercase letters; 
 * The consumer calls the object’s getSharedChar() method to acquire each letter.
 * The writeable instance field tracks two conditions: the producer waiting on the
 * consumer to consume a data item and the consumer waiting on the producer to produce
 * a new data item. 
 * It helps coordinate execution of the producer and consumer.
 * 
 * 
 * Although the synchronization works correctly, you might observe multiple 
 * producing messages before multiple consuming messages.
 * Also, you might observe a consuming message before a producing message.
 * 
 * Either strange output order doesn’t mean that the producer and consumer threads
 * aren’t synchronized. 
 * Instead, it’s the result of the call to setSharedChar() followed by its companion 
 * System.out.println() method call not being synchronized, and by the
 * call to getSharedChar() followed by its companion System.out.println() method call 
 * not being synchronized. 
 * 
 * The output order can be corrected by wrapping each of these method call pairs 
 * in a synchronized block that synchronizes on the Shared object referenced by s.
 * 
 * */

public class PC_Test {
	public static void main(String[] args) {
		
		Shared s = new Shared();
		
		new Producer(s).start();
		new Consumer(s).start();
	}
}
