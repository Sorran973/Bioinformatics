package MSA;

import java.util.ArrayList;

public class Alignment {

    private int score;
    private String firstSeq;
    private String secondSeq;
    private int i;
    private int j;


    public Alignment(int i, String firstSeq, int j, String secondSeq, int score) {

        this.i = i;
        this.firstSeq = firstSeq;

        this.j = j;
        this.secondSeq = secondSeq;

        this.score = score;
    }


    public int getScore() {
        return score;
    }

    public String getFirstSeq() {
        return firstSeq;
    }

    public String getSecondSeq() {
        return secondSeq;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }
}
