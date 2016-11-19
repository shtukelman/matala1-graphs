package graph;



import java.io.IOException;

//https://github.com/PeterStampfli/readWriteText.git

public class Main {

	public static void main(String[] args) throws IOException {
//		Graph myGraph = new Graph("G0.txt");
//		Dijkstra dj=new Dijkstra(myGraph);
//		BFS bf=new BFS(myGraph);
// 		dj.BlackListShortPath("test1.txt", myGraph);
//
//		dj.computePaths(4);
//		dj.printPaths();
//		dj.printWeights(); 
//		int v = 5;
//		
//		System.out.println("path to "+v+": "+ dj.getPath(v));
//		int[]diam = bf.Getdiameter();
//		System.out.println("diameter: "+diam[2] +", vertex1: "+diam[1]+", vertex2: "+diam[0]);
//		
//		int[] radius = bf.Getradius();
// 		System.out.println("radius: "+radius[1] +", vertex: "+radius[0]);
// 		
// 		System.out.println("is Triangle inequality: "+dj.isTriangleInequality());
// 		
		OutputGraph Graph=new OutputGraph("G3.txt", "G3test.txt", "G0outPut.txt");
	}
}
