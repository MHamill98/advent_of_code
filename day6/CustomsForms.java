import java.io.InputStream;
import java.util.Scanner;
import java.util.ArrayList;

public class CustomsForms {
    private static ArrayList<String> readData(String filename) {
        ArrayList<String> answers = new ArrayList<>();
        try {
            InputStream is = CustomsForms.class.getClassLoader().getResourceAsStream(filename);
            Scanner scan = new Scanner(is);
            scan.useDelimiter("\r\n\r\n");            
            while (scan.hasNext()) {
                String line = scan.next();            
                answers.add(line);
            }            
            scan.close();
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return answers;
    }

    private static int answerCount(String answers) {
        ArrayList<Character> uniques = new ArrayList<>();
        for (int i = 0; i < answers.length(); i++) {
            char c = answers.charAt(i);
            if (!uniques.contains(c) && Character.isLetter(c)) {
                uniques.add(c);
            }
        }
        return uniques.size();
    }

    private static boolean isCharInAll(char c, String[] list) {
        for (String str: list) {
            if (!str.contains(""+c)) return false;
        }
        return true;
    }

    private static int sharedAnswerCount(String answers) {
        String[] splitAns = answers.split("\r\n");
        int count = 0;
        for (char c: splitAns[0].toCharArray()) {
            if (isCharInAll(c, splitAns)) count++;
        }
        return count;
    }

    public static void main(String[] args) {
        ArrayList<String> answerList = readData("answers.txt");
        int sum1 = 0;
        int sum2 = 0;
        for (String answer:answerList) {
            sum1 = sum1 + answerCount(answer);
            sum2 = sum2 + sharedAnswerCount(answer);
        }
        System.out.println(sum1);
        System.out.println(sum2);
    }
}