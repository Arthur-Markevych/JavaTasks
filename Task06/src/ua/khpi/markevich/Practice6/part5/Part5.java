package ua.khpi.markevich.Practice6.part5;

/**
 * Implementation of Tree.
 * 
 * @param <E>
 *            Tree type.
 */
public final class Part5<E extends Comparable<E>> {

	/**
	 * Node of tree.
	 */
	private Node root;

	/**
	 * Utility constant to use.
	 */
	private static final int THREE = 3;

	/**
	 * Utility constant to use.
	 */
	private static final int FOUR = 4;

	/**
	 * Utility constant to use.
	 */
	private static final int FIVE = 5;

	/**
	 * Utility constant to use.
	 */
	private static final int SIX = 6;

	/**
	 * Start point of application.
	 * 
	 * @param args
	 *            ignored by application.
	 */
	public static void main(final String[] args) {
		Part5<Integer> tree = new Part5<>();
		System.out.println(tree.add(THREE));
		System.out.println(tree.add(THREE));
		System.out.println("~~~~~~~");
		tree.add(new Integer[] { 1, 2, SIX, FIVE, FOUR, 0 });
		tree.printTree();
		System.out.println("~~~~~~~");
		System.out.println(tree.remove(FIVE));
		System.out.println(tree.remove(FIVE));
		System.out.println("~~~~~~~");
		tree.printTree();
	}

	/**
	 * Method returned true if element do not exists in tree before add. Else
	 * returned false.
	 * 
	 * @param element
	 *            specified element to add.
	 * @return Returned true if element do not exists in tree before add. Else
	 *         returned false.
	 */
	public boolean add(final E element) {
		E e = get(element);
		root = add(root, element);

		if (e == null) {
			return true;
		}

		return false;
	}

	/**
	 * Add specified array of type E.
	 * 
	 * @param elements
	 *            specified array of type E.
	 */
	public void add(final E[] elements) {
		for (E elem : elements) {
			add(elem);
		}
	}

	/**
	 * Returned element of type E if it exists in tree.
	 * 
	 * @param element
	 *            specified element to get.
	 * @return element of type E if it exists in tree
	 */
	public E get(final E element) {
		Node cur = root;
		for (; true;) {
			if (cur == null) {
				break;
			} else if (element.compareTo(cur.element) < 0) {
				cur = cur.left;
			} else if (element.compareTo(cur.element) > 0) {
				cur = cur.right;
			} else {
				break;
			}
		}
		if (cur != null) {
			return cur.element;
		}
		return (E) null;
	}

	/**
	 * Method returned True if element exists in tree and removed. Else false.
	 * 
	 * @param element
	 *            specified element to remove.
	 * @return True if element exists in tree and removed. Else false.
	 */
	public boolean remove(final E element) {
		Node current = root;
		Node previous = null;

		for (; true;) {
			if (current == null) {
				return false;
			}
			if (element.compareTo(current.element) < 0) {
				previous = current;
				current = current.left;
			} else if (element.compareTo(current.element) > 0) {
				previous = current;
				current = current.right;
			} else {
				break;
			}
		}

		if (current.right == null) {

			if (previous == null) {
				root = root.left;
			} else if (current == previous.right) {
				previous.right = current.left;
			} else {
				previous.left = current.left;
			}

		} else if (current.left == null) {

			if (previous == null) {
				root = root.left;
			} else if (current == previous.right) {
				previous.right = current.left;
			} else {
				previous.left = current.left;
			}
		} else {
			Node mostLeft = current.right;
			previous = null;
			for (; mostLeft.left != null;) {
				previous = mostLeft;
				mostLeft = mostLeft.left;
			}
			if (previous != null) {
				previous.left = mostLeft.right;
			} else {
				current.right = mostLeft.right;
				current.element = mostLeft.element;
				current.element = mostLeft.element;
			}
		}

		E elem = get(element);
		boolean res = (elem == null);

		return res;
	}

	/**
	 * Print node content to console.
	 * 
	 * @param node
	 *            specify node
	 * @param level
	 *            current level
	 */
	private void print(final Node node, final int level) {
		if (node != null) {
			print(node.left, level + 1);
			for (int i = 0; i < level; i++) {
				System.out.print("   ");
			}
			System.out.println(node.element);
			print(node.right, level + 1);

		}
	}

	/**
	 * Node of Tree.
	 * 
	 */
	private class Node {
		/**
		 * Element of node.
		 */
		private E element;
		/**
		 * Close left node.
		 */
		private Node left;
		/**
		 * Close right node.
		 */
		private Node right;

		/**
		 * Node constructor.
		 * 
		 * @param key
		 *            element
		 */
		Node(final E key) {
			this.element = key;
			this.left = null;
			this.right = null;
		}
	}

	/**
	 * Inner method for adding element.
	 * 
	 * @param node
	 *            specified Node.
	 * @param element
	 *            specified element
	 * @return Node.
	 */
	private Node add(final Node node, final E element) {
		if (node == null) {
			return new Node(element);
		}
		int compareResult = element.compareTo(node.element);
		if (compareResult == 0) {
			node.element = element;
			return node;
		} else if (compareResult < 0) {
			node.left = add(node.left, element);
		} else {
			node.right = add(node.right, element);
		}
		return node;
	}

	/**
	 * Print tree from up to down.
	 * 
	 */
	public void printTree() {
		print(root, 0);
	}

	/**
	 * Private constructor.
	 */
	private Part5() {

	}
}
