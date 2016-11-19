package graph;

public class Edge{
	public int vert;	//one side vertex
	public double weight;	//weight of edge
	
	/**
	 * Constructor
	 * @param v Vertex
	 * @param w Weight
	 */
	public Edge(int v, double w){ 
		vert = v; 
		weight = w; 
	}
	/**
	 * Copy constructor
	 * @param e	Edge
	 */
	public Edge(Edge e){ 
		vert = e.vert; 
		weight = e.weight; 
	}
	public String toString(){
		return ""+vert;
	}
}
