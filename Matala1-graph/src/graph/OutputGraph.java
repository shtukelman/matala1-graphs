package graph;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class OutputGraph {
	/**
	 * 
	 * @param Graph The graph we work with
	 * @param TestFile Test File name
	 * @param OutPutFile Test Output
	 */
	public OutputGraph(String Graph,String TestFile,String OutPutFile)
	{
		long startTime=System.currentTimeMillis();		//start time calc
		Graph myGraph = new Graph(Graph);		//createa graph
		Dijkstra dj=new Dijkstra(myGraph);		//run dijkstra algothm
		BFS bf=new BFS(myGraph);		//run bfs algorithm
		
		File outFile=new File(OutPutFile);		//output file
		try {
			outFile.createNewFile();
			
			FileWriter fw = new FileWriter(outFile.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			
			dj.BlackListShortPath(TestFile, myGraph,bw);		//run black list test
					
			bw.write("|V|="+myGraph.getVertex_number()+", |E|="+myGraph.getEdge_number()+", Triangle="+dj.isTriangleInequality()+
					", Radius: "+bf.Getradius()[1]+", Diameter: "+bf.Getdiameter()[2] +"");
			long endTime=System.currentTimeMillis()-startTime;		//end time in MS
			bw.write(", runetime: "+endTime+" ms");
			dj.printPath(19, 50);
			dj.printPath(29, 89);
			bw.close();
			fw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
