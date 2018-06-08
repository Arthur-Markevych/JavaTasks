package ua.khpi.markevich.Practice5.part1;

/**
 * Application prints to console ran threads names during 5 sec.
 * 
 * @author Arthur Markevich
 *
 */
public final class Part1 {
	/**
	 * Count of print lines.
	 */
	private static final int COUNT = 10;
	/**
	 * Thread sleep time setup.
	 */
	private static final int SLEEP = 500;

	/**
	 * Application start point.
	 * 
	 * @param args
	 *            is ignored
	 * @throws InterruptedException
	 *             if thread throw exception
	 */
	public static void main(final String[] args) throws InterruptedException {
		MyThread thread = new MyThread();
		Thread runable = new Thread(new MyRunable());
		thread.start();
		runable.start();
		thread.join();
		runable.join();
	}

	/**
	 * The method prints self thread name 10 times during 5 sec.
	 */
	public void run() {
		for (int i = 0; i < COUNT; i++) {
			System.out.println(Thread.currentThread());
			try {
				Thread.sleep(SLEEP);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}

/**
 * Thread implementation to run method <tt>run</tt> of <tt>Part1</tt>.
 *
 */
class MyThread extends Thread {
	/**
	 * Start printing self tread name.
	 */
	@Override
	public void run() {
		new Part1().run();
	}
}

/**
 * Runnable implementation to run method <tt>run</tt> of <tt>Part1</tt>.
 *
 */
class MyRunable implements Runnable {
	/**
	 * Start printing self tread name.
	 */
	@Override
	public void run() {
		new Part1().run();
	}

}
