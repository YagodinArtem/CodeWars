
import java.util.ArrayList;
import java.util.List;

public class ErrorCorrection {
    public static String encode(String text) {
        char[] temp = text.toCharArray();
        List<Integer> ASCii = new ArrayList<>();
        List<String> binary = new ArrayList<>();
        List<String> tripleBinary = new ArrayList<>();
        StringBuilder result = new StringBuilder();

        //Convert to ASCii code ->
        for (char c : temp) {
            ASCii.add((int) c);
        }
        //Convert to binary code ->
        for (Integer i : ASCii) {
            binary.add(String.format("%8s", Integer.toBinaryString(i)).replace(' ', '0'));
        }

        //print1 (optional) ->
        for (String s : binary) {
            System.out.println(s);
        }
        System.out.println("\nBinaries ^ ___________________________________________________________________________\n");

        //triple the binaries ->
        for (String s : binary) {
            String[] temporary = s.split("");
            for (int i = 0; i < temporary.length; i++) {
                 temporary[i] = temporary[i]+temporary[i]+temporary[i];
            }

            StringBuilder builder = new StringBuilder();
            for (String sb : temporary) {
                builder.append(sb);
            }
            tripleBinary.add(builder.toString());
        }

        //print2 (optional)
        for (String str : tripleBinary) {
            System.out.println(str);
        }
        System.out.println("\nTripled binaries ^ ___________________________________________________________________________\n");

        //concatenate all str to result ->
        for (String encodedString : tripleBinary) {
            result.append(encodedString);
        }

        return result.toString();
    }

    public static String decode(String bits) {



        /*
        Steps:
Split the input into groups of three characters;
Check if an error occurred: replace each group with the character that occurs most often, e.g. 010 --> 0, 110 --> 1, etc;
Take each group of 8 characters and convert that binary number;
Convert the binary values to decimal (ASCII);
Convert the ASCII values to characters and concatenate the result
For example:

input: "100111111000111001000010000111111000000111001111000111110110111000010111"
--> 100, 111, 111, 000, 111, 001, ...  // triples
-->  0,   1,   1,   0,   1,   0,  ...  // corrected bits
--> 01101000, 01100101, 01111001       // bytes
--> 104, 101, 121                      // ASCII values
--> "hey"
         */

        ArrayList<String> splitBy3 = new ArrayList<>();
        ArrayList<String> corrected = new ArrayList<>();
        char[] temp = bits.toCharArray();
        StringBuilder sb = new StringBuilder();

        //split input string to triples
        for (char c : temp) {
            sb.append(c);
            if (sb.length() > 2) {
                splitBy3.add(sb.toString());
                sb.setLength(0);
            }
        }

        //correct and check tripled bytes
        StringBuilder sb2 = new StringBuilder();
        for (String s : splitBy3) {
            sb2.append(byteCorrector(s));
            if (sb2.length() > 7) {
                corrected.add(sb2.toString());
                sb2.setLength(0);
            }
        }

        StringBuilder result = new StringBuilder();

        //convert binaries -> ASCii -> chars
        for (String str : corrected) {
            int asci = Integer.parseInt(str, 2);
            char character = (char)asci;
            result.append(character);
        }
        return result.toString();
    }

    public static String byteCorrector(String triple) {
        int zeroCounter = 0;
        int oneCounter = 0;
        char[] temp = triple.toCharArray();
        for (char c : temp) {
            if (c == 48) {
                zeroCounter++;
            }
            else
                oneCounter++;
        }
        if (zeroCounter > oneCounter) {
            return "0";
        }
        else
            return "1";
    }


    public static void main(String[] args) {
        String s = encode("The Sensei told me that i can do this kata");

        System.out.println(s);
        System.out.println("\nThis is encoded ^ ___________________________________________________________________________\n");
        System.out.println(decode(s));
        System.out.println("\nThis is decoded ^ ___________________________________________________________________________\n");

        //test1();
        //test2();


    }

    public static void test1() {
        System.out.println("111000000000000111000000111000111111000000000000111000000000111111111000000000000111111111000000111".equals(
                ErrorCorrection.encode("ХУй"))+"\n");
        System.out.println("000111111000111000000000000111111000000111000111000111111111111000000111".equals(
                ErrorCorrection.encode("hey"))+"\n");

    }
    public static void test2() {
        System.out.println("000111000111000111000000000111111000111000000000000111111000000111000111000000111000000000000000000111000111000000111111000111111000000111000111000111111000111111111000000111111111000000111111000111111000000111000111000111111000111000000111000000111000000000000000000111111111000111000000000111111000111111111111000111111000111111000000000111111000000111000000000000111000000000000000000111111000111111000111000111111000000111000111000000111000000000000000000111111111000111000000000111111000111000000000000111111000000000000111000111111111000111000000000000111000000000000000000111111000111000000111000000111000000000000000000111111000000000111111000111111000000000000111000111111000111111111000000000111000000000000000000111111000000111000000000111111000111111111111000000111000000000000000000111111111000111000000000111111000111000000000000111111000111000000111000111111111000000111111000000111000000000000000000111111000111000111111000111111000000000000111000111111111000111000000000111111000000000000111".equals(
                ErrorCorrection.encode("The Sensei told me that i can do this kata"))+"\n");
    }
}