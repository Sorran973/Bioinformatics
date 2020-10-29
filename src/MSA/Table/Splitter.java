package MSA.Table;


import java.io.PrintStream;
import java.util.ArrayList;

public class Splitter {

    // Перенос последовательности или выравнивания на новую строку, если их длина превышает 100 символов
//    static ArrayList<StringBuilder> check(String first, String second) {
//
//        StringBuilder firstRes = new StringBuilder();
//        StringBuilder secondRes = new StringBuilder();
//
//        if (first.length() > 100 || second.length() > 100) {
//            firstRes.append("Seq1:");
//            ArrayList<String> lines = split(first, 100);
//            for (String line : lines) {
//                firstRes.append(line);
//            }
//
//            secondRes.append("Seq2:");
//            lines = split(second, 100);
//            for (String line : lines) {
//                secondRes.append(line);
//            }
//        }
//        else {
//            firstRes.append("Seq1: ").append(first);
//            secondRes.append("Seq2: ").append(second);
//        }
//
//        ArrayList<StringBuilder> res = new ArrayList<>();
//        res.add(firstRes);
//        res.add(secondRes);
//
//        return res;
//    }

    public static void check(ArrayList<String> array, PrintStream printTo) {

        ArrayList<ArrayList<String>> arrayLines = new ArrayList<>();
        for (String str: array) {
            if (str.length() > 100) {
                ArrayList<String> lines = split(str, 100);
                arrayLines.add(lines);
            } else {
                printTo.println(str);
            }
        }

        if (array.get(0).length() > 100) {
            for (int i = 0; i < arrayLines.get(0).size(); i++) {
                for (ArrayList arr : arrayLines) {
                    printTo.println(arr.get(i));
                }
                printTo.println();
            }
        }

    }

    private static ArrayList<String> split(String text, int size) {

        ArrayList<String> parts = new ArrayList<>();

        for (int i = 0; i < text.length(); i += size) {
            String str = text.substring(i, Math.min(text.length(), i + size));
            parts.add(str);

        }
        return parts;
    }
}
