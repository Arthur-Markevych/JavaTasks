package ua.khpi.markevich.Practice6.part4;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * The class represents undirected graph.
 * 
 * @author Arthur Markevich
 *
 */
public class Part4 {

	/**
	 * Used vertex.
	 */
	private static final int V3 = 3;
	/**
	 * Used vertex.
	 */
	private static final int V6 = 6;
	/**
	 * Used vertex.
	 */
	private static final int V7 = 7;

	/**
	 * Store graph.
	 */
	private Map<Object, HashSet<Object>> map;

	/**
	 * Size of graph storage.
	 */
	private int count;

	/**
	 * Application start point. Show work with graph.
	 * 
	 * @param args
	 *            ignored by application
	 */
	public static void main(final String[] args) {

		Part4 graph = new Part4(V7);
		graph.addVertex(1);
		graph.addVertex(V7);
		graph.addEdge(1, V7);

		graph.addVertex(2);
		graph.addVertex(V6);
		graph.addEdge(2, V6);

		graph.addVertex(V3);
		graph.addEdge(V3, 2);

		System.out.println("Graph contains vertex:\n" + graph.getMap());
		System.out.println("Pares of 2\n" + graph.getPair(2));
	}

	/**
	 * Create new graph.
	 * 
	 * @param count
	 *            size of graph
	 */
	public Part4(final int count) {
		this.count = count;
		map = new HashMap<>(count);
	}

	/**
	 * Add vertex to graph.
	 * 
	 * @param v
	 *            vertex
	 */
	public void addVertex(final int v) {
		if (map.containsKey(v)) {
			System.out.println("vertex already exists");
		}
		if (map.size() < count) {
			map.put(v, new HashSet<>());
		}

	}

	/**
	 * Add edge to graph.
	 * 
	 * @param v1
	 *            vertex a
	 * @param v2
	 *            vertex b
	 */
	public void addEdge(final Integer v1, final Integer v2) {
		if (!(map.containsKey(v1) || map.containsKey(v2))) {
			System.out.println("the graph not contans both of two vertexes");
		}
		if (map.size() < count) {
			map.get(v1).add(v2);
			map.get(v2).add(v1);
		}
	}

	/**
	 * Remove vertex from graph.
	 * 
	 * @param v
	 *            vertex
	 */
	private void removeVertex(final int v) {
		if (!map.containsKey(v)) {
			System.out.println("vertex " + v + " doesn't exist");
		}
		map.remove(v);
	}

	/**
	 * Remove edge from graph.
	 * 
	 * @param v1
	 *            vertex a
	 * @param v2
	 *            vertex b
	 */
	public void removeEdge(final Integer v1, final Integer v2) {
		if (!(map.containsKey(v1) || map.containsKey(v2))) {
			System.out.println("the graph not contans both of two vertexes");
		}
		removeVertex(v1);
		removeVertex(v2);
	}

	/**
	 * Find connected pairs of vertex.
	 * 
	 * @param v
	 *            vertex
	 * @return founded pairs
	 */
	public Set<Object> getPair(final Integer v) {
		return map.get(v);
	}

	/**
	 * Get all vertexes from the graph.
	 * 
	 * @return all vertexes of graph
	 */
	public Set<Object> getMap() {
		return map.keySet();
	}

}
