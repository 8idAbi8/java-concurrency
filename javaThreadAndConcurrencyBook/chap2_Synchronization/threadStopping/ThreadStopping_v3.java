package javaThreadAndConcurrencyBook.chap2_Synchronization.threadStopping;

/*
 * You can overcome this problem by using a local variable and assigning stopped’s value 
 * to _stopped variable in a synchronized block.
 * 
 * However, this solution is messy and wasteful because there is a performance cost when 
 * attempting to acquire the lock, and this task is being done for every loop iteration.
 * */

public class ThreadStopping_v3 {

	public static void main(String[] args)	{

		class StoppableThread extends Thread {
			
			boolean stopped = false;

			@Override
			public void run() {
				
				boolean _stopped = false;
				
				while(!_stopped) {
					synchronized (this) {		// performance cost
						_stopped = stopped;
					}
					
					System.out.println("running");
				}
			}

			synchronized void stopThread() {    
				stopped = true;
			}
		}

		StoppableThread thd = new StoppableThread();
		thd.start();

		try	{
			Thread.sleep(1000); 
		} catch (InterruptedException ie) {
			
		}

		thd.stopThread();
	}
}
