package graph;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class OutputGraph {
	/**
	 * 
	 * @param Graph The graph we work with
	 * @param TestFile Test File name
	 * @param OutPutFile Output for test
	 */
	public OutputGraph(String Graph,String TestFile,String OutPutFile)
	{
		long startTime=System.currentTimeMillis();
		Graph myGraph = new Graph(Graph);
		Dijkstra dj=new Dijkstra(myGraph);
		BFS bf=new BFS(myGraph);
		File outFile=new File(OutPutFile);
		try {
			outFile.createNewFile();
			
			FileWriter fw = new FileWriter(outFile.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			
			dj.BlackListShortPath(TestFile, myGraph,bw);
			dj.printPath(0, 4, bw);
		long endTime=System.currentTimeMillis()-startTime;
			bw.write("|V|="+myGraph.getVertex_number()+", |E|="+myGraph.getEdge_number()+", Triangle="+dj.isTriangleInequality()+
					", Radius: "+bf.Getradius()[1]+", Diameter: "+bf.Getdiameter()[2]+", runetime: "+endTime+" ms");
			System.out.println(Arrays.toString(bf.Getradius()));
			System.out.println(Arrays.toString(bf.Getdiameter()));
			bw.close();
			fw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}


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
 		
	}
}
