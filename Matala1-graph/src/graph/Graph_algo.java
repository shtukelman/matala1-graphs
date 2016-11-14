package graph;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Vertex {
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
class Edge{
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

public class Graph_algo {
	Vertex[] vertices;
	int source;
	public static double infinity = Double.POSITIVE_INFINITY;
	public Graph_algo(Vertex[] vs, int source){
		this.source = source;
		vertices = new Vertex[vs.length];
		for (int i=0; i<vs.length; i++){
			vertices[i] = vs[i];
		}
	}

	public void computePaths(){
		Vertex s = vertices[source];
		s.dist = 0.;
		HeapMin Q = new HeapMin();
		Q.minHeapInsert(s);
		for (int i=0; i<vertices.length; i++){
			if (i!=source){
			Q.minHeapInsert(vertices[i]);
			}
		}
		while (!Q.isEmpty()) {
			Vertex u = Q.heapExtractMin();
			// Visit each edge exiting u
			for (Edge e : u.edges){
				Vertex v = vertices[e.vert];
				if (!v.visited){
					double distU = u.dist + e.weight;
					if (distU < v.dist) {//relaxation
						v.dist = distU ;
						v.previous = vertices[u.name].name;
						Q.heapDecreaseKey(v);
					}
				}
			}
			u.visited = true;
		}
	}

	public void printWeights(){
		System.out.print("weights: ");
		for (Vertex v : vertices) {
			System.out.print(new DecimalFormat("#.###").format(v.dist)+", ");
		}
		System.out.println();
	}
	public String getPath(int v){
		int t = v;
		String ans = t + "";
		while(t != source){
			t = vertices[t].previous;
			ans = t + "->" + ans;
		}
		return ans;
	}
	public void printPaths(){
		for (Vertex v : vertices){
			System.out.println("price of " + v.name+" = " +new DecimalFormat("#.###").format(v.dist) + ", path: " +  getPath(v.name));
		}
		System.out.println();
	}

	/**
	 * 
	 * @param s String line
	 * @return integer
	 */
	public static int StringToInt(String s)
	{
		return Integer.parseInt(s);
	}
	
	public static void main(String[] args) throws IOException {
		long startTime = System.currentTimeMillis();
		File file=new File("G0.txt");	//choose file
		int vertex_number,edge_number;
		String line;		//read file by lines
	    StringTokenizer st;		//Spliting the string
	    
		file.createNewFile();
		BufferedReader br=new BufferedReader(new FileReader(file));		//create a buffer for reading file
		vertex_number=Integer.parseInt(line=br.readLine());		//read num of vertex
		edge_number=Integer.parseInt(line=br.readLine());		//read num of edges
		
		Vertex[] vs=new Vertex[vertex_number];		//vertex array	
		for (int i = 0; i < vertex_number; i++) {
			vs[i]=new Vertex(i,infinity);
			vs[i].edges=new ArrayList<>();
		}
		
		//read the file text and fill in the array
		while((line = br.readLine()) != null)
		{
			st=new StringTokenizer(line);
			int v1=StringToInt(st.nextToken()),v2=StringToInt(st.nextToken());
			double weight=Double.parseDouble(st.nextToken());
			vs[v1].edges.add(new Edge(v2,weight));
			vs[v2].edges.add(new Edge(v1,weight));
		}
		br.close();

		//run dijkstra algo
		for (int i = 0; i < vs.length; i++) {
			for (int j = 0; j < vs[i].edges.size(); j++) {
				System.out.print(vs[i].edges.get(j)+" ");
			}
			System.out.println();
		}
		Graph_algo ds = new Graph_algo(vs,5);	
		ds.computePaths();
		
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println(totalTime+" ms");
		
		ds.printWeights();
		ds.printPaths();
		
 
		
	}
}