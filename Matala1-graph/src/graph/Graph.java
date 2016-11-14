package graph;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Graph {

	public static double infinity = Double.POSITIVE_INFINITY;
	private File file;
	private int vertex_number, edge_number;
	private Vertex[] vs;
	private ArrayList<Integer>[] regGraph;
    private String path;
	private Dijkstra myDijkstra;
	private BFS myBFS;

	public Graph(String path) throws IOException {
		this.path = path;
		readFileToGraph();
		
	}

	public void computePaths(int v){
		myDijkstra.computePaths(v);
	}
	public void printWeights() {
		myDijkstra.printWeights();
	}

	public String getPath(int v) {
		return myDijkstra.getPath(v);
	}

	public void printPaths() {
		myDijkstra.printPaths();
	}
	
	public int[] getDiameterWithVertexs(){
		return myBFS.diameter();
	}
	public int[] getRadiusWithVertex(){
		return myBFS.radius();
	}
	private void readFileToGraph() throws IOException {
		String line;
		StringTokenizer st;
		BufferedReader br;
		this.file = new File(path);
		this.file.createNewFile();
		br = new BufferedReader(new FileReader(file));
		vertex_number = Integer.parseInt(line = br.readLine()); // read num of
																// vertex
		edge_number = Integer.parseInt(line = br.readLine()); // read num of
																// edges
		vs = new Vertex[vertex_number]; // vertex array
		regGraph = new ArrayList[vertex_number];
		for (int i = 0; i < vertex_number; i++) {
			vs[i] = new Vertex(i, infinity);
			vs[i].edges = new ArrayList<>();
			regGraph[i] = new ArrayList<Integer>();

		}

		// read the file text and fill in the array
		while ((line = br.readLine()) != null) {
			st = new StringTokenizer(line);
			int v1 = StringToInt(st.nextToken()), v2 = StringToInt(st.nextToken());
			double weight = Double.parseDouble(st.nextToken());
			vs[v1].edges.add(new Edge(v2, weight));
			vs[v2].edges.add(new Edge(v1, weight));

			regGraph[v1].add(v2);
			regGraph[v2].add(v1);
		}
		br.close();

		myDijkstra = new Dijkstra(vs);
		myBFS = new BFS(regGraph);
	}

	private static int StringToInt(String s) {
		return Integer.parseInt(s);
	}
	
	public ArrayList<Integer>[] getArrayListGraph(){
		return this.regGraph;
	}
	public Vertex[] getVertexGraph(){
		return this.vs;
	}
}
