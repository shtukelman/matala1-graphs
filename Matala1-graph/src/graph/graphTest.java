package graph;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

public class graphTest {

	// build Graph from file Test
	@Test
	public void readFromFileTest() throws IOException {

		Graph g = new Graph("G0.txt");
		ArrayList<Integer>[] actual = g.getArrayListGraph();
		ArrayList<Integer>[] expected = initGraph.InPut_2();

		assertEquals(actual.length, expected.length);

		for (int i = 0; i < expected.length; i++) {
			for (int j = 0; j < expected[i].size(); j++) {
				assertEquals(expected[i].get(j), actual[i].get(j));
			}
			System.out.println();
		}
	}

	@Test
	public void readFromFileTest1() throws IOException {
		Graph g = new Graph("G0.txt");
		Vertex[] v_expected = initGraph.InPut_3();
		Vertex[] v_actual = g.getVertexGraph();

		assertEquals(v_actual.length, v_expected.length);

		for (int i = 0; i < v_expected.length; i++) {
			for (int j = 0; j < v_expected[i].edges.size(); j++) {
				assertEquals(v_expected[i].edges.get(j).vert,
						v_actual[i].edges.get(j).vert);
			}
		}
	}

	
	// Radius Tests
	@Test
	public void radTest1() throws IOException {
		Graph g = new Graph("G3.txt");
		BFS bfs = new BFS(g);
		int actual = bfs.Getradius()[1];
		int expected = 2;
		assertEquals(expected,expected );
	}
	
	@Test
	public void radTest2() throws IOException {
		Graph g = new Graph("G4.txt");
		BFS bfs = new BFS(g);
		int actual = bfs.Getradius()[1];
		int expected = 2;
		assertEquals(expected,expected );
	}
	
	@Test
	public void radTest3() throws IOException {
		Graph g = new Graph("G5.txt");
		BFS bfs = new BFS(g);
		int actual = bfs.Getradius()[1];
		int expected = 2;
		assertEquals(expected,expected );
	}

	@Test
	public void radTest4() throws IOException {
		Graph g = new Graph("G5.txt");
		BFS bfs = new BFS(g);
		int actual = bfs.Getradius()[1];
		int expected = 2;
		assertEquals(expected,expected );
	}
	
	@Test
	public void radTest5() throws IOException {
		Graph g = new Graph("G6.txt");
		BFS bfs = new BFS(g);
		int actual = bfs.Getradius()[1];
		int expected = 3;
		assertEquals(expected,expected );
	}

	
	//Diameter Tests
	@Test
	public void diamTest1() throws IOException {
		Graph g = new Graph("G3.txt");
		BFS bfs = new BFS(g);
		int actual = bfs.Getdiameter()[2];
		int expected = 3;
		assertEquals(expected,expected );
	}
	
	@Test
	public void diamTest2() throws IOException {
		Graph g = new Graph("G4.txt");
		BFS bfs = new BFS(g);
		int actual = bfs.Getdiameter()[2];
		int expected = 3;
		assertEquals(expected,expected );
	}
	
	@Test
	public void diamTest3() throws IOException {
		Graph g = new Graph("G5.txt");
		BFS bfs = new BFS(g);
		int actual = bfs.Getdiameter()[2];
		int expected = 3;
		assertEquals(expected,expected );
	}
	
	@Test
	public void diamTest4() throws IOException {
		Graph g = new Graph("G6.txt");
		BFS bfs = new BFS(g);
		int actual = bfs.Getdiameter()[2];
		int expected = 5;
		assertEquals(expected,expected );
	}

	//path Tests
	@Test
	public void pathTest1() throws IOException {
	
		// 4-> 0 | 5 |  4-6-5
		Graph g = new Graph("G3.txt");
		Dijkstra d = new Dijkstra(g);
		d.computePaths(4); //start point
		String actual = d.getPath(5); //end point
		String expected = "4->6->5";
		assertTrue(actual.equals(expected));
		
	}
	
	@Test
	public void pathTest2() throws IOException {
		
		// 3-> 6 | 5 |  3->4->6
		Graph g = new Graph("G3.txt");
		Dijkstra d = new Dijkstra(g);
		d.computePaths(3); //start point
		String actual = d.getPath(6); //end point
		String expected = "3->4->6";
		assertTrue(actual.equals(expected));
		
	}
		
