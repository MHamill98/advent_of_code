import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class tobogganTrees {
    private static ArrayList<String> readData(String filename) {
        ArrayList<String> fileData = new ArrayList<>();
        try {
            File dataFile = new File(filename);
            Scanner myReader = new Scanner(dataFile);
            while (myReader.hasNextLine()) {
                fileData.add(myReader.nextLine());
            }
            myReader.close();
        } catch (Exception e) {
            System.out.println("Oh no!");
        }
        return fileData;
    }

    private static long countTrees(ArrayList<String> trees, int xtick, int ytick) {
        int x = 0;
        int y = 0;
        long count = 0;
        for (String line : trees) {
            if (line.charAt(x) == '#') {
                count++;
            }
            x = (x + xtick) % line.length();
            y++;
        }
        return count;
    }

    private static long countTreesYtick(ArrayList<String> trees, int ytick) {
        int x = 0;
        int y = 0;
        long count = 0;
        int linelength = trees.get(0).length();
        while (y < trees.size()) {
            if (trees.get(y).charAt(x) == '#') {
                count ++;
            }
            x = (x + 1) % linelength;
            y = y + 2;
        }
        return count;
    }

    public static void main(String[] args) {
        ArrayList<String> trees = readData("trees.txt");
        System.out.println(countTrees(trees, 3, 1));
        long answer2 = countTrees(trees, 1, 1) * countTrees(trees, 3, 1) * countTrees(trees, 5, 1) * countTrees(trees, 7, 1) * countTreesYtick(trees, 2);
        System.out.println(answer2);
    }
}