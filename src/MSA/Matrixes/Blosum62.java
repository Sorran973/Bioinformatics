package MSA.Matrixes;

import java.util.HashMap;


public class Blosum62 implements IMatrix {

    private int openGAP;
    private int extendGAP;
    private HashMap<Character, Integer> Blosum62Map = new HashMap<>();


    // источник: https://www.ncbi.nlm.nih.gov/Class/BLAST/BLOSUM62.txt
    private int[][] Blosum62Matrix = {
            /*         A    R    N    D    C    Q    E    G    H    I    L    K    M    F    P    S    T    W    Y    V    B    Z    X   _   */
            /* A */ {  4,  -1,  -2,  -2,   0,  -1,  -1,   0,  -2,  -1,  -1,  -1,  -1,  -2,  -1,   1,   0,  -3,  -2,   0,  -2,  -1,   0, openGAP},
            /* R */ { -1,   5,   0,  -2,  -3,   1,   0,  -2,   0,  -3,  -2,   2,  -1,  -3,  -2,  -1,  -1,  -3,  -2,  -3,  -1,   0 , -1, openGAP},
            /* N */ { -2,   0,   6,   1,  -3,   0,   0,   0,   1,  -3,  -3,   0,  -2,  -3,  -2,   1,   0,  -4,  -2,  -3,   3,   0,  -1, openGAP},
            /* D */ { -2,  -2,   1,   6,  -3,   0,   2,  -1,  -1,  -3,  -4,  -1,  -3,  -3,  -1,   0,  -1,  -4,  -3,  -3,   4,   1,  -1, openGAP},
            /* C */ {  0,  -3,  -3,  -3,   9,  -3,  -4,  -3,  -3,  -1,  -1,  -3,  -1,  -2,  -3,  -1,  -1,  -2,  -2,  -1,  -3,  -3,  -2, openGAP},
            /* Q */ { -1,   1,   0,   0,  -3,   5,   2,  -2,   0,  -3,  -2,   1,   0,  -3,  -1,   0,  -1,  -2,  -1,  -2,   0,   3,  -1, openGAP},
            /* E */ { -1,   0,   0,   2,  -4,   2,   5,  -2,   0,  -3,  -3,   1,  -2,  -3,  -1,   0,  -1,  -3,  -2,  -2,   1,   4,  -1, openGAP},
            /* G */ {  0,  -2,   0,  -1,  -3,  -2,  -2,   6,  -2,  -4,  -4,  -2,  -3,  -3,  -2,   0,  -2,  -2,  -3,  -3,  -1,  -2,  -1, openGAP},
            /* H */ { -2,   0,   1,  -1,  -3,   0,   0,  -2,   8,  -3,  -3,  -1,  -2,  -1,  -2,  -1,  -2,  -2,   2,  -3,   0,   0,  -1, openGAP},
            /* I */ { -1,  -3,  -3,  -3,  -1,  -3,  -3,  -4,  -3,   4,   2,  -3,   1,   0,  -3,  -2,  -1,  -3,  -1,   3,  -3,  -3,  -1, openGAP},
            /* L */ { -1,  -2,  -3,  -4,  -1,  -2,  -3,  -4,  -3,   2,   4,  -2,   2,   0,  -3,  -2,  -1,  -2,  -1,   1,  -4,  -3,  -1, openGAP},
            /* K */ { -1,   2,   0,  -1,  -3,   1,   1,  -2,  -1,  -3,  -2,   5,  -1,  -3,  -1,   0,  -1,  -3,  -2,  -2,   0,   1,  -1, openGAP},
            /* M */ { -1,  -1,  -2,  -3,  -1,   0,  -2,  -3,  -2,   1,   2,  -1,   5,   0,  -2,  -1,  -1,  -1,  -1,   1,  -3,  -1,  -1, openGAP},
            /* F */ { -2,  -3,  -3,  -3,  -2,  -3,  -3,  -3,  -1,   0,   0,  -3,   0,   6,  -4,  -2,  -2,   1,   3,  -1,  -3,  -3,  -1, openGAP},
            /* P */ { -1,  -2,  -2,  -1,  -3,  -1,  -1,  -2,  -2,  -3,  -3,  -1,  -2,  -4,   7,  -1,  -1,  -4,  -3,  -2,  -2,  -1,  -2, openGAP},
            /* S */ {  1,  -1,   1,   0,  -1,   0,   0,   0,  -1,  -2,  -2,   0,  -1,  -2,  -1,   4,   1,  -3,  -2,  -2,   0,   0,   0, openGAP},
            /* T */ {  0,  -1,   0,  -1,  -1,  -1,  -1,  -2,  -2,  -1,  -1,  -1,  -1,  -2,  -1,   1,   5,  -2,  -2,   0,  -1,  -1,   0, openGAP},
            /* W */ { -3,  -3,  -4,  -4,  -2,  -2,  -3,  -2,  -2,  -3,  -2,  -3,  -1,   1,  -4,  -3,  -2,  11,   2,  -3,  -4,  -3,  -2, openGAP},
            /* Y */ { -2,  -2,  -2,  -3,  -2,  -1,  -2,  -3,   2,  -1,  -1,  -2,  -1,   3,  -3,  -2,  -2,   2,   7,  -1,  -3,  -2,  -1, openGAP},
            /* V */ {  0,  -3,  -3,  -3,  -1,  -2,  -2,  -3,  -3,   3,   1,  -2,   1,  -1,  -2,  -2,   0,  -3,  -1,   4,  -3,  -2,  -1, openGAP},
            /* B */ { -2,  -1,   4,   4,  -3,   0,   1,  -1,   0,  -3,  -4,   0,  -3,  -3,  -2,   0,  -1,  -4,  -3,  -3,   4,   1,  -1, openGAP},
            /* Z */ { -1,   0,   0,   1,  -3,   4,   4,  -2,   0,  -3,  -3,   1,  -1,  -3,  -1,   0,  -1,  -3,  -2,  -2,   1,   4,  -1, openGAP},
            /* X */ {  0,  -1,  -1,  -1,  -2,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -2,   0,   0,  -2,  -1,  -1,  -1,  -1,  -1, openGAP},
            /* _ */ {openGAP, openGAP, openGAP, openGAP, openGAP, openGAP, openGAP, openGAP, openGAP, openGAP, openGAP, openGAP, openGAP, openGAP, openGAP, openGAP, openGAP, openGAP, openGAP, openGAP, openGAP, openGAP, openGAP, 1 }
    };

