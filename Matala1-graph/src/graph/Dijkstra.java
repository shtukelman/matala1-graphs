package graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
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
		this.graph = new Graph(graph);
		vertices = this.graph.getVertexGraph();
	}


	/**
	 * Activate dijkstra algorithm
	 * @param source vertex start
	 */
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
					if (distU <= v.dist && v.dist != infinity) {
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

	/** Is TriangleInequality
	 * @return boolean
	 */
	public boolean isTriangleInequality() {
		try{
			for (int i = 0; i < vertices.length; i++) {
				computePaths(i);
			}
		}
		catch(Exception e)
		{
			return false;
		}
		return TriangleInequality;

	}

	/**
	 * Print all weights in graph
	 */
	public void printWeights() {
		System.out.print("weights: ");
		for (Vertex v : vertices) {
			System.out.print(new DecimalFormat("#.###").format(v.dist) + ", ");
		}
		System.out.println();
	}
	public double printVertexWeight(int v)
	{
		return vertices[v].dist;
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

	/**
	 * Print specific path 
	 * @param v1 First Vertex
	 * @param v2 Second Vertex
	 */
	public void printPath(int v1, int v2,BufferedWriter bw) {
		computePaths(v1);
		try {
			bw.write(v1 + " " + v2 + " "+ new DecimalFormat("#.###").format(vertices[v2].dist) + ", path: "+ getPath(v2)+"\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(v1 + " " + v2 + " "+ new DecimalFormat("#.###").format(vertices[v2].dist) + ", path: "+ getPath(v2)+"\n");
	}

	public void BlackListShortPath(String BlackListFile, Graph g,BufferedWriter bw) {
		File file = new File(BlackListFile); // choose file
		@SuppressWarnings("unused")
		int num_of_queries;
		String line; // read file by lines
		StringTokenizer st; // Spliting the string
		try {
			file.createNewFile();
			BufferedReader br;

			br = new BufferedReader(new FileReader(file));

			// create a buffer for reading file

			num_of_queries = StringToInt(br.readLine());
			// read the file text and fill in the array
			while ((line = br.readLine()) != null) {
				if (line.contains("info"))
					break;
				st = new StringTokenizer(line);

				int v1 = StringToInt(st.nextToken()), v2 = StringToInt(st.nextToken()); // Path
				int numberOfBlacks = StringToInt(st.nextToken());
				bw.write(v1+" "+v2+" "+numberOfBlacks);
				System.out.print(v1+" "+v2+" "+numberOfBlacks);
				for (int k = 0; k < numberOfBlacks; k++) {
					int BlackV = StringToInt(st.nextToken());
					bw.write(" "+BlackV);
					System.out.print(" "+BlackV);
					UpdateBlackPoint(BlackV); 	//Black the point
				}

				computePaths(v1);
				bw.write(" "+vertices[v2].dist+"\n");
				System.out.println(" "+vertices[v2].dist+"\n");
				resetGraph(g);	//reset to original graph
			}
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void UpdateBlackPoint(int BlackV)
	{
		int BlackV2;
		for (int i = 0; i < vertices[BlackV].edges.size(); i++) {
			vertices[BlackV].edges.get(i).weight = Double.POSITIVE_INFINITY;
			BlackV2 = vertices[BlackV].edges.get(i).vert;
			vertices[BlackV2].edges.get(vertices[BlackV2].getEdgeIndex(BlackV)).weight = Double.POSITIVE_INFINITY;							
		}
	}
	private void resetGraph(Graph g)
	{
		for (int i = 0; i < vertices.length; i++) {
			vertices[i] = new Vertex(g.getVertexGraph()[i]);
		}
	}
	private int StringToInt(String s) {
		return Integer.parseInt(s);
	}
}
