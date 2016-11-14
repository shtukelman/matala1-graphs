package graph;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Dijkstra {
	private Vertex[] vertices;
	private int source;
	private boolean TriangleInequality = true;
	private static double infinity = Double.POSITIVE_INFINITY;

	public Dijkstra(Vertex[] vs) {

		vertices = new Vertex[vs.length];
		for (int i = 0; i < vs.length; i++) {
			vertices[i] = vs[i];
		}
	}

	public void computePaths(int source) {
		this.source = source;
		Vertex s = vertices[source];
		s.dist = 0;
		HeapMin Q = new HeapMin();
		Q.minHeapInsert(s);
		for (int i = 0; i < vertices.length; i++) {
			if (i != source) {
				Q.minHeapInsert(vertices[i]);
			}
		}
		while (!Q.isEmpty()) {
			Vertex u = Q.heapExtractMin();
			// Visit each edge exiting u
			for (Edge e : u.edges) {
				Vertex v = vertices[e.vert];
				if (!v.visited) {
					double distU = u.dist + e.weight;
					if (distU <= v.dist && v.dist!=infinity){
						TriangleInequality = false;
					}
					if (distU < v.dist) {// relaxation
						v.dist = distU;
						v.previous = vertices[u.name].name;
						Q.heapDecreaseKey(v);
					}
				}
			}
			u.visited = true;
		}
	}

	public boolean isTriangleInequality() {
		for (int i = 0; i < vertices.length; i++) {
		computePaths(i);
		}
		return TriangleInequality;

	}

	public void printWeights() {
		System.out.print("weights: ");
		for (Vertex v : vertices) {
			System.out.print(new DecimalFormat("#.###").format(v.dist) + ", ");
		}
		System.out.println();
	}

	public String getPath(int v) {
		int t = v;
		String ans = t + "";
		while (t != source) {
			t = vertices[t].previous;
			ans = t + "->" + ans;
		}
		return ans;
	}

	public void printPaths() {
		for (Vertex v : vertices) {
			System.out.println("price of " + v.name + " = " + new DecimalFormat("#.###").format(v.dist) + ", path: "
					+ getPath(v.name));
		}
		System.out.println();
	}
}
