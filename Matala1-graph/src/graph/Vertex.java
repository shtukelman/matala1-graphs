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
	public Vertex(Vertex v) {
		this.name = v.name; 
		this.dist = v.dist;
		previous = v.previous;
		visited = v.visited;
		
	}
	public String toString() {
		return "" + name; 
	}
}
