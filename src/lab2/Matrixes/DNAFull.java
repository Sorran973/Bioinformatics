package lab2.Matrixes;

import java.util.HashMap;


public class DNAFull implements IMatrix {

    private int openGAP;
    private int extendGAP;
    private HashMap<Character, Integer> DNAFullMap = new HashMap<>();

    // источник: http://rosalind.info/glossary/dnafull/
    private int[][] DNAFullMatrix = {
            /*         A    T    G    C    S    W    R    Y    K    M    B    V    H    D    N   _   */
            /* A */ {  5,  -4,  -4,  -4,  -4,   1,   1,  -4,  -4,   1,  -4,  -1,  -1,  -1,  -2, openGAP},
            /* T */ { -4,   5,  -4,  -4,  -4,   1,  -4,   1,   1,  -4,  -1,  -4,  -1,  -1,  -2, openGAP},
            /* G */ { -4,  -4,   5,  -4,   1,  -4,   1,  -4,   1,  -4,  -1,  -1,  -4,  -1,  -2, openGAP},
            /* C */ { -4,  -4,  -4,   5,   1,  -4,  -4,   1,  -4,   1,  -1,  -1,  -1,  -4,  -2, openGAP},
            /* S */ { -4,  -4,   1,   1,  -1,  -4,  -2,  -2,  -2,  -2,  -1,  -1,  -3,  -3,  -1, openGAP},
            /* W */ {  1,   1,  -4,  -4,  -4,  -1,  -2,  -2,  -2,  -2,  -3,  -3,  -1,  -1,  -1, openGAP},
            /* R */ {  1,  -4,   1,  -4,  -2,  -2,  -1,  -4,  -2,  -2,  -3,  -1,  -3,  -1,  -1, openGAP},
            /* Y */ { -4,   1,  -4,   1,  -2,  -2,  -4,  -1,  -2,  -2,  -1,  -3,  -1,  -3,  -1, openGAP},
            /* K */ { -4,   1,   1,  -4,  -2,  -2,  -2,  -2,  -1,  -4,  -1,  -3,  -3,  -1,  -1, openGAP},
            /* M */ {  1,  -4,  -4,   1,  -2,  -2,  -2,  -2,  -4,  -1,  -3,  -1,  -1,  -3,  -1, openGAP},
            /* B */ { -4,  -1,  -1,  -1,  -1,  -3,  -3,  -1,  -1,  -3,  -1,  -2,  -2,  -2,  -1, openGAP},
            /* V */ { -1,  -4,  -1,  -1,  -1,  -3,  -1,  -3,  -3,  -1,  -2,  -1,  -2,  -2,  -1, openGAP},
            /* H */ { -1,  -1,  -4,  -1,  -3,  -1,  -3,  -1,  -3,  -1,  -2,  -2,  -1,  -2,  -1, openGAP},
            /* D */ { -1,  -1,  -1,  -4,  -3,  -1,  -1,  -3,  -1,  -3,  -2,  -2,  -2,  -1,  -1, openGAP},
            /* N */ { -2,  -2,  -2,  -2,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1, openGAP},
            /* _ */ {openGAP, openGAP, openGAP, openGAP, openGAP, openGAP, openGAP, openGAP, openGAP, openGAP, openGAP, openGAP, openGAP, openGAP, openGAP, openGAP}
    };
    public DNAFull(){
        fillMap();
    };

    public DNAFull(Integer openGAP, Integer extendGAP){

        fillMap();
        if (openGAP != null) {
            this.openGAP = openGAP;
            for (int i = 0; i < DNAFullMatrix.length; i++) {
                DNAFullMatrix[15][i] = DNAFullMatrix[i][15] = openGAP;
            }
        }

        if (extendGAP != null) {
            this.extendGAP = extendGAP;
        }

    }

    public void fillMap() {
        // Алфавит нуклеотидов для проверки последовательностей
        Character[] letters = {'A', 'T', 'G', 'C', 'S', 'W', 'R', 'Y', 'K', 'M', 'B', 'V', 'H', 'D', 'N'};
        for (int i = 0; i < 15; i++){
            DNAFullMap.put(letters[i], i);
        }
        DNAFullMap.put('_', 15);
    }

    @Override
    public int get(char x, char y) {
        // Проверка
        if (x != '_' && !Character.isLetter(x)) {
            throw new IllegalStateException("Character is not letter: " + x);
        } else if (y != '_' && !Character.isLetter(y)) {
            throw new IllegalStateException("Character is not letter: " + y);
        }

        return DNAFullMatrix[DNAFullMap.get(x)][DNAFullMap.get(y)];
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
