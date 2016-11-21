package graph;


import java.io.File;
import java.io.PrintWriter;
import java.util.Vector;

public class Generator {
    private File output;
    private PrintWriter stream;

    private int getLines(int x) {
        int lines = 0;
        for (int i = 0; i < x; i++) {
            lines += i;
        }
        return lines;
    }

    private void initGraph(int fileNum) {
        this.output = new File("exampleFiles\\G" + fileNum + ".txt");
        try {
            this.output.createNewFile();
            this.stream = new PrintWriter(output);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void generateGraph(int fileNum, int numOfVertex) {
        initGraph(fileNum);
        stream.println(numOfVertex);
        stream.println(getLines(numOfVertex));
        for (int i = 0; i < numOfVertex; i++) {
            for (int j = i + 1; j < numOfVertex; j++) {
                if (i == numOfVertex - 1 && j == numOfVertex - 1) {
                    stream.print(i + " " + j + " " + ((Math.random() * 60) + 30));
                } else {
                    stream.println(i + " " + j + " " + ((Math.random() * 60) + 30));
                }
            }
        }
        stream.close();
    }

    private void initTest(int fileNum) {
        this.output = new File("exampleFiles\\test" + fileNum + ".txt");
        try {
            this.output.createNewFile();
            this.stream = new PrintWriter(output);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void generateTest(int fileNum, int numOfQueries, int numOfNodes) {
        initTest(fileNum);
        int v, u, currentNods;
        stream.println(numOfQueries);
        Vector<Integer> bucket;
        for (int i = 0; i < numOfQueries; i++) {
            bucket = new Vector();
            currentNods = numOfNodes;
            for (int k = 0; k < numOfQueries; k++) {
                bucket.add(k);
            }
            v = bucket.remove(((int) (Math.random() * currentNods--)));
            u = bucket.remove(((int) (Math.random() * currentNods--)));
            int blackListSize = (int) ((Math.random() * currentNods));
            int blackList[] = new int[blackListSize];
            for (int j = 0; j < blackListSize; j++) {
                blackList[j] = bucket.remove(((int) (Math.random() * currentNods--)));
            }
            stream.println(v + " " + u + " " + blackListSize + " " + arrayToString(blackList));
        }
        stream.print("info");
        stream.close();
    }

    public static String arrayToString(int arr[]) {
        String string = "";
        for (int black : arr) {
            string += black + " ";
        }
        return string;
    }

    public static void main(String args[]) {
        // number of file ( G<fileNum>.txt \ test<fileNum>.txt)
        int fileNum = 7;
        // number of nodes in graph
        int nodes = 15;
        // number of queries in test file
        int queries = 150;
        Generator g = new Generator();
//        g.generateTest(fileNum, queries, nodes);
        g.generateGraph(fileNum, nodes);
    }
}
