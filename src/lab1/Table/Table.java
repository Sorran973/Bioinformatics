package lab1.Table;

import lab1.Matrixes.*;
import lab1.Sequence.*;

import java.io.PrintStream;


public class Table {

    private char GAP = '_';
    private TableElement[][] table;
    private IMatrix matrix;
    private Sequence seqA; // первая строка в таблице
    private Sequence seqB; // первый столбец в таблице

    public Table(Sequence sequenceA, Sequence sequenceB, IMatrix iMatrix) {
        this.seqA = sequenceA;
        this.seqB = sequenceB;

        this.matrix = iMatrix;
        table = new TableElement[sequenceB.size() + 1][sequenceA.size() + 1];

        fillTable();
    }

    private void fillTable() {

        // Заполнение гепами первой строки и первого столбца
        fillGaps();

        // Заполнение оставшейся части таблицы (алгоритм Нидлмана-Вунша)
        NeedlemanWunsch();

    }

    private void fillGaps() {

        table[0][0] = new TableElement(0, 0, 0);

        for (int i = 1; i < seqB.size() + 1; i++) {
            table[i][0] = new TableElement(i, 0, matrix.getGAP() * i, GAP, seqB.get(i - 1), table[i - 1][0]);
        }

        for (int i = 1; i < seqA.size() + 1; i++) {
            table[0][i] = new TableElement(0, i, matrix.getGAP() * i, seqA.get(i - 1), GAP, table[0][i - 1]);
        }
    }

    private void NeedlemanWunsch() {
        for (int i = 1; i < seqB.size() + 1; i++) {
            for (int j = 1; j < seqA.size() + 1; j++) {

                int diagFine = matrix.get(seqB.get(i - 1), seqA.get(j - 1));

                int upFine = matrix.get(seqB.get(i - 1), GAP);

                int leftFine = matrix.get(GAP, seqA.get(j - 1));

                TableElement diag = table[i - 1][j - 1].addScore(diagFine);
                TableElement up = table[i - 1][j].addScore(upFine);
                TableElement left = table[i][j - 1].addScore(leftFine);

                TableElement max = TableElement.max(diag, TableElement.max(up, left));

                // В случае несоответствия i элементы таблицы выровнены
                // (текущий элемент таблицы запоминает элемент последовательности),
                // иначе выровнено с разрывом
                // (текущий элемент таблицы запоминает геп)
                char elemOfSeqB;
                if (max.getI() == i)
                    elemOfSeqB = GAP;
                else
                    elemOfSeqB = seqB.get(i - 1);


                // В случае несоответствия i элементы таблицы выровнены
                // (текущий элемент таблицы запоминает элемент последовательности),
                // иначе выровнено с разрывом
                // (текущий элемент таблицы запоминает геп)
                char elemOfSeqA;
                if (max.getJ() == j)
                    elemOfSeqA = GAP;
                else
                    elemOfSeqA = seqA.get(j - 1);


                table[i][j] = new TableElement(i, j, max.getScore(), elemOfSeqA, elemOfSeqB, max);
            }
        }
    }


    public void showResults(PrintStream printTo) {
        printTo.println("1) " + seqA.getName() + ":");
        printTo.println("2) " + seqB.getName() + ":");
        printTo.println();
        table[seqB.size()][seqA.size()].printAlignment(printTo);
        printTo.println("Score: " + table[seqB.size()][seqA.size()].getScore());
    }

}
