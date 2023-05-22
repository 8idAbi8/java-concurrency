package javaThreadAndConcurrencyBook.chap1_ThreadAndRunnable;

/*
 * Write an IntSleep application that creates a background thread to repeatedly output 
 * Hello and then sleep for 100 milliseconds. 
 * After sleeping for 2 seconds, the default main thread should interrupt the background 
 * thread, which should break out of the loop after outputting interrupted.
*/

public class IntSleep_v1 {
	
	/*
	 * In questa versione gestiamo correttamente l'interruzione del thread, 
	 * utilizzando l'istruzione "break" .
	 * */

	public static void main(String[] args) {
		
		Runnable r = () -> {
			while(!Thread.currentThread().isInterrupted()) {  // andrebbe bene anche un while(true)  
				try {
					System.out.println("Hello");
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
					
					break;
				}
			}
		};
		
		Thread t = new Thread(r);
		t.start();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		t.interrupt();
	}
}
