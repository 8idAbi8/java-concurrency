package javaThreadAndConcurrencyBook.chap2_Synchronization.threadStopping;

/*
 * Because stopped has been marked volatile, each thread will access the main memory copy 
 * of this variable and not access a cached copy. 
 * The application will stop, even on a multiprocessor-based or a multicore-based machine.
 * */

public class ThreadStopping_v4 {

	public static void main(String[] args)	{

		class StoppableThread extends Thread {
			
			private volatile boolean stopped = false;

			@Override
			public void run() {			
				
				while(!stopped) 
					System.out.println("running");				
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
			ie.printStackTrace();
		}
		
		thd.stopThread();
	}
}
