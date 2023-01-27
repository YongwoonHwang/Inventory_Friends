import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;

public class Test4 {
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(new File("test.txt"));
        String path = "test.txt";

        try (FileWriter fw = new FileWriter(path, true)){
            for (int i = 0; i < 3; i++) {
                fw.write( '\n'+ Integer.toString(i));
            }

            System.out.println("Successfully wrote to the file.");
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        while (scanner.hasNextLine()) {
            String str = scanner.nextLine();
            System.out.println(str);
        }

    }
}