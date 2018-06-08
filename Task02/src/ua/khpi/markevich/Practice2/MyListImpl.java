package ua.khpi.markevich.Practice2;

import java.util.Iterator;

public class MyListImpl implements MyList, ListIterable {

	/**
	 * Default initial capacity.
	 */
	private static final int DEFAULT_CAPACITY = 10;

	/**
	 * The array buffer into which the elements of the ArrayList are stored. The
	 * capacity of the ArrayList is the length of this array buffer. Any empty
	 * ArrayList with elementData == DEFAULTCAPACITY will be expanded to
	 * DEFAULT_CAPACITY when the first element is added.
	 */
	private Object[] elements;

	/**
	 * The size of the ArrayList (the number of elements it contains).
	 */
	private int size = 0;

	/**
	 * Constructs an empty list with the default capacity.
	 */
	public MyListImpl() {
		this.elements = new Object[DEFAULT_CAPACITY];
	}

	/**
	 * Returns the number of elements in this list.
	 *
	 * @return the number of elements in this list
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Appends the specified element to the end of this list.
	 *
	 * @param e
	 *            element element to be appended to this list.
	 * @throws IllegalArgumentException
	 *             if e == null
	 */
	@Override
	public void add(Object e) {
		if (e == null) {
			throw new IllegalArgumentException("null is not allowed");
		}
		if (size == elements.length) {
			ensureCapa();
		}
		elements[size++] = e;
	}

	/**
	 * Initialize <tt>elements</tt> with empty Object[] with default capacity. And
	 * reset <tt>size</tt> to 0.
	 */
	@Override
	public void clear() {
		elements = new Object[elements.length];
		size = 0;
	}

	/**
	 * Removes the first occurrence of the specified element from this list, if it
	 * is present. If the list does not contain the element, it is unchanged. More
	 * formally, removes the element with the lowest index <tt>i</tt> such that
	 * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt> (if
	 * such an element exists). Returns <tt>true</tt> if this list contained the
	 * specified element (or equivalently, if this list changed as a result of the
	 * call).
	 *
	 * @param o
	 *            element to be removed from this list, if present
	 * @return <tt>true</tt> if this list contained the specified element
	 */
	@Override
	public boolean remove(Object o) {
		for (int i = 0; i < elements.length; i++) {
			if (elements[i] != null) {
				if (elements[i].equals(o)) {
					size--;
					Object[] newArray = new Object[size];
					elements[i] = null;
					if (i < size + 1) {
						System.arraycopy(elements, 0, newArray, 0, i);
					}
					System.arraycopy(elements, i + 1, newArray, i, size - i);
					elements = newArray;
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Returns an array containing all of the elements in this list in proper
	 * sequence (from first to last element).
	 *
	 * <p>
	 * The returned array will be "safe" in that no references to it are maintained
	 * by this list. (In other words, this method must allocate a new array). The
	 * caller is thus free to modify the returned array.
	 *
	 * @return an array containing all of the elements in this list in proper
	 *         sequence
	 */
	@Override
	public Object[] toArray() {
		Object[] newArray = new Object[size];
		System.arraycopy(elements, 0, newArray, 0, size);
		return newArray;
	}

	/**
	 * Returns <tt>true</tt> if this list contains the specified element. More
	 * formally, returns <tt>true</tt> if and only if this list contains at least
	 * one element <tt>e</tt> such that
	 * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</tt>.
	 *
	 * @param o
	 *            element whose presence in this list is to be tested
	 * @return <tt>true</tt> if this list contains the specified element
	 */
	@Override
	public boolean contains(Object o) {
		for (Object e : elements) {
			if (e != null) {
				if (o.equals(e)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Check if collection contains all elements of c.
	 * 
	 * @return {@code true} if the collection contains all elements of c
	 */
	@Override
	public boolean containsAll(MyList c) {
		for (Object o : c.toArray()) {
			if (!contains(o)) {
				return false;
			}
		}
		return true;
	}

	private void ensureCapa() {
		int newSize = elements.length * 2;
		Object[] newElements = new Object[newSize];
		System.arraycopy(elements, 0, newElements, 0, elements.length);
		this.elements = newElements;
	}

	/**
	 * Returns an iterator over the elements in this list in proper sequence.
	 *
	 * @return an iterator over the elements in this list in proper sequence
	 */
	@Override
	public Iterator<Object> iterator() {
		return new IteratorImpl();
	}

	/**
	 * Returns a list iterator over the elements in this list (in proper sequence).
	 */
	@Override
	public ListIterator listIterator() {
		return new ListIteratorImpl();
	}

	private class ListIteratorImpl extends IteratorImpl implements ListIterator {

		@Override
		public synchronized boolean hasPrevious() {
			return cursor != 0;
		}

		@Override
		public synchronized Object previous() {
			if (!hasPrevious()) {
				throw new IllegalStateException();
			}
			int i = cursor;
			Object[] elementData = MyListImpl.this.elements;
			cursor = i - 1;
			lastRet = cursor;
			return elementData[lastRet];
		}

		@Override
		public synchronized void set(Object e) {
			if (lastRet < 0) {
				throw new IllegalStateException();
			}
			MyListImpl.this.elements[cursor] = e;

		}
	}

	private class IteratorImpl implements Iterator<Object> {

		protected int cursor;
		protected int lastRet = -1;

		@Override
		public boolean hasNext() {
			return cursor != size;
		}

		@Override
		public Object next() {
			if (!hasNext()) {
				throw new IllegalStateException();
			}
			int i = cursor;
			cursor = i + 1;
			lastRet = i;
			return elements[lastRet];
		}

		@Override
		public void remove() {
			if (lastRet < 0) {
				throw new IllegalStateException();
			}
			MyListImpl.this.remove(MyListImpl.this.elements[lastRet]);
			cursor = lastRet;
			lastRet = -1;
		}
	}

	/**
	 * Returns a string representation of the collections elements.
	 * 
	 * @return a string representation of the collections elements
	 */
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder(size);
		s.append("[");
		if (elements[0] != null) {
			for (int i = 0; i < size; i++) {
				if (i < size - 1) {
					s.append(elements[i]);
					s.append(", ");
				} else {
					s.append(elements[i]);
				}
			}
		}
		s.append("]");
		return s.toString();
	}

}
