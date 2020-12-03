import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class sum2020 {
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
        } catch (Exception e) {
            System.out.println("Oh no!");
        }
        return fileData;
    }

    private static void round1(ArrayList<Integer> expenses) {
        System.out.println("########## First Round ##########");
        for (int i : expenses) {
            for (int j : expenses) {
                if(i + j == 2020) {
                    System.out.println(i + " * " + j + " = " + i * j);
                    return;
                }
            }
        }
    }

    private static void round2(ArrayList<Integer> expenses) {
        System.out.println("########## Second Round ##########");
        for (int i : expenses) {
            for (int j : expenses) {
                for (int k : expenses) {
                    if(i + j + k == 2020) {
                        System.out.println(i + " * " + j + " * " + k + " = " + i * j * k);
                        return;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        ArrayList<Integer> expenseReport = readData("expense_report.txt");
        round1(expenseReport);
        round2(expenseReport);     
    }
}