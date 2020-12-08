import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Instructions {
    private static ArrayList<String[]> readData(String filename) {
        ArrayList<String[]> fileData = new ArrayList<>();
        try {
            File dataFile = new File(filename);
            Scanner myReader = new Scanner(dataFile);
            while (myReader.hasNextLine()) {
                String[] data = myReader.nextLine().split(" ");
                fileData.add(data);
            }
            myReader.close();
        } catch (Exception e) {}
        return fileData;
    }

    private static int[] getAcc(ArrayList<String[]> instructions) {
        int j = 0;
        int acc = 0;
        ArrayList<Integer> js = new ArrayList<>();
        for (int i = 0; i < instructions.size(); i++) {
            String instr = instructions.get(j)[0];
            int num = Integer.parseInt(instructions.get(j)[1]);

            switch (instr) {
                case "nop":
                    j++;
                    break;
                case "acc":
                    j++;
                    acc += num;
                    break;
                case "jmp":
                    j += num;
                    break;
            }
            if (js.contains(j) || j == instructions.size()) break;
            js.add(j);
        }
        return new int[]{acc, j};
    }

    private static int getCorrectAcc(ArrayList<String[]> instructions) {
        for (int i = 0; i < instructions.size(); i++) {
            String instr = instructions.get(i)[0];
            String num = instructions.get(i)[1];
            String[] input = {instr, num};
            switch (instr) {
                case "nop":
                    input[0] = "jmp";
                    break;
                case "jmp":
                    input[0] = "nop";
                    break;
            }
            instructions.set(i, input);
            if (getAcc(instructions)[1] == instructions.size()) {
                return getAcc(instructions)[0];
            }
            input[0] = instr;
            instructions.set(i, input);
        }
        return 0;
    }

    public static void main(String[] args) {
        ArrayList<String[]> instructions = readData("input.txt");
        System.out.println(getAcc(instructions)[0]);
        System.out.println(getCorrectAcc(instructions));
    }
}