import java.io.InputStream;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class passportValidator {
    private static ArrayList<String[]> readData(String filename) {
        ArrayList<String[]> passports = new ArrayList<>();
        try {
            InputStream is = passportValidator.class.getClassLoader().getResourceAsStream(filename);
            Scanner scan = new Scanner(is);
            scan.useDelimiter("\r\n\r\n");            
            while (scan.hasNext()) {
                String[] line = scan.next().trim().split("\\s");                
                passports.add(line);
            }           
            scan.close();
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return passports;
    }

    private static boolean isValidPart1(String[] passport) {
        String[] required = {"byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"};
        int validFields = 0;
        for (String req : required) {
            for (String field : passport) {
                if (field.contains(req)) validFields++;
            }
        }
        if (validFields == 7) return true;
        return false;
    }

    private static boolean isValidPart2(String[] passport) {
        String[] requiredRegex = {
                                "byr:(19[2-9][0-9]|200[0-2])",
                                "iyr:(201[0-9]|2020)",
                                "eyr:(202[0-9]|2030)",
                                "hgt:(1[5-8][0-9]cm|19[0-3]cm|59in|6[0-9]in|7[0-6]in)",
                                "hcl:#[0-9a-f]{6}",
                                "ecl:(amb|blu|brn|gry|grn|hzl|oth)",
                                "pid:[0-9]{9}"
                                };
        int validFields = 0;                       
        for (String reg : requiredRegex) {
            Pattern p = Pattern.compile(reg);
            for (String field : passport) {
                Matcher m = p.matcher(field);
                if (m.matches()) validFields ++;
            }
        }
        if (validFields == requiredRegex.length) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        ArrayList<String[]> passports = readData("passports.txt");
        int validCount1 = 0;
        int validCount2 = 0;
        for (String[] passport : passports) {
            if (isValidPart1(passport)) validCount1++;
            if (isValidPart2(passport)) validCount2++;
        }
        System.out.println("Part 1: " + validCount1);
        System.out.println("Part 2: " + validCount2);
    }
}