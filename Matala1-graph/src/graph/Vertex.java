package graph;

import java.util.ArrayList;

public class Vertex {
	public int name;
	public ArrayList<Edge> edges;
	public double dist;
	public int previous;
	public boolean visited;
	
	/**
	 * Constructor
	 * @param name number of the vertex
	 * @param dist
	 */
	public Vertex(int name, double dist) {
		this.name = name; 
		this.dist = dist;
		previous = -1;
		visited = false;
	}
	
	/**
	 * Copy constructor of vertex
	 * @param v Other Vertex
	 */
	public Vertex(Vertex v) {
		name = v.name; 
		dist = v.dist;
		previous = v.previous;
		visited = v.visited;
		edges=new ArrayList<Edge>();
		for (int i = 0; i < v.edges.size(); i++) {
			edges.add(new Edge(v.edges.get(i)));
		}
		
	}
	
	/**
	 * Get index of edge that holds vertex (v)
	 * @param v	Vertex
	 * @return index
	 */
	public int getEdgeIndex(int v)
	{
		for (int i = 0; i < edges.size(); i++) {
			if(edges.get(i).vert==v)
				return i;
		}
		return -1;
	}
	public String toString() {
		return "" + name; 
	}
}
