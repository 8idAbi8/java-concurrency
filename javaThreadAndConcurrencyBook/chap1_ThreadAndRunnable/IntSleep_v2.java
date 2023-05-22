package javaThreadAndConcurrencyBook.chap1_ThreadAndRunnable;

/*
 * Write an IntSleep application that creates a background thread to repeatedly output 
 * Hello and then sleep for 100 milliseconds. 
 * After sleeping for 2 seconds, the default main thread should interrupt the background 
 * thread, which should break out of the loop after outputting interrupted.
*/

public class IntSleep_v2 {	
	/*
	 * In questa versione gestiamo correttamente l'interruzione del thread, 
	 * utilizzando una variabile booleana come flag di controllo per indicare 
	 * se il thread è stato interrotto.
	 * */

	public static void main(String[] args) {

		Runnable r = () -> {
			
			boolean isInterrupted = false;
			
			while(!isInterrupted) {  
				try {
					System.out.println("Hello");
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
					
					// variabile booleana come flag di controllo per indicare se il thread è stato interrotto
					isInterrupted = true;
				}
			}
		};
		
		Thread t = new Thread(r);
		t.start();
		
		try {
			// main thread
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		t.interrupt();		
	}
}
