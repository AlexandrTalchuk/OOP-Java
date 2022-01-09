
import java.io.*;

public class file {
    static void InFile(String st) throws IOException {
        File file = new File("laba4_2.txt");
        FileWriter writer = new FileWriter(file, true);
        writer.append(st);
        writer.flush();
        writer.close();
    }

    static void readFile() throws IOException {
        File file = new File("laba4_2.txt");
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);

        String line = reader.readLine();
        while (line != null){
            System.out.println(line);
            line = reader.readLine();
        }
        fileReader.close();
    }
}
