package graph;

public class Edge{
	public  int vert;
	public double weight;
	public Edge(int v, double w){ 
		vert = v; 
		weight = w; 
	}
	public String toString(){
		return ""+vert;
	}
}
