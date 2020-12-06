import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class BoardingPasses {
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

    private static int getRow(String seat) {
        int lower = 0;
        int upper = 127;
        for (char dir : seat.toCharArray()) {
            int regionLength = upper - lower + 1;
            if (dir == 'F') {
                upper = upper - regionLength/2;
            } else {
                lower = lower + regionLength/2;
            }
        }
        return upper;
    }

    private static int getSeat(String seat) {
        int lower = 0;
        int upper = 7;
        for (char dir : seat.toCharArray()) {
            int regionLength = upper - lower + 1;
            if (dir == 'L') {
                upper = upper - regionLength/2;
            } else {
                lower = lower + regionLength/2;
            }
        }
        return upper;
    }

    private static int getId(String pass) {
        int row = getRow(pass.substring(0,7));
        int seat = getSeat(pass.substring(7));
        return (row * 8 + seat);
    }

    private static ArrayList<Integer> idList(ArrayList<String> passes) {
        ArrayList<Integer> idList = new ArrayList<>();
        for (String pass : passes) {
            idList.add(getId(pass));           
        }
        Collections.sort(idList);
        return idList;
    }

    private static int getMySeat(ArrayList<Integer> seats) {
        for (int i = 0; i < seats.size(); i++) {
            if (seats.get(i) + 1 != seats.get(i+1)) {
                return seats.get(i) + 1;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        ArrayList<String> passes = readData("boarding_passes.txt");
        int maxId = 0;
        for (String pass : passes) {
            int id = getId(pass);
            if (id > maxId) {
                maxId = id;
            }
        }
        System.out.println(maxId);
        System.out.println(getMySeat(idList(passes)));
    }
}