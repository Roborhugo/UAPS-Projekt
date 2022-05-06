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
      List<Integer> copy = new ArrayList<>(list);
      long start = System.nanoTime();
      sort(copy);
      long end = System.nanoTime();
      writer.write(Long.toString(end - start) + "\n");
    }
    writer.close();

  }

  private static void sort(List<Integer> list) {
    sort(list, 0, list.size() - 1);
  }

  private static void sort(List<Integer> list, int l, int r) {
    if (l >= r)
	  {
		  return;
	  }
    int m = l + (r - l) / 2;
    sort(list, l, m);
    sort(list, m + 1, r);
    merge(list, l, m, r);
  }

  private static void merge(List<Integer> list, int l, int m, int r) {
    int leftSize = m - l + 1;
	  int rightSize = r - m;
    
    Integer[] left = new Integer[leftSize];
    Integer[] right = new Integer[rightSize];

    for (int i = 0; i < leftSize; i++) {
      left[i] = list.get(i + l);
    }
    for (int i = 0; i < rightSize; i++) {
      right[i] = list.get(i + m + 1);
    }

    int lInd = 0;
    int rInd = 0;
    int dInd = l;

	  while (lInd < leftSize && rInd < rightSize)
	  {
		  if (left[lInd] <= right[rInd])
		  {
			  list.add(dInd, left[lInd]);
  			lInd++;
  		}
  		else
  		{
  			list.add(dInd, right[rInd]);
  			rInd++;
  		}
  		dInd++;
  	}
  	while (lInd < leftSize) {
  		list.add(dInd, left[lInd]);
  		dInd++;
  		lInd++;
  	}
  }
}
