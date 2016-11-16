package graph;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.StringTokenizer;

public class Dijkstra {
	private Vertex[] vertices;
	private Graph graph;
	private int source;
	private boolean TriangleInequality = true;
	private static double infinity = Double.POSITIVE_INFINITY;
	
	public Dijkstra(Graph graph) {
		this.graph=new Graph(graph);
		vertices=this.graph.getVertexGraph();
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
	public void printPath(Vertex v1,Vertex v2) {
		System.out.println("price of " + v1.name + " = " + new DecimalFormat("#.###").format(v2.dist) + ", path: "
				+ getPath(v2.name));
		System.out.println();
	}
	public void BlackListShortPath(String BlackListFile,Graph g) throws IOException
	{
		File file=new File(BlackListFile);	//choose file
		@SuppressWarnings("unused")
		int num_of_queries;
		String line;		//read file by lines
		StringTokenizer st;		//Spliting the string

		file.createNewFile();
		BufferedReader br=new BufferedReader(new FileReader(file));		//create a buffer for reading file

		num_of_queries=StringToInt(br.readLine());
		//read the file text and fill in the array
		while((line = br.readLine()) != null)
		{
			if(line.contains("info"))
				break;
			st=new StringTokenizer(line);
			@SuppressWarnings("unused")
			int v1=StringToInt(st.nextToken()),v2=StringToInt(st.nextToken());	//Path points

			while(st.hasMoreTokens())
			{
				int BlackV=StringToInt(st.nextToken()), BlackV2;
				for (int i = 0; i < vertices[BlackV].edges.size(); i++) {
					vertices[BlackV].edges.get(i).weight=Double.POSITIVE_INFINITY;
					BlackV2=vertices[BlackV].edges.get(i).vert;
					vertices[BlackV2].edges.get(vertices[BlackV2].getEdgeIndex(BlackV)).weight=Double.POSITIVE_INFINITY;
				}

			}
			computePaths(v1);
			printPath(vertices[v1],vertices[v2]);
			for (int i = 0; i < vertices.length; i++) {
				vertices[i]=new Vertex(g.getVertexGraph()[i]);
			}
		}
		br.close();
	}
	
	private int StringToInt(String s) {
		return Integer.parseInt(s);
	}
}
