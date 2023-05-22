package javaThreadAndConcurrencyBook.chap6_Synchronizers;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * The startSignal countdown latch prevents any worker thread from proceeding until 
 * the default main thread is ready for them to proceed. 
 * The doneSignal countdown latch causes the default main thread to wait 
 * until all worker threads have finished.
 * */

public class CountDownLatchDemo {

	final static int NTHREADS = 3;
	
	public static void main(String[] args) {
		
		final CountDownLatch startSignal = new CountDownLatch(1);
		final CountDownLatch doneSignal = new CountDownLatch(NTHREADS);
		
		Runnable r = new Runnable()	{
			
			@Override
			public void run() {
				try	{
					report("entered run() and is waiting to proceed");
					
					startSignal.await(); // wait until told to ...
					
					report("proceed and doing work"); // ... proceed
					
					Thread.sleep((int) (Math.random() * 3000));
					
					System.out.println(System.currentTimeMillis() +	": " 			
							+ Thread.currentThread() + " finished");
					doneSignal.countDown(); // reduce count on which main thread is waiting
				} 
				catch (InterruptedException ie)	{
					System.err.println(ie);
				}
			}
			
			void report(String s) {
				System.out.println(System.currentTimeMillis() +	": " 			
						+ Thread.currentThread() + ": " + s);
			}
		};
		
		ExecutorService executor = Executors.newFixedThreadPool(NTHREADS);
		for (int i = 0; i < NTHREADS; i++)
			executor.execute(r);
		
		try	{
			System.out.println("main thread sleep for 2 second");
			Thread.sleep(2000); // sleep for 2 second
			
			System.out.println("Let all threads proceed");
			startSignal.countDown(); // let all threads proceed
			
			System.out.println("\nWaiting for all threads to finish");			
			doneSignal.await(); // wait for all threads to finish
			
			System.out.println("All threads finished");
			System.out.println("executor.shutdownNow()");
			executor.shutdownNow();
		}
		catch (InterruptedException ie)	{
			System.err.println(ie);
		}
	}
}
