package graph;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Breadth-first search
 * Complexity: O(|V|+|E|)
 */
public class BFS {
	private static final int white = 0 , gray = 1, black = 2;
	private int[] color, dist, pred;
	private ArrayList<Integer>[] graph;
	private ArrayBlockingQueue<Integer> queue;
	private int size;
	
	/**
	 * BFS Algorithm run
	 * @param graph	graph
	 */
	public BFS(Graph graph) {
		this.graph = graph.getArrayListGraph().clone();
		size = graph.getArrayListGraph().length;
		init();
	}
	
	private void init() {
		color = new int[size];
		dist = new int[size];
		pred = new int[size];
		Arrays.fill(dist, -1);
		Arrays.fill(pred, -1);
	}
	
	/**
	 * Run BFS Algo
	 * @param s source vertex
	 */
	private void AlgoBFS(int s) {
		init();
		queue = new ArrayBlockingQueue<Integer>(size);
		queue.add(s);
		color[s] = gray;
		dist[s] = 0;
		while(!queue.isEmpty()) {
			int u = queue.poll();
			for(int v : graph[u]) {
				if(color[v] == white) {
					color[v] = gray;
					dist[v] = dist[u] + 1;
					pred[v] = u;
					queue.add(v);
				}
			}
			color[u] = black;
		}
	}
	

	/**
	 * print best path source to destination
	 * @param src
	 * @param dest
	 * @return best path
	 */
	public String bestPath(int src, int dest) {
		if(src >= size || dest >= size || src < 0 || dest < 0) return "";
		AlgoBFS(src);
		String ans = "";
		while(dest != src) {
			ans = "->" + dest + ans;
			dest = pred[dest];
		}
		ans = src + ans;
		return ans;
	}
	
	/** the diameter of the tree is the largest of all shortest-path
	 * distances in the tree. 
	 * */
	public int[] Getdiameter(){
		int diam=Integer.MIN_VALUE;
		int[] indexAndDiam = {-1,-1,-1};
		if (size < 2) return indexAndDiam;
		if (size == 2) {
			indexAndDiam[0] = 0;
			indexAndDiam[1] = 1;
			indexAndDiam[2] = 1;
			return indexAndDiam;
		}
 
		ArrayList<Integer> longestDist = new ArrayList<>();
		ArrayList<Integer> longestDistIndex = new ArrayList<>();
		ArrayList<Integer> longestDistIndex2 = new ArrayList<>();
		for (int i = 0; i < graph.length; i++) {
			AlgoBFS(i);
			longestDist.add(maxDist());
			longestDistIndex.add(i);
			longestDistIndex2.add(maxIndex());
			
		}
		for (int i = 0; i < longestDist.size(); i++) {
			if (longestDist.get(i) > diam) {
				diam = longestDist.get(i);
				indexAndDiam[0] = longestDistIndex.get(i);
				indexAndDiam[1] = longestDistIndex2.get(i);
				indexAndDiam[2]=diam;
			}
		}
		return indexAndDiam;
	}

	public int[] Getradius(){
		int rad=Integer.MAX_VALUE; 
		int[] indexAndRadius = {-1,-1};
		if (size < 2) return indexAndRadius;
		if (size == 2) {
			indexAndRadius[0] = 0;
			indexAndRadius[1] = 1;
			return indexAndRadius;			
		}
		ArrayList<Integer> longestDist = new ArrayList<>();
		ArrayList<Integer> longestDistIndex = new ArrayList<>();
		for (int i = 0; i < graph.length; i++) {
			AlgoBFS(i);
			longestDist.add(maxDist());
			longestDistIndex.add(i);
		}
		for (int i = 0; i < longestDist.size(); i++) {
			if (longestDist.get(i) < rad) {
				rad = longestDist.get(i);
				indexAndRadius[0] = longestDistIndex.get(i);
				indexAndRadius[1]=rad;
			}
		}
		return indexAndRadius;
	}
	

	/**  the maxIndex function returns the index of the smallest
	 * distance from source vertex to the other vertices in the 
	 * the shortest path tree
	 */
	private int maxIndex(){
		int index = 0;
		for (int i=0; i<dist.length; i++){
			if (dist[index] < dist[i]) index = i;
		}
	 
		return index;
	}
	private int maxDist(){
		int distance = Integer.MIN_VALUE;
		for (int i=0; i<dist.length; i++){
			if (distance < dist[i]) distance = dist[i];
		}
	 
		return distance;
	}	
 
}