package javaThreadAndConcurrencyBook.chap3_WaitingAndNotification.producerConsumer.v3_Locks;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Shared {

	private char c;

	private volatile boolean writeable = true;  // initially the char is "empty" therefore the producer can write it


	private final Lock lock = new ReentrantLock();
	private final Condition isWriteable = lock.newCondition();


	// package scope (it should not be a accessible from the client
	Lock getLock() {
		return lock;
	}


	void setSharedChar(char c)	{

		while (!writeable)		// consumer is getting the char
			try	{
				isWriteable.await();
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}

		this.c = c;
		writeable = false;			// producer has written and the consumer can get the char
		isWriteable.signal();
	}


	char getSharedChar() {

		while (writeable)		// producer is writing the char therefore the consumer has to wait
			try	{
				isWriteable.await();
			} catch (InterruptedException ie)	{
				ie.printStackTrace();
			}

		writeable = true;		// consumer gets the char, than "c" is again writable
		isWriteable.signal();

		return c;
	}
}

