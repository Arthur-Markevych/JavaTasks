package ua.khpi.markevich.Practice2;

import java.util.Iterator;

/**
 * ListIterator interface for self implementation.
 * 
 * @author Arthur Markevich
 *
 */
interface ListIterator extends Iterator<Object> {

	/**
	 * Check if collection has previous element.
	 * 
	 * @return true if collection has one more previous element
	 */
	boolean hasPrevious();

	/**
	 * Return previous element from collection.
	 * 
	 * @return previous element
	 */
	Object previous();

	/**
	 * Set element e to the current place of iterator.
	 * 
	 * @param e
	 *            element
	 */
	void set(Object e);

	/**
	 * Remove element on the current place of iterator.
	 */
	void remove();

}
