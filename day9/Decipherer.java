import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Decipherer {
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

    public static boolean isValid(int next, List<Integer> previous) {
        for (int i = 0; i < previous.size(); i++) {
            for (int j = 0; j < previous.size(); j++) {
                if (previous.get(i) + previous.get(j) == next && i != j) {
                    return true;
                }
            }
        }
        return false;
    }

    public static int part1(ArrayList<Integer> nums) {
        List<Integer> previous = nums.subList(0, 25);
        for (int i = 25; i < nums.size(); i++) {
            int next = nums.get(i);
            if (isValid(next, previous)) {
                previous.remove(0);
                previous.add(next);
            } else {
                return next;
            }
        }
        return -1;
    }

    public static ArrayList<Integer> part2(int num, ArrayList<Integer> nums) {
        for (int i = 0; i < nums.size(); i++) {
            int sum = 0;
            int j = i;
            ArrayList<Integer> sumNums = new ArrayList<>();
            while (sum < num) {
                sumNums.add(nums.get(j));
                sum += nums.get(j);
                j++;
            }
            if (sum == num) {
                return sumNums;
            }
        }
        return nums;
    }

    public static int max(ArrayList<Integer> list) {
        int max = list.get(0);
        for (int num : list) {
            if (num > max) max = num;
        }
        return max;
    }

    public static int min(ArrayList<Integer> list) {
        int min = list.get(0);
        for (int num : list) {
            if (num < min) min = num;
        }
        return min;
    }

    public static void main(String[] args) {
        ArrayList<Integer> nums = readData("input.txt");
        int num = part1(nums);
        System.out.println(num);
        ArrayList<Integer> part2 = part2(num, nums);
        System.out.println(max(part2) + min(part2));
        //System.out.println(part2.get(0) + part2.get(part2.size()-1));
    }
}