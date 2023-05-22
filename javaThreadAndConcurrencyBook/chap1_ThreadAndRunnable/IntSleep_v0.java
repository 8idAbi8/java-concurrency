package javaThreadAndConcurrencyBook.chap1_ThreadAndRunnable;

/*
 * Write an IntSleep application that creates a background thread to repeatedly output 
 * Hello and then sleep for 100 milliseconds. 
 * After sleeping for 2 seconds, the default main thread should interrupt the background 
 * thread, which should break out of the loop after outputting interrupted.
*/

public class IntSleep_v0 {
	
	/*   --- Versione non funzionante correttamente, per far vedere il problema 
	 * 		 della corretta gestione dell'interruzione del thread.				  ---
	 * 
	 * Nel programma fornito, la condizione while(!Thread.currentThread().isInterrupted())
	 * non è sufficiente a terminare il programma perche' l'interruzione del thread non 
	 * viene gestita correttamente.
	 * 
	 * Quando viene chiamato il metodo t.interrupt() per interrompere il thread, la 
	 * chiamata imposta il flag di interruzione del thread, ma non causa immediatamente 
	 * l'uscita dal ciclo while nel thread in esecuzione. 
	 * 
	 * La condizione del ciclo while viene controllata solo dopo che il thread completa 
	 * l'intero blocco try-catch.
	 * 
	 * Nel codice fornito, quando viene sollevata un'eccezione InterruptedException 
	 * all'interno del blocco try-catch, il flag di interruzione viene automaticamente 
	 * cancellato e la condizione del ciclo while diventa falsa.  
	 * Ciò significa che il thread continuerà ad eseguire il ciclo while nonostante 
	 * l'interruzione.
	 * 
	 * Per correggere il problema, è necessario gestire l'interruzione in modo appropriato
	 * all'interno del blocco catch. 
	 * Una soluzione comune consiste nel rompere il ciclo while utilizzando un'istruzione 
	 * break o utilizzare un'altra condizione per uscire dal ciclo.
	 * 
	 * */

	public static void main(String[] args) {
		
		Runnable r = () -> {
			
			while(!Thread.currentThread().isInterrupted()) {  
				
				try {
					System.out.println("Hello");
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
					
					// gestione non corretta dell'interruzione del thread
					// il programma non termina
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
