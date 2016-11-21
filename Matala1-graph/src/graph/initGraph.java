package graph;

import java.util.ArrayList;

public class initGraph {

	
	public static ArrayList<Integer>[] InPut_1() {
		int size = 8;
		ArrayList <Integer>list[] = new ArrayList[size];
		for (int i=0; i<size; i++) {
		list[i] = new ArrayList<Integer>();
		}
		list[0].add(1); list[0].add(4);
		list[1].add(0); list[1].add(5);
		list[2].add(3); list[2].add(5); list[2].add(6);
		list[3].add(2); list[3].add(6); list[3].add(7);
		list[4].add(0);
		list[5].add(1); list[5].add(2); list[5].add(6);
		list[6].add(5); list[6].add(2); list[6].add(3); list[6].add(7);
		list[7].add(3); list[7].add(6);
		return list;
		}
	
	/**
	 * (0)--------(1) 		(2)--------(3)
		|			| 		/|	        /|
		| 			|	   / |	       / |
		|			| 	  /  |        /  |
		| 			| 	 / 	 | 		 /   |
		| 			| 	/ 	 | 		/    |
		| 			|  / 	 | 	   /  	 |
		| 			| / 	 | 	  / 	 |
		| 			|/ 		 |   / 		 |
		| 			|/ 		 |  / 		 |
	   (4) 	   	   (5)-------(6)--------(7)
	 */
	
	
	public static ArrayList<Integer>[] InPut_2() {
		int size = 6;
		ArrayList <Integer>list[] = new ArrayList[size];
		for (int i=0; i<size; i++) {
			list[i] = new ArrayList<Integer>();
		}
		list[0].add(3); list[0].add(4); list[0].add(5);
		list[1].add(2); list[1].add(4);
		list[2].add(1); list[2].add(3); list[2].add(4);
		list[3].add(0); list[3].add(2); list[3].add(5);
		list[4].add(0); list[4].add(1); list[4].add(2); list[4].add(5);
		list[5].add(0); list[5].add(3); list[5].add(4);
		return list;
		}

	
	public static Vertex[] InPut_3() {
		int size = 6;
		Vertex[]list = new Vertex[size];
		 
		for (int i=0; i<size; i++) {
			list[i] = new Vertex(i,Double.MAX_VALUE);
			list[i].edges = new ArrayList<>();
		}

		list[0].edges.add(new Edge(3, 4.1));
		list[3].edges.add(new Edge(0, 4.1));
		list[0].edges.add(new Edge(4, 1.1));
		list[4].edges.add(new Edge(0, 1.1));
		list[0].edges.add(new Edge(5, 3));
		list[5].edges.add(new Edge(0, 3));
		list[1].edges.add(new Edge(2, 3));
		list[2].edges.add(new Edge(1, 3));
		list[1].edges.add(new Edge(4, 4.2));
		list[4].edges.add(new Edge(1, 4.2));
		list[2].edges.add(new Edge(3, 5.16));
		list[3].edges.add(new Edge(2, 5.16));
		list[2].edges.add(new Edge(4, 2.2));
		list[4].edges.add(new Edge(2, 2.2));
		list[3].edges.add(new Edge(5, 0.3));
		list[5].edges.add(new Edge(3, 0.3));
		list[4].edges.add(new Edge(5, 2.2));
		list[5].edges.add(new Edge(4, 2.2));
		

		return list;
		}

	
}