	@Test
	public void pathTest3() throws IOException {
		
		// 3-> 6 | 9 |  1->0->5->6->4->3
		Graph g = new Graph("G3.txt");
		Dijkstra d = new Dijkstra(g);
		d.computePaths(1); //start point
		String actual = d.getPath(3); //end point
		String expected = "1->0->5->6->4->3";
		assertTrue(actual.equals(expected));
		
	}
	
	@Test
	public void pathTest4() throws IOException {
		
		// 1-> 8 | 5 |  1->2->3->8
		Graph g = new Graph("G4.txt");
		Dijkstra d = new Dijkstra(g);
		d.computePaths(1); //start point
		String actual = d.getPath(8); //end point
		String expected = "1->2->3->8";
		assertTrue(actual.equals(expected));
		
	}
	
	@Test
	public void pathTest5() throws IOException {
		
		// 1-> 5 | 7 |  1->0->7->6->5
		Graph g = new Graph("G4.txt");
		Dijkstra d = new Dijkstra(g);
		d.computePaths(1); //start point
		String actual = d.getPath(5); //end point
		String expected = "1->0->7->6->5";
		assertTrue(actual.equals(expected));
		
	}
	
	@Test
	public void pathTest6() throws IOException {
		
		// 0-> 6 | 22 |  0->3->5->6
		Graph g = new Graph("G5.txt");
		Dijkstra d = new Dijkstra(g);
		d.computePaths(0); //start point
		String actual = d.getPath(6); //end point
		String expected = "0->3->5->6";
		assertTrue(actual.equals(expected));
		
	}
	
	@Test
	public void pathTest7() throws IOException {
		
		// 2->3 | 17 |  2->1->3
		Graph g = new Graph("G5.txt");
		Dijkstra d = new Dijkstra(g);
		d.computePaths(2); //start point
		String actual = d.getPath(3); //end point
		String expected = "2->1->3";
		assertTrue(actual.equals(expected));
		
	}


	
	//pathWeightTest
	@Test
	public void pathWeightTest1() throws IOException {
		
		// 4-> 5 | 5 
		Graph g = new Graph("G3.txt");
		Dijkstra d = new Dijkstra(g);
		d.computePaths(4); //start point
		int actual = (int) d.getVertices()[5].dist; //end point
		int expected = 5;
		assertEquals(actual,expected);
		
	}
	
	@Test
	public void pathWeightTest2() throws IOException {
		
		// 3-> 6 | 5 
		Graph g = new Graph("G3.txt");
		Dijkstra d = new Dijkstra(g);
		d.computePaths(3); //start point
		int actual = (int) d.getVertices()[6].dist; //end point
		int expected = 5;
		assertEquals(actual,expected);
		
	}
	
	@Test
	public void pathWeightTest3() throws IOException {
		
		// 1-> 3 | 5 
		Graph g = new Graph("G3.txt");
		Dijkstra d = new Dijkstra(g);
		d.computePaths(1); //start point
		int actual = (int) d.getVertices()[3].dist; //end point
		int expected = 9;
		assertEquals(actual,expected);
		
	}
	
	@Test
	public void pathWeightTest4() throws IOException {
		
		// 1-> 8 | 5 
		Graph g = new Graph("G4.txt");
		Dijkstra d = new Dijkstra(g);
		d.computePaths(1); //start point
		int actual = (int) d.getVertices()[8].dist; //end point
		int expected = 5;
		assertEquals(actual,expected);
		
	}
	
	@Test
	public void pathWeightTest5() throws IOException {
		
		// 1-> 5 | 5 
		Graph g = new Graph("G4.txt");
		Dijkstra d = new Dijkstra(g);
		d.computePaths(1); //start point
		int actual = (int) d.getVertices()[5].dist; //end point
		int expected = 7;
		assertEquals(actual,expected);
		
	}
	
	@Test
	public void pathWeightTest6() throws IOException {
		
		// 0-> 6 | 22 
		Graph g = new Graph("G5.txt");
		Dijkstra d = new Dijkstra(g);
		d.computePaths(0); //start point
		int actual = (int) d.getVertices()[6].dist; //end point
		int expected = 22;
		assertEquals(actual,expected);
		
	}
	
	@Test
	public void pathWeightTest7() throws IOException {
		
		// 2->3 | 17 
		Graph g = new Graph("G5.txt");
		Dijkstra d = new Dijkstra(g);
		d.computePaths(2); //start point
		int actual = (int) d.getVertices()[3].dist; //end point
		int expected = 17;
		assertEquals(actual,expected);
		
	}


}
