package ua.khpi.markevich.Practice5.part3;

/**
 * Application is comparing and changing counters.
 * 
 * @author Arthur Markevich
 *
 */
public class Part3 extends Thread {

	/**
	 * Threads count.
	 */
	public static final int THREADS_COUNT = 5;

	/**
	 * Thread sleeping time(milliseconds).
	 */
	public static final int SLEEP = 10;

	/**
	 * Counter number 1.
	 */
	private int firstCount;

	/**
	 * Counter number 2.
	 */
	private int secondCount;

	/**
	 * Flag if <tt>lock</tt> is <tt>true</tt> method runs synchronized.
	 */
	private boolean lock = false;

	/**
	 * Set flag to instance.
	 * 
	 * @param lock
	 *            if true method will be synchronized
	 */
	public void setLock(final boolean lock) {
		this.lock = lock;
	}

	/**
	 * Application start point.
	 * 
	 * @param args
	 *            is ignored
	 * @throws InterruptedException
	 *             in case of thread exception
	 */
	public static void main(final String[] args) throws InterruptedException {
		Part3 part3 = new Part3();
		Thread t = null;

		part3.setLock(true);
		System.out.println("Synchronized method input:");
		for (int i = 0; i < THREADS_COUNT; i++) {
			t = new Thread(part3);
			t.start();
			t.join();
		}

		part3.setLock(false);
		System.out.print("No synchronized method input:");
		for (int i = 0; i < THREADS_COUNT; i++) {
			t = new Thread(part3);
			t.start();
		}
		t.join();
	}

	/**
	 * Start method <tt>print</tt>.
	 * 
	 * @see {@link #print()}
	 */
	@Override
	public void run() {
		if (lock) {
			synchronized (this) {
				print();
			}
		} else {
			print();
		}
	}

	/**
	 * Prints result of comparison of two counters.
	 */
	private void print() {
		System.out.println(compare());
		incrementFirstCount();
		try {
			Thread.sleep(SLEEP);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		incrementSecondCount();
	}

	/**
	 * Comparing counters.
	 * 
	 * @return true if counters have the same value
	 */
	public boolean compare() {
		return firstCount == secondCount;
	}

	/**
	 * Increment first counter.
	 */
	public void incrementFirstCount() {
		this.firstCount++;
	}

	/**
	 * Increment second counter.
	 */
	public void incrementSecondCount() {
		this.secondCount++;
	}

}
