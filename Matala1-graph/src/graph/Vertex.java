package graph;

import java.util.ArrayList;

public class Vertex {
	public int name;
	public ArrayList<Edge> edges;
	public double dist;
	public int previous;
	public boolean visited;
	
	public Vertex(int name, double dist) {
		this.name = name; 
		this.dist = dist;
		previous = -1;
		visited = false;
	}
	
	@SuppressWarnings("unchecked")
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
