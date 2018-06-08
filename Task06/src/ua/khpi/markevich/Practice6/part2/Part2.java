package ua.khpi.markevich.Practice6.part2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * The class is modeling child game where n children stand in the circle and
 * count k times and then person which will be spotted is leaving the circle and
 * operation repeat again while the circle has more than n-1 persons.
 * 
 * Class is implementing to methods of the operation, use ArrayList and
 * LinkedList, After execution application shows resulting performance time of
 * the methods.
 * 
 * @author Arthur Markevich
 *
 */
public final class Part2 {

	/**
	 * Number of persons.
	 */
	private static final int N = 100_000;

	/**
	 * Size of count to throw out person from the circle.
	 */
	private static final int K = 40;

	/**
	 * ArrayList to store persons.
	 */
	private static List<Integer> aList = new ArrayList<>();

	/**
	 * LinkedList to store persons.
	 */
	private static List<Integer> lList = new LinkedList<>();

	/**
	 * Application start point. Check speed of perform of each methods and prints
	 * result time to console.
	 * 
	 * @param args
	 *            is ignored
	 */
	public static void main(final String[] args) {
		fillList(aList, N);
		fillList(lList, N);

		long start, result;

		start = System.nanoTime();
		comeAround(aList, K);
		result = System.nanoTime() - start;
		System.out.println("ArrayList time: " + TimeUnit.NANOSECONDS.toMillis(result));

		start = System.nanoTime();
		comeAround(lList, K);
		result = System.nanoTime() - start;
		System.out.println("LinkedList time: " + TimeUnit.NANOSECONDS.toMillis(result));
	}

	/**
	 * The method removes each k element from list in the loop.
	 * 
	 * @param list
	 *            which will be counted
	 * @param k
	 *            size of count
	 */
	private static void comeAround(final List<Integer> list, final int k) {
		int count = 0;
		Iterator<Integer> it = list.iterator();
		while (list.size() > 1) {
			if (it.hasNext()) {
				it.next();
				count++;
				if (count == k) {
					it.remove();
					count = 0;
				}
			} else {
				it = list.iterator();
			}
		}
	}

	/**
	 * Fill list with numbers.
	 * 
	 * @param list
	 *            which will be filled
	 * @param size
	 *            count of list notes
	 */
	private static void fillList(final List<Integer> list, final int size) {
		for (int i = 0; i < size; i++) {
			list.add(i + 1);
		}
	}

	/**
	 * Private constructor.
	 */
	private Part2() {
		// TODO Auto-generated constructor stub
	}

}
