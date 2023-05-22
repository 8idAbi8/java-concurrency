package javaThreadAndConcurrencyBook.chap3_WaitingAndNotification.gate;

/*
 * Create an Await application that demonstrates a higher-level concurrency construct 
 * known as a gate. 
 * This construct permits multiple threads to arrive at a synchronization point (the gate) 
 * and wait until the gate is unlocked by another thread so that they can all proceed. 
 * 
 * The main() method first creates a runnable for the threads that will wait at the gate. 
 * The runnable prints a message stating that the thread is waiting, increments a counter, 
 * sleeps for 2 seconds, and waits (make sure to account for spurious wakeups). 
 * Upon wakeup, the thread outputs a message stating that the thread is terminating. 
 * main() then creates three Thread objects and starts three threads to execute the runnable. 
 * 
 * Next, main() creates another runnable that repeatedly sleeps for 200 milliseconds until 
 * the counter equals 3, at which point it notifies all waiting threads. 
 * Finally, main() creates a Thread object for the second runnable and starts the thread.
 * */

/*
 * In the provided code, the class Await is used as a lock object for synchronization purposes. 
 * It is used in the synchronized blocks within the Worker and WakeUp classes to establish a 
 * mutual exclusion region and enable inter-thread communication through the use 
 * of wait() and notifyAll() methods.
 * 
 * In summary, Await.class is used as a shared lock object to coordinate the execution of 
 * multiple worker threads and the wakeUp thread. 
 * It ensures that the worker threads wait until the wakeUp thread increments the 
 * counter variable to 3 and signals them to continue their execution using notifyAll().
 * */

public class Await {

	static volatile int counter;

	public static void main(String[] args) {
		new Await().go();
	}

	void go() {

		Worker worker = new Worker();

		for (int i = 0; i < 3; i++) {
			new Thread(worker, "Thread-" + i).start();
		}

		WakeUp wakeUp = new WakeUp();
		new Thread(wakeUp, "wakeUp").start();
	}


	class Worker implements Runnable {

		@Override
		public void run() {

			System.out.println(Thread.currentThread().getName() + " has entered runnable and is waiting");

			synchronized (Await.class) {
				counter++;

				try {
					Thread.sleep(1000);

					while(counter < 3) {
						Await.class.wait();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			System.out.println(Thread.currentThread().getName() + " has woken up and is terminating");
		}
	}


	class WakeUp implements Runnable {

		@Override
		public void run() {

			System.out.println("\n" + Thread.currentThread().getName() + " has entered runnable and is going to notifyAll");

			try {
				while (counter < 3)
					Thread.sleep(200);

				synchronized (Await.class) {
					Await.class.notifyAll();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}

