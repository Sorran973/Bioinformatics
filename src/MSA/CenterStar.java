package MSA;

import MSA.Table.Splitter;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.stream.IntStream;

public class CenterStar {

    private ArrayList<Alignment> arrayOfAlignment;
    private ArrayList<String> msaResult;
    private int maxScore = Integer.MIN_VALUE;
    private int maxRow = 0;
    private int numOfSeq;
    private Alignment[][] alignmentTable; // Таблица попарных выравниваний


    public CenterStar(int numOfSeq, ArrayList<Alignment> arrayOfAlignment) {

        this.arrayOfAlignment = arrayOfAlignment;
        this.numOfSeq = numOfSeq;
        this.alignmentTable = new Alignment[numOfSeq][numOfSeq];

        msa();
    }

    private void msa() {

        // Заполнение таблицы выравниваний
        for (Alignment alignment : arrayOfAlignment) {

            int i = alignment.getI();
            int j = alignment.getJ();

            alignmentTable[i][j] = alignment;
            alignmentTable[j][i] = new Alignment(alignment.getJ(), alignment.getSecondSeq(),
                    alignment.getI(), alignment.getFirstSeq(),
                    alignment.getScore());
        }

        // Поиск максимальной строки (где больше суммарный скор)
        int score = 0;
        for (int i = 0; i < alignmentTable.length; i++) {
            for (int j = 0; j < alignmentTable[i].length; j++) {
                if (alignmentTable[i][j] != null)
                    score += alignmentTable[i][j].getScore();
            }
            if (score > maxScore) {
                maxScore = score;
                maxRow = i;
            }
        }

        for (int i = 0; i < alignmentTable.length; i++) {
            if (i == maxRow)
                continue;
            if (msaResult == null) {
                msaResult = new ArrayList<>();
                msaResult.add(alignmentTable[maxRow][i].getFirstSeq());
                msaResult.add(alignmentTable[maxRow][i].getSecondSeq());
                continue;
            }

            ArrayList<String> n = new ArrayList<>();
            n.add(alignmentTable[maxRow][i].getFirstSeq());
            n.add(alignmentTable[maxRow][i].getSecondSeq());
            // Запоминаем где должны быть пропуски
            ArrayList[] align = alignSimilar(msaResult.get(0), n.get(0));
            ArrayList ch_index1 = align[0];
            ArrayList ch_index2 = align[1];

            // Расставляем пропуски
            adjust(msaResult, ch_index1);
            adjust(n, ch_index2);
            msaResult.add(n.get(1));
        }
    }

    public void printResults(PrintStream printTo) {
        printAlignment(printTo);
    }

    private void printAlignment(PrintStream printTo) {
        Splitter.check(msaResult, printTo);
    }

    private ArrayList[] alignSimilar(String s1, String s2) {
        ArrayList<Integer> change1 = new ArrayList<>();
        ArrayList<Integer> change2 = new ArrayList<>();

        int i = 0;

        while (!s1.equals(s2)) {
            if (i > s1.length() - 1) {
                s1 += s2.substring(i, s2.length());
                IntStream.range(i, i + s2.substring(i, s2.length()).length()).forEachOrdered(change1::add);
                continue;
            }
            if (i > s2.length() - 1) {
                s2 += s1.substring(i, s1.length());
                IntStream.range(i, i + s1.substring(i, s1.length()).length()).forEachOrdered(change2::add);
                continue;
            }
            if (s1.charAt(i) != s2.charAt(i)) {
                if (s1.charAt(i) == '_') {
                    s2 = s2.substring(0, i) + "_" + s2.substring(i, s2.length());
                    change2.add(i);
                } else {
                    s1 = s1.substring(0, i) + "_" + s1.substring(i, s1.length());
                    change1.add(i);
                }
            }

            i++;
        }

        return new ArrayList[]{change1, change2};
    }


    private void adjust(ArrayList<String> stringArrayList, ArrayList<Integer> indices) {
        for (int i = 0; i < stringArrayList.size(); i++) {
            for (Integer index : indices) {
                String str = stringArrayList.get(i);
                stringArrayList.set(i, str.substring(0, index) + "_" + str.substring(index, str.length()));
            }
        }
    }
}

