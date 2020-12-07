import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class BagSort {
    private static ArrayList<String> readData(String filename) {
        ArrayList<String> fileData = new ArrayList<>();
        try {
            File dataFile = new File(filename);
            Scanner myReader = new Scanner(dataFile);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                fileData.add(data);
            }
            myReader.close();
        } catch (Exception e) {
            System.out.println("Oh no!");
        }
        return fileData;
    }

    private static ArrayList<String> getParentBags(String colour, ArrayList<String> bags) {
        ArrayList<String> parentBags = new ArrayList<>();
        Pattern p = Pattern.compile("(\\w+\\s\\w+).*" + colour + ".*");
        for (String bag : bags) {
            Matcher m = p.matcher(bag);
            if (m.matches()) {
                parentBags.add(m.group(1));
            }
        }
        return parentBags;
    }

    private static ArrayList<String> getAncestorBags(String colour, ArrayList<String> bags, ArrayList<String> ancestors) {
        ArrayList<String> parentBags = getParentBags(colour, bags);
        if (parentBags.size() > 0) {
            for (String bag : parentBags) {
                if (!ancestors.contains(bag)) {
                    ancestors.add(bag);
                }
                getAncestorBags(bag, bags, ancestors);
            }
        }
        return ancestors;
    }

    private static int countChildren(String bag) {
        int count = 0;
        for (char c : bag.toCharArray()) {
            if (Character.isDigit(c)) {
                count = count + Integer.parseInt(""+c);
            }
        }
        return count;
    }

    private static ArrayList<String> getChildBags(String colour, ArrayList<String> bags) {
        Pattern p = Pattern.compile(colour + " bags contain((\\s\\d\\s\\w+\\s\\w+\\s\\w+\\W)+)")
        String childString = "";
        for (String bag : bags) {
            Matcher m = p.matcher(bag);
            if (p.matches()) {

            }
        }
    }

    private static ArrayList<String>

    public static void main(String[] args) {
        ArrayList<String> bags = readData("bags.txt");
        ArrayList<String> goldenAncestors = getAncestorBags("shiny gold", bags, new ArrayList<String>());
        System.out.println(goldenAncestors.size());
        System.out.println(countChildren("muted lime bags contain 1 wavy lime bag, 1 vibrant green bag, 3 light yellow bags."));
    }
}