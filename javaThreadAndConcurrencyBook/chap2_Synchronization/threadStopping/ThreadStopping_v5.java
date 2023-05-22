package javaThreadAndConcurrencyBook.chap2_Synchronization.threadStopping;

/* Version with wait() and notify().
 * 
 * In the given code, we cannot use StoppableThread.class.wait() instead of wait() 
 * because StoppableThread.class.wait() would be waiting on the lock associated with the 
 * StoppableThread class object, not on the lock associated with the current instance 
 * of the StoppableThread object.
 * 
 * When you use wait() within an instance method, such as run() in this case, it implicitly refers 
 * to the lock associated with the current instance of the class. 
 * In other words, it releases the lock held by the current instance and waits for notification.
 * 
 * On the other hand, when you use StoppableThread.class.wait(), it refers to the lock associated 
 * with the StoppableThread class object itself. 
 * This means it would be waiting on a different lock and not releasing the lock held by the 
 * current instance. 
 * */

public class ThreadStopping_v5 {

	public static void main(String[] args)	{

		class StoppableThread extends Thread {
			
			private boolean stopped; // defaults to false

			@Override
			public void run() {
				
				synchronized (this) {
					
					while(!stopped) {
						
						try {
							System.out.println("Running");
								wait();		// equivalente a: this.wait();  non equivalente a: StoppableThread.class.wait()
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					System.out.println("Stoped");
				}
			}

			synchronized void stopThread() {    // equivalente a: this.wait();  non equivalente a: StoppableThread.class.wait()
				/*
				 * L'uso di synchronized su un metodo non statico implica che il blocco 
				 * sincronizzato è associato all'istanza su cui il metodo viene chiamato.
				 * 
				 * il metodo stopThread() viene chiamato sull'istanza thd di StoppableThread. 
				 * Quindi, il blocco sincronizzato associato a thd viene acquisito, 
				 * consentendo all'istanza corrente di eseguire il codice all'interno del blocco sincronizzato.
				 * */
				stopped = true;
				notify();
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
