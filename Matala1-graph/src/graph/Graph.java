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

	public Graph(String path) {
		this.path = path;
		try {
			readFileToGraph();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unchecked")
	public Graph(Graph OtherGraph) {
		this.file=OtherGraph.file;
		this.vertex_number=OtherGraph.vertex_number;
		this.edge_number=OtherGraph.edge_number;
		this.vs=new Vertex[OtherGraph.vs.length];
		this.regGraph=new ArrayList[OtherGraph.regGraph.length];
		for (int i = 0; i < vs.length; i++) {
			this.vs[i]= new Vertex(OtherGraph.vs[i]);
		}
		for (int i = 0; i < OtherGraph.regGraph.length; i++) {
			regGraph[i]=(ArrayList<Integer>) OtherGraph.regGraph[i].clone();
		}
		this.path=OtherGraph.path;
	}
	
	@SuppressWarnings("unchecked")
	private void readFileToGraph() throws IOException {
		String line;
		StringTokenizer st;
		BufferedReader br;
		this.file = new File(path);
		this.file.createNewFile();
		br = new BufferedReader(new FileReader(file));
		vertex_number = Integer.parseInt(line = br.readLine()); 	// read num of vertex
		edge_number = Integer.parseInt(line = br.readLine()); 	// read num of edges
		vs = new Vertex[vertex_number]; 	// vertex array
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
	}

	private int StringToInt(String s) {
		return Integer.parseInt(s);
	}

	public ArrayList<Integer>[] getArrayListGraph(){
		return regGraph;
	}
	public Vertex[] getVertexGraph(){
		return vs;
	}

}
