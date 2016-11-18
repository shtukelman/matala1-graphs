package graph;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

public class graphTest {

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
		
		Vertex [] v_expected= initGraph.InPut_3();
		Vertex [] v_actual= g.getVertexGraph();
		
		assertEquals(v_actual.length, v_expected.length);
		
		for (int i = 0; i < v_expected.length; i++) {
			for (int j = 0; j < v_expected[i].edges.size(); j++) {
				assertEquals(v_expected[i].edges.get(j).vert , v_actual[i].edges.get(j).vert);
			}
		}
		
		
	}

	
	
}
