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
        return ones * threes;
    }

    private static int paths(ArrayList<Integer> adapters) {
        int num = 0;
        switch (adapters.size()) {
            case 1:
                num = 1;
                break;
            case 2:
                num = 1;
                break;
            case 3:
                num = 2;
                break;
            case 4:
                num = 4;
                break;
            case 5:
                num = 7;
                break;
        }
        return num;
    }

    private static long part2(ArrayList<Integer> adapters) {
        ArrayList<ArrayList<Integer>> sets = new ArrayList<>();
        ArrayList<Integer> working = new ArrayList<>();
        for (int i = 0; i < adapters.size() - 1; i++) {
            working.add(adapters.get(i));

            if(adapters.get(i+1) - adapters.get(i) == 3) {
                sets.add(working);
                working = new ArrayList<Integer>();
            }
        }
        long paths = 1;
        for (ArrayList<Integer> set : sets) {
            System.out.println(set);
            System.out.println(paths(set));
            paths *= paths(set);
        }
        return paths;
    }

    public static void main(String[] args) {
        ArrayList<Integer> adapters = readData("input.txt");
        adapters.add(0);
        adapters.add(159);
        System.out.println(part1(adapters));
        System.out.println(part2(adapters));
    }
}