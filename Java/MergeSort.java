import java.io.File;
import java.io.FileWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class MergeSort {
  public static void main(String args[]) throws Exception {
    String input = args[0];
    String output = args[1];
    int iterations = Integer.parseInt(args[2]);
    List<Integer> list = new ArrayList<>();

    // Read from file
    File inFile = new File(input);
    Scanner scanner = new Scanner(inFile);
    while(scanner.hasNext()) {
      list.add(Integer.parseInt(scanner.nextLine().trim()));
    }
    scanner.close();

    // Sort and write to file
    File outFile = new File(output);
    outFile.createNewFile();
    FileWriter writer = new FileWriter(outFile);
    for(int i = 0; i < iterations; i++) {
      long start = System.nanoTime();
      List<Integer> copy = new ArrayList<>(list);
      sort(copy);
      long end = System.nanoTime();
      writer.write(Long.toString(end - start) + "\n");
    }
    writer.close();

  }

  private static void sort(List<Integer> list) {

  }
}
