package ua.khpi.markevich.Practice2;

/**
 * Self list iterator interface.
 * 
 * @author Arthur Markevich
 *
 */
public interface MyList extends Iterable<Object> {

	/**
	 * Self implementation of ArrayList. Not exactly the same. The array has no
	 * indexing.
	 */

	/**
	 * Adding Object e to the end of Array.
	 * 
	 * @param e
	 *            element
	 */
	void add(Object e);

	/**
	 * Clearing collection.
	 */
	void clear();

	/**
	 * Remove element o from collection if collection contains o.
	 * 
	 * @param o
	 *            element
	 * @return true if o was removed else false
	 */
	boolean remove(Object o);

	/**
	 * Converts collection to Object[].
	 * 
	 * @return new Object array contains all elements of collection
	 */
	Object[] toArray();

	/**
	 * Size of collection.
	 * 
	 * @return count elements of collection
	 */
	int size();

	/**
	 * Check if collection contains o.
	 * 
	 * @param o
	 *            element
	 * @return true if collection contains o else return false
	 */
	boolean contains(Object o);

	/**
	 * Check if collection contains all elements of c.
	 * 
	 * @param c
	 *            collection of elements
	 * @return true if contains
	 */
	boolean containsAll(MyList c);

}
