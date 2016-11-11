package graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

//https://github.com/PeterStampfli/readWriteText.git

public class Main {

	public static void main(String[] args) {

		String inPath = "C://Users//Shtukel//Desktop//לימודים//אריאל//שנה 3//סימסטר א//מבנה תוכנה//G0.txt";
		String outPath = "C://Users//Shtukel//Desktop//לימודים//אריאל//שנה 3//סימסטר א//מבנה תוכנה//new.txt";
		String ans = readTextFile(inPath);
		System.out.println(ans);
		writeTextFile(outPath, ans);

	}

	public static String readTextFile(String inPath) {

		Path path = Paths.get(inPath);
		String line = null;
		String ans = "";
		try {
			BufferedReader reader = Files.newBufferedReader(path);
			while ((line = reader.readLine()) != null) {
				// do something with the line
				// System.out.println(line);
				ans += line + "\n";

			}
			reader.close();

		} catch (IOException ex) {
			ex.printStackTrace(System.out);
		}
		return ans;

	}

	public static void writeTextFile(String outPath, String ans) {

		Path path = Paths.get(outPath);
		try {
			BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.CREATE);
			writer.write(ans);
			writer.close();

		} catch (IOException ex) {
			ex.printStackTrace(System.out);
		}
	}

}
