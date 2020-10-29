package MSA.Table;

import java.io.PrintStream;
import java.util.ArrayList;


public class TableElement implements Comparable<TableElement> {

    private char elemOfSeqA; // Буква первой последовательности, которую запомнили в Нидлмане-Вунше
    private char elemOfSeqB; // Буква второй последовательности, которую запомнили в Нидлмане-Вунше

    private int i;           // Позиция текущего элемента в строке
    private int j;           // Позиция текущего элемента в столбце
    private int score;

    private TableElement parent;

    public TableElement(int i, int j, int score) {
        this.i = i;
        this.j = j;
        this.score = score;
    }

    public TableElement(int i, int j, int score, char elementUp, char elementLeft, TableElement parent) {
        this.i = i;
        this.j = j;
        this.score = score;

        this.elemOfSeqA = elementUp;
        this.elemOfSeqB = elementLeft;
        this.parent = parent;
    }

    public TableElement addScore(int score){
        return new TableElement(this.i, this.j, this.score + score, this.elemOfSeqA, this.elemOfSeqB, this.parent);
    }

    public static TableElement max(TableElement a, TableElement b) {
        if (a.compareTo(b) >= 0)
            return a;
        else
            return b;
    }

    @Override
    public int compareTo(TableElement o) {
        return Integer.compare(this.score, o.score);
    }

    public int getScore() {
        return score;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }


    // Вывод выравнивания
    public String[] printAlignment() {

        String first = getUpPath().reverse().toString();
        StringBuffer s = getLeftPath();
        String second = s.deleteCharAt(s.length() - 1).reverse().toString();

        return new String[]{first, second};
//        Splitter.check(first, second, printTo);
    }

    // Cоставление выравнивания с последнего элемента таблицы для верхней последовательности
    private StringBuffer getUpPath() {
        StringBuffer res = new StringBuffer();
        if (elemOfSeqA != 0)
            res.append(elemOfSeqA);

        if (parent != null) {
            res.append(parent.getUpPath());
        }

        return res;
    }

    // Cоставление выравнивания с последнего элемента таблицы для левой последовательности
    private StringBuffer getLeftPath() {
        StringBuffer res = new StringBuffer();
        res.append(elemOfSeqB);

        if (parent != null) {
            res.append(parent.getLeftPath());
        }

        return res;
    }
}
