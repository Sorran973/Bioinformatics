package lab1.Table;


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

    static void check(String first, String second, PrintStream printTo) {

        if (first.length() > 100 || second.length() > 100) {
            ArrayList<String> lines1 = split(first, 100);
            ArrayList<String> lines2 = split(second, 100);

            for (int i = 0; i < lines1.size(); i++) {
                printTo.println(lines1.get(i));
                printTo.println(lines2.get(i));
                printTo.println();
                printTo.println();
            }
        }
        else {
            printTo.println(first);
            printTo.println(second);
            printTo.println();
            printTo.println();
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
