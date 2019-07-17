package Torn.Program.Service;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class CRUD {
	public static void write(List<String> text, String fileLocation, String fileName) {
		try {
			File file = new File(fileLocation + fileName);
			FileWriter pw = new FileWriter(file);
			PrintWriter pr = new PrintWriter(pw);
			for (int i = 0; i < text.size(); i++) {
				pr.println(text.get(i));
			}
			pr.close();
			System.out.println("File: "+fileName+" has been written to "+fileLocation);

		} catch (Exception e) {
			System.out.println("Could not create file "+fileName);
		}
	}
	public static ArrayList<String> read(String fileLocation, String fileName) {
		ArrayList<String> fileContent = new ArrayList<String>();
		try (BufferedReader br = new BufferedReader(new FileReader(fileLocation+fileName))) {
			String currentLine;
			while ((currentLine = br.readLine()) != null) {
				fileContent.add(currentLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
			System.out.println("File: "+fileName+" has been read from "+fileLocation);
			return fileContent;

	}
}