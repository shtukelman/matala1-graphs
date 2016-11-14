package graph;



import java.io.IOException;

//https://github.com/PeterStampfli/readWriteText.git

public class Main {

	public static void main(String[] args) throws IOException {


		Graph myGraph = new Graph("G0.txt");
		myGraph.computePaths(0);
		myGraph.printPaths();
		myGraph.printWeights(); 
		int v = 5;
		
		System.out.println("path to "+v+": "+ myGraph.getPath(v));
		int[]diam = myGraph.getDiameterWithVertexs();
		System.out.println("diameter: "+diam[2] +", vertex1: "+diam[1]+", vertex2: "+diam[0]);
		
		int[] radius = myGraph.getRadiusWithVertex();
 		System.out.println("radius: "+radius[1] +", vertex: "+radius[0]);
 		
 		System.out.println("is Triangle inequality: "+myGraph.isTriangleInequality());
	}
}
