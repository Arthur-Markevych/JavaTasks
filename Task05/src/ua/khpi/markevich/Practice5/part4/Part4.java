package ua.khpi.markevich.Practice5.part4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Application find max number of 2D matrix.
 * 
 * @author Arthur Markevich
 *
 */
public class Part4 implements Runnable {

	/**
	 * Count rows of the matrix.
	 */
	private static final int M = 4;

	/**
	 * Count columns of the matrix.
	 */
	private static final int N = 100;

	/**
	 * Max value allowed to be in the matrix.
	 */
	private static final int BOUND = 2000;

	/**
	 * Added milliseconds for synchronized access to find the max value.
	 */
	private static final int EXTRA_TIME = 100;

	/**
	 * Matrix instance.
	 */
	private Matrix matrix;

	/**
	 * Count of threads which is working on.
	 */
	private static volatile int threadNumber;

	/**
	 * Max value of the matrix.
	 */
	private int max;

	/**
	 * Start point of application.
	 * 
	 * @param args
	 *            is ignored
	 * @throws InterruptedException
	 *             in case of thread exception
	 */
	public static void main(final String[] args) throws InterruptedException {
		Matrix matrix = new Matrix(M, N, BOUND);
		Part4 multiThread = new Part4(matrix);
		Part4 singleThread = new Part4(matrix);

		long start;
		long end;
		long result;

		List<Thread> threads = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			threads.add(new Thread(multiThread));
		}
		start = System.nanoTime();
		for (Thread thread : threads) {
			thread.start();
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		end = System.nanoTime();
		Thread.sleep(N + EXTRA_TIME);
		result = Part4.getMillis(end - start);
		System.out.println("(multi thread) max value is: [ " + multiThread.getMax() + " ] time: " + result + " ms");

		start = System.nanoTime();
		int m2 = singleThread.findMax();
		end = System.nanoTime();
		result = Part4.getMillis(end - start);
		System.out.println("(single thread) max value is: [ " + m2 + " ] time: " + result + " ms");

	}

	/**
	 * Find max value in the row of the matrix.
	 * 
	 * @param m
	 *            number of the row
	 */
	private void findMax(final int m) {
		for (int num : this.matrix.getMatrix()[m]) {
			if (num > max) {
				max = num;
			}
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.getMessage();
			}
		}
	}

	/**
	 * Find max value in the matrix.
	 * 
	 * @return max value
	 */
	public int findMax() {
		int maxN = 0;
		for (int[] nums : matrix.getMatrix()) {
			for (int i : nums) {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.getMessage();
				}
				if (i > maxN) {
					maxN = i;
				}
			}
		}
		return maxN;
	}

	/**
	 * Convert nanoseconds to milliseconds.
	 * 
	 * @param nanoSeconds
	 *            nanoseconds
	 * @return milliseconds
	 */
	private static long getMillis(final long nanoSeconds) {
		return TimeUnit.NANOSECONDS.toMillis(nanoSeconds);
	}

	/**
	 * Starting thread.
	 */
	@Override
	public void run() {
		findMax(threadNumber++);
	}

	/**
	 * Find max value from matrix.
	 * 
	 * @return max value
	 */
	public int getMax() {
		return max;
	}

	/**
	 * Constructor.
	 * 
	 * @param matrixInstance
	 *            the matrix instance which will be used
	 */
	public Part4(final Matrix matrixInstance) {
		this.matrix = matrixInstance;
	}
}

/**
 * Application creates 2D matrix and fill it with random numbers.
 *
 */
class Matrix {

	/**
	 * Matrix 2D array.
	 */
	private int[][] matrix;

	/**
	 * Count rows of the matrix.
	 */
	private int m;

	/**
	 * Count columns of the matrix.
	 */
	private int n;

	/**
	 * Max value to be in the matrix.
	 */
	private int bound;

	/**
	 * <tt>Random</tt> instance.
	 * 
	 * @see {@link Random}
	 */
	private static final Random RANDOM = new Random();

	/**
	 * Get created matrix.
	 * 
	 * @return matrix
	 */
	public int[][] getMatrix() {
		return matrix;
	}

	/**
	 * Constructor create new matrix and fill it with random numbers.
	 * 
	 * @param rows
	 *            count rows of the matrix
	 * @param columns
	 *            count columns of the matrix
	 * @param maxRandomValue
	 *            max random value allowed to fill the matrix
	 */
	Matrix(final int rows, final int columns, final int maxRandomValue) {
		this.m = rows;
		this.n = columns;
		this.bound = maxRandomValue;
		fillTheMatrix();
	}

	/**
	 * Fill the matrix with random numbers.
	 */
	public void fillTheMatrix() {
		matrix = new int[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				matrix[i][j] = RANDOM.nextInt(bound);
			}
		}
	}

	/**
	 * Print created matrix to console.
	 */
	public void printTheMatrix() {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}
}
