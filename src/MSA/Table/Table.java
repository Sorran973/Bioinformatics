package MSA.Table;

import MSA.Matrixes.IMatrix;
import MSA.Sequence.Sequence;

import java.io.PrintStream;


public class Table {

    private TableElement[][] mismatchTable;      // Таблица с диагональными переходами (две буквы)
    private TableElement[][] insertionTable;    // Таблица с горизонтальными гэпами    (буква и геп)
    private TableElement[][] deletionTable;    // Таблица с вертикальными гэпами       (геп и буква)
    private TableElement[][] resTable;        // Результирующая таблица
    private IMatrix iMatrix;

    private final int infinity;
    private final char GAP = '_';

    private Sequence seqA;
    private Sequence seqB;

    private String firstRes;
    private String secondRes;
    private int score;



    public Table(Sequence seqA, Sequence seqB, IMatrix iMatrix) {
        this.seqA = seqA;
        this.seqB = seqB;

        this.iMatrix = iMatrix;

        mismatchTable = new TableElement[seqB.size() + 1][seqA.size() + 1];
        insertionTable = new TableElement[seqB.size() + 1][seqA.size() + 1];
        deletionTable = new TableElement[seqB.size() + 1][seqA.size() + 1];
        resTable = new TableElement[seqB.size() + 1][seqA.size() + 1];

        infinity = 2 * iMatrix.getOpenE() + (seqB.size() + seqA.size()) * iMatrix.getExtendE() + 1;

        fillTables();
        printResults();
    }

    private void printResults() {
        String[] sequences = resTable[seqB.size()][seqA.size()].printAlignment();
        firstRes = sequences[0];
        secondRes = sequences[1];
        score = resTable[seqB.size()][seqA.size()].getScore();
    }

    private void fillTables() {

        // Инициализация и заполнение гепами первых строки и столбца таблиц
        initMismatchTable();
        initInsertionTable();
        initDeletionTable();

        // Заполнение оставшихся частей таблиц
        for (int i = 1; i < seqB.size() + 1; i++) {
            for (int j = 1; j < seqA.size() + 1; j++) {
                mismatchTable[i][j] = getMismatchElement(i, j);
                insertionTable[i][j] = getInsertionElement(i, j);
                deletionTable[i][j] = getDeletionElement(i, j);
            }
        }

        // Заполнение результирующей таблицы
        for (int i = 1; i < seqB.size() + 1; i++) {
            for (int j = 1; j < seqA.size() + 1; j++) {
                resTable[i][j] = getResElement(i, j);
            }
        }
    }

    private void initMismatchTable() {
        mismatchTable[0][0] = new TableElement(0, 0, 0);
        for (int i = 1; i < seqB.size() + 1; i++) {
            mismatchTable[i][0] = new TableElement(i, 0, infinity, GAP, seqB.get(i - 1), mismatchTable[i - 1][0]);
        }
        for (int j = 1; j < seqA.size() + 1; j++) {
            mismatchTable[0][j] = new TableElement(0, j, infinity, GAP, seqA.get(j - 1), mismatchTable[0][j - 1]);
        }
    }

    private void initInsertionTable() {
        insertionTable[0][0] = new TableElement(infinity, 0 ,0);
        for (int i = 1; i < seqB.size() + 1; i++) {
            insertionTable[i][0] = new TableElement(i, 0, iMatrix.getOpenE() + iMatrix.getExtendE() * (i - 1), GAP, seqB.get(i - 1), insertionTable[i - 1][0]);
        }
        for (int j = 1; j < seqA.size() + 1; j++) {
            insertionTable[0][j] = new TableElement(0, j, infinity, GAP, seqA.get(j - 1), insertionTable[0][j - 1]);
        }
    }

    private void initDeletionTable() {
        deletionTable[0][0] = new TableElement(infinity, 0, 0);
        for (int i = 1; i < seqB.size() + 1; i++) {
            deletionTable[i][0] = new TableElement(i, 0, infinity, GAP, seqB.get(i - 1), deletionTable[i - 1][0]);
        }
        for (int j = 1; j < seqA.size() + 1; j++) {
            deletionTable[0][j] = new TableElement(j, 0, iMatrix.getOpenE() + iMatrix.getExtendE() * (j - 1), GAP, seqA.get(j - 1), deletionTable[0][j - 1]);
        }
    }

    private TableElement getMismatchElement(int i, int j) {
        int score = iMatrix.get(seqB.get(i - 1), seqA.get(j - 1));

        TableElement mismatchElement  = mismatchTable[i - 1][j - 1].addScore(score);
        TableElement insertionElement = insertionTable[i - 1][j - 1].addScore(score);
        TableElement deletionElement  = deletionTable[i - 1][j - 1].addScore(score);

        TableElement max = TableElement.max(mismatchElement, TableElement.max(insertionElement, deletionElement));

        char elemOfSeqB = seqB.get(i - 1);
        char elemOfSeqA = seqA.get(j - 1);

        return new TableElement(i, j, max.getScore(), elemOfSeqA, elemOfSeqB, max);
    }

    private TableElement getInsertionElement(int i, int j) {
        TableElement mismatchElement = mismatchTable[i][j - 1].addScore(iMatrix.getOpenE());
        TableElement insertionElement = insertionTable[i][j - 1].addScore(iMatrix.getExtendE()); // Продолжение штрафа
        TableElement deletionElement = deletionTable[i][j - 1].addScore(iMatrix.getOpenE());

        TableElement max = TableElement.max(mismatchElement, TableElement.max(insertionElement, deletionElement));

        char elemOfSeqA = seqA.get(j - 1);

        return new TableElement(i, j, max.getScore(), elemOfSeqA, GAP, max);
    }

    private TableElement getDeletionElement(int i, int j) {
        TableElement mismatchElement  = mismatchTable[i - 1][j].addScore(iMatrix.getOpenE());
        TableElement insertionElement = insertionTable[i - 1][j].addScore(iMatrix.getOpenE());
        TableElement deletionElement  = deletionTable[i - 1][j].addScore(iMatrix.getExtendE()); // Продолжение штрафа

        TableElement max = TableElement.max(mismatchElement, TableElement.max(insertionElement, deletionElement));


        char elemOfSeqB = seqB.get(i - 1);

        return new TableElement(i, j, max.getScore(), GAP, elemOfSeqB, max);
    }

    private TableElement getResElement(int i, int j) {
        // Выбор максимального из трех таблиц в результирующую
        return TableElement.max(mismatchTable[i][j], TableElement.max(insertionTable[i][j], deletionTable[i][j]));
    }

    public int getScore() {
        return score;
    }

    public String getSecondRes() {
        return secondRes;
    }

    public String getFirstRes() {
        return firstRes;
    }
}
