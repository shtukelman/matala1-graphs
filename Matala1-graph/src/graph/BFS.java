package graph;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Breadth-first search
 * Complexity: O(|V|+|E|)
 */
public class BFS {
	public static final int white = 0 , gray = 1, black = 2;
	private int[] color, dist, pred;
	private ArrayList<Integer>[] graph;
	private ArrayBlockingQueue<Integer> queue;
	private int size;
	
	public BFS(ArrayList<Integer>[] graph) {
		this.graph = copy(graph);
		size = graph.length;
		init();
	}
	
	private void init() {
		color = new int[size];
		dist = new int[size];
		pred = new int[size];
		Arrays.fill(dist, -1);
		Arrays.fill(pred, -1);
	}
	
	public void AlgoBFS(int s) {
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
	
	private ArrayList<Integer>[] copy(ArrayList<Integer>[] g) {
		@SuppressWarnings("unchecked")
		ArrayList<Integer>[] copy = new ArrayList[g.length];
		for (int i = 0; i < g.length; i++) {
			copy[i] = new ArrayList<Integer>();
			for (int j = 0; j < g[i].size(); j++) {
				copy[i].add(g[i].get(j));
			}
		}
		return copy;
	}
	
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
	public int[] diameter(){
		int diam;
		int[] indexAndDiam = {-1,-1,-1};
		if (size < 2) return indexAndDiam;
		if (size == 2) {
			indexAndDiam[0] = 0;
			indexAndDiam[1] = 1;
			indexAndDiam[2] = 1;
			return indexAndDiam;
		}
 
		AlgoBFS(graph[0].get(0));
		int indMax1 = maxIndex();
		AlgoBFS(indMax1);
		diam = maxDist();
		int indMax2 = maxIndex();
		indexAndDiam[0] = indMax1;
		indexAndDiam[1] = indMax2;
		indexAndDiam[2] = diam;
		return indexAndDiam;
	}

	public int[] radius(){
		int rad=Integer.MAX_VALUE; 
		int[] indexAndRadius = {-1,-1,-1};
		if (size < 2) return indexAndRadius;
		if (size == 2) {
			indexAndRadius[0] = 0;
			indexAndRadius[1] = 1;
			indexAndRadius[2] = 1;
			return indexAndRadius;			
		}
		ArrayList<Integer> longestDist = new ArrayList<>();
		for (int i = 0; i < graph.length; i++) {
			AlgoBFS(i);
			longestDist.add(maxDist());			
		}
		for (int i = 0; i < longestDist.size(); i++) {
			if (longestDist.get(i) < rad) {
				rad = longestDist.get(i);
			}
		}
		int j=0;
		for (int i = 0; i < longestDist.size(); i++) {
			if (longestDist.get(i) == rad){
				indexAndRadius[j++] = i;
			}
		}
		indexAndRadius[2] = rad;
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
	public static void main(String[] args) {
		ArrayList<Integer>[] graph = new ArrayList[7];
		for (int i = 0; i < graph.length; i++) {
			graph[i] = new ArrayList<>();
		}
		
		graph[0].add(1);
		graph[1].add(0);graph[1].add(2);
		graph[2].add(1);graph[2].add(3);
		graph[3].add(2);graph[3].add(4);graph[3].add(6); 
		graph[4].add(3);graph[4].add(5); 
		graph[5].add(4);
		graph[6].add(3);  
		
		BFS Gb = new BFS(graph);
		
		System.out.println("dem = "+ Arrays.toString(Gb.diameter()));
		System.out.println("rad = "+ Arrays.toString(Gb.radius()));
	}
}