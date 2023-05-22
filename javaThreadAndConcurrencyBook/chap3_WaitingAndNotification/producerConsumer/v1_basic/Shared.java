package javaThreadAndConcurrencyBook.chap3_WaitingAndNotification.producerConsumer.v1_basic;

public class Shared {

	private char c;

	private volatile boolean writeable = true;

	synchronized void setSharedChar(char c)	{   // synchronized(this)

		while (!writeable)   // try-catch all'interno del while
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
		this.c = c;
		writeable = false;
		notify();
	}

	synchronized char getSharedChar() {		// synchronized(this)
		
		while (writeable)
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		writeable = true;
		notify();
		return c;
	}

}