    public Blosum62(){
        fillMap();
    };

    public Blosum62(Integer openGAP, Integer extendGAP){

        fillMap();
        if (openGAP != null) {
            this.openGAP = openGAP;
            for (int i = 0; i < Blosum62Matrix.length; i++) {
                Blosum62Matrix[i][23] = Blosum62Matrix[23][i] = openGAP;
            }
        }

        if (extendGAP != null) {
            this.extendGAP = extendGAP;
        }
    }

    public void fillMap() {
        // Алфавит аминокислот для проверки последовательностей
        Character[] letters = {'A', 'R', 'N', 'D', 'C', 'Q', 'E', 'G', 'H', 'I', 'L', 'K', 'M', 'F', 'P', 'S', 'T', 'W', 'Y', 'V', 'B', 'Z', 'X'};
        for (int i = 0; i < 23; i++){
            Blosum62Map.put(letters[i], i);
        }
        Blosum62Map.put('_', 23);
    }

    @Override
    public int get(char x, char y) {
        // Проверка
        if (x != '_' && !Character.isLetter(x)) {
            throw new IllegalStateException("Character is not letter: " + x);
        } else if (y != '_' && !Character.isLetter(y)) {
            throw new IllegalStateException("Character is not letter: " + y);
        }

        return Blosum62Matrix[Blosum62Map.get(x)][Blosum62Map.get(y)];
    }

    @Override
    public int getOpenE() {
        return this.openGAP;
    }

    @Override
    public int getExtendE() {
        return this.extendGAP;
    }
}