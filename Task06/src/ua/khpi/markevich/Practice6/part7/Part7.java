package ua.khpi.markevich.Practice6.part7;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Application prints interval numbers from n to k and from k to n.
 * 
 * @author Arthur Markevich
 *
 */
@SuppressWarnings("rawtypes")
public class Part7 implements Iterable {

	/**
	 * Start number.
	 */
	private int from;

	/**
	 * End number.
	 */
	private int to;

	/**
	 * Flag to reverse printing.
	 */
	private boolean reverse;

	/**
	 * Default value of start.
	 */
	public static final int FROM = 4;

	/**
	 * Default value of end.
	 */
	public static final int TO = 15;

	/**
	 * Application start point.
	 * 
	 * @param args
	 *            ignored by application
	 */
	public static void main(final String[] args) {
		Part7 normal = new Part7(FROM, TO, false);
		Part7 reverse = new Part7(FROM, TO, true);

		print(normal);
		System.out.println();
		print(reverse);
	}

	/**
	 * Start printing of numbers.
	 * 
	 * @param instance
	 *            of class
	 */
	public static void print(final Part7 instance) {
		for (Object num : instance) {
			System.out.print(num + " ");
		}
	}

	/**
	 * Constructor set up values for printing.
	 * 
	 * @param from
	 *            low number point
	 * @param to
	 *            high number point
	 * @param reverse
	 *            if true printing will be from high point to low point
	 */
	public Part7(final int from, final int to, final boolean reverse) {
		if (from > to) {
			throw new IllegalArgumentException("from must be garater than to");
		}
		this.from = from;
		this.to = to;
		this.reverse = reverse;
	}

	/**
	 * Obtain new IteratorRange instance.
	 */
	@Override
	public Iterator iterator() {
		return new IteratorRange();
	}

	/**
	 * 
	 * Iterator fill rung of counting from start number to end number. Able to
	 * reverse iteration.
	 * 
	 */
	private class IteratorRange implements Iterator {

		/**
		 * Low number.
		 */
		private Integer start;
		/**
		 * High number.
		 */
		private int end;

		/**
		 * Constructor set up iteration way.
		 */
		IteratorRange() {
			if (reverse) {
				start = to + 1;
				end = from;
			} else {
				start = from - 1;
				end = to;
			}
		}

		/**
		 * Check if iteration has next element.
		 * 
		 * @return false if iteration has no more elements
		 */
		@Override
		public boolean hasNext() {
			if (reverse) {
				return start > end;
			} else {
				return start < end;
			}
		}

		/**
		 * Obtain next element from iteration.
		 * 
		 * @return next element
		 * @throws NoSuchElementException
		 *             if iteration has no more elements
		 */
		@Override
		public Object next() {
			if (start == null) {
				throw new NoSuchElementException();
			}
			if (reverse) {
				start--;
			} else {
				start++;
			}
			return start;
		}

		/**
		 * Unsupported method.
		 * 
		 * @see java.util.Iterator#remove()
		 */
		@Override
		public void remove() {
			throw new UnsupportedOperationException("remove");
		}

	}

}
