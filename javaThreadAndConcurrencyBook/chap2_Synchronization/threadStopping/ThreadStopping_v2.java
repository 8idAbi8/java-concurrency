package javaThreadAndConcurrencyBook.chap2_Synchronization.threadStopping;

/*
 * You might decide to use the synchronized keyword to make sure that only the 
 * main memory copy of stopped is accessed.
 * 
 * Bad idea:
 * Notice the while loop. 
 * This loop is unending because the thread executing the loop has acquired the lock to the 
 * current StoppableThread object (via synchronized(this)), and any attempt by the default main 
 * thread to call stopThread() on this object will cause the default main thread to block 
 * because the default main thread needs to acquire the same lock.
 * 
 * 
 * The reason this program never stops is due to a synchronization issue in the StoppableThread class. 
 * The thread's run() method contains a synchronized block that holds the lock on the 
 * StoppableThread instance itself. 
 * Within that block, there is a while loop that continuously checks the stopped flag, but it does 
 * not release the lock during each iteration of the loop.
 * 
 * Since the stopThread() method is also synchronized on the StoppableThread instance, it cannot be 
 * executed until the lock is released. 
 * However, since the lock is held by the thread itself, the stopThread() method cannot be called, 
 * and the stopped flag remains false indefinitely. 
 * As a result, the thread continues executing the while loop endlessly.
 * 
 * To fix this issue, you should release the lock during each iteration of the while loop by using
 *  wait() and notify() methods for proper synchronization.
 * */

public class ThreadStopping_v2 {

	public static void main(String[] args)	{

		class StoppableThread extends Thread {
			private boolean stopped; // defaults to false

			@Override
			public void run() {
				
				synchronized (this) {
					while(!stopped)
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
		} catch (InterruptedException ie){
			
		}

		thd.stopThread();
	}
}
