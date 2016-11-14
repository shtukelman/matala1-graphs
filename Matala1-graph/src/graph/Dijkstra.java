package graph;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Dijkstra {
	private Vertex[] vertices;
	private int source;
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
		for (int k = 0; k < vertices.length; k++) {
			for (int i = 0; i < vertices[k].edges.size(); i++) {
				for (int j = 0; j < vertices[k].edges.size(); j++) {
					if (vertices[i].edges.get(j).weight >= vertices[i].edges.get(k).weight + vertices[k].edges.get(j).weight){
						return false;
					}
				}
			}
		}
		return true;

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
