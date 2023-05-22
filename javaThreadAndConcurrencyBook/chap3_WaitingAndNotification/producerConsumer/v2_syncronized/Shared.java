package javaThreadAndConcurrencyBook.chap3_WaitingAndNotification.producerConsumer.v2_syncronized;

public class Shared {

	private char c;

	private volatile boolean writeable = true;

	void setSharedChar(char c)	{	// penso che non serve che sia: synchronized method

		while (!writeable)
			try	{
				wait();
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}

		this.c = c;
		writeable = false;
		notify();
	}

	char getSharedChar() {   // penso che non serve che sia: synchronized method
		while (writeable)
			try	{
				wait();
			} catch (InterruptedException ie)	{
				ie.printStackTrace();
			}

		writeable = true;
		notify();
		return c;
	}
}
