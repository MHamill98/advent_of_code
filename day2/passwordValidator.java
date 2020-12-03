import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class passwordValidator {
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

    private static boolean isValidPart1(String password) {
        Pattern p = Pattern.compile("(\\d+)-(\\d+)\\s(\\w):\\s(.*)");
        Matcher m = p.matcher(password);
        if (m.matches()) {
            int lowerBound = Integer.parseInt(m.group(1));
            int upperBound = Integer.parseInt(m.group(2));
            char letter = m.group(3).charAt(0);
            long count = m.group(4).chars().filter(ch -> ch == letter).count();
            if (lowerBound <= count && count <= upperBound) {
                return true;
            }
        }
        return false;
    }

    private static boolean isValidPart2(String pwd) {
        Pattern p = Pattern.compile("(\\d+)-(\\d+)\\s(\\w):\\s(.*)");
        Matcher m = p.matcher(pwd);
        if (m.matches()) {
            int index1 = Integer.parseInt(m.group(1)) - 1;
            int index2 = Integer.parseInt(m.group(2)) - 1;
            char letter = m.group(3).charAt(0);
            String password = m.group(4);
            if (password.charAt(index1) == letter || password.charAt(index2) == letter) {
                if (password.charAt(index1) == letter && password.charAt(index2) == letter) {
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    private static int countMatches(ArrayList<String> passwords, int part) {
        int count = 0;
        if (part == 1) {
            for (String password : passwords) {
                if (isValidPart1(password)) {
                    count++;
                }
            }
        } else {
            for (String password : passwords) {
                if (isValidPart2(password)) {
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        ArrayList<String> passwords = readData("passwords.txt");
        System.out.println(countMatches(passwords, 1));
        System.out.println(countMatches(passwords, 2));
    }
}