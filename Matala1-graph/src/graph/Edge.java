package graph;

public class Edge{
	public  int vert;
	public double weight;
	public Edge(int v, double w){ 
		vert = v; 
		weight = w; 
	}
	public Edge(Edge e){ 
		vert = e.vert; 
		weight = e.weight; 
	}
	public String toString(){
		return ""+vert;
	}
}
