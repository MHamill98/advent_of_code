import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Stream;

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

    public static Map<String, String> readToMap(String filename) {
        String delimiter = " bags contain ";
        Map<String, String> map = new HashMap<>();

        try(Stream<String> lines = Files.lines(Paths.get(filename))){
            lines.forEach(
                line -> map.putIfAbsent(line.split(delimiter)[0], line.split(delimiter)[1])
            );
        } catch (IOException e) {
            System.out.println("Oh no");
        }

        return map;    
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
        for (String bag : parentBags) {
            if (!ancestors.contains(bag)) {
                ancestors.add(bag);
            }
            getAncestorBags(bag, bags, ancestors);
        }
        return ancestors;
    }

    private static ArrayList<String> getChildBags(String colour, Map<String, String> bags) {
        String[] children = bags.get(colour).split(", ");
        Pattern p = Pattern.compile("\\d\\s(\\w+\\s\\w+)\\s\\w+\\W?");
        ArrayList<String> childList = new ArrayList<>();
        for (String bag : children) {
            Matcher m = p.matcher(bag);
            if (m.matches()) childList.add(m.group(1));
        }
        return childList;
    }

    private static int count(String bag) {
        for (char c : bag.toCharArray()) {
            if (Character.isDigit(c)) return Integer.parseInt(""+c);
        }
        return 0;
    }

    private static int getTotalCount(String colour, Map<String, String> bags) {
        ArrayList<String> childBags = getChildBags(colour, bags);
        int count = 0;
        for (int i = 0; i < childBags.size(); i++) {
            count += count(bags.get(colour).split(",")[i]) + count(bags.get(colour).split(",")[i]) * getTotalCount(childBags.get(i), bags);
        }
        return count;
    }

    public static void main(String[] args) {
        ArrayList<String> bags = readData("bags.txt");
        ArrayList<String> goldenAncestors = getAncestorBags("shiny gold", bags, new ArrayList<String>());
        System.out.println(goldenAncestors.size());

        Map<String, String> bagMap = readToMap("bags.txt");
        int totalCount = getTotalCount("shiny gold", bagMap);
        System.out.println(totalCount);
    }
}