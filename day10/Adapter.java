import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Adapter {
    private static ArrayList<Integer> readData(String filename) {
        ArrayList<Integer> fileData = new ArrayList<>();
        try {
            File dataFile = new File(filename);
            Scanner myReader = new Scanner(dataFile);
            while (myReader.hasNextLine()) {
                int data = Integer.parseInt(myReader.nextLine());
                fileData.add(data);
            }
            myReader.close();
        } catch (Exception e) {}
        return fileData;
    }

    private static int part1(ArrayList<Integer> adapters) {
        Collections.sort(adapters);
        int ones = 0;
        int twos = 0;
        int  threes = 0;
        ones++;
        for (int i = 0; i < adapters.size() - 1; i++){
            switch (adapters.get(i+1) - adapters.get(i)) {
                case 1:
                    ones++;
                    break;
                case 2:
                    twos++;
                    break;
                case 3:
                    threes++;
                    break;
            }
        }
        threes++;
        System.out.println(adapters);
        System.out.println(ones);
        System.out.println(twos);
        System.out.println(threes);
        return ones * threes;
    }

    public static void main(String[] args) {
        ArrayList<Integer> adapters = readData("input.txt");
        System.out.println(part1(adapters));
    }
}