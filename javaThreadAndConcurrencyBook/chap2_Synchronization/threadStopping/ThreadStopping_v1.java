package javaThreadAndConcurrencyBook.chap2_Synchronization.threadStopping;

/*
 * When you run this application on a single-processor/single-core machine, you’ll 
 * probably observe the application stopping. 
 * You might not see this stoppage on a multiprocessor machine or a uniprocessor machine 
 * with multiple cores where each processor or core probably has its own cache with 
 * its own copy of stopped. 
 * When one thread modifies its copy of this field, the other thread’s copy of stopped 
 * isn’t changed.
 * */

public class ThreadStopping_v1 {

	public static void main(String[] args)	{

		class StoppableThread extends Thread {
			private boolean stopped; // defaults to false

			@Override
			public void run() {
				while(!stopped)
					System.out.println("running");
			}

			void stopThread() {
				stopped = true;
			}
		}

		StoppableThread thd = new StoppableThread();
		thd.start();

		try	{
			Thread.sleep(1000); 
		} catch (InterruptedException ie){
			
		}

		thd.stopThread();
	}
}
