package lab1.Matrixes;

import java.util.HashMap;


public class DNAFull implements IMatrix {

    private int GAP; // Дефолтный геп
    private HashMap<Character, Integer> DNAFullMap = new HashMap<>();

    // источник: http://rosalind.info/glossary/dnafull/
    private int[][] DNAFullMatrix = {
            /*         A    T    G    C    S    W    R    Y    K    M    B    V    H    D    N   _   */
            /* A */ {  5,  -4,  -4,  -4,  -4,   1,   1,  -4,  -4,   1,  -4,  -1,  -1,  -1,  -2, GAP},
            /* T */ { -4,   5,  -4,  -4,  -4,   1,  -4,   1,   1,  -4,  -1,  -4,  -1,  -1,  -2, GAP},
            /* G */ { -4,  -4,   5,  -4,   1,  -4,   1,  -4,   1,  -4,  -1,  -1,  -4,  -1,  -2, GAP},
            /* C */ { -4,  -4,  -4,   5,   1,  -4,  -4,   1,  -4,   1,  -1,  -1,  -1,  -4,  -2, GAP},
            /* S */ { -4,  -4,   1,   1,  -1,  -4,  -2,  -2,  -2,  -2,  -1,  -1,  -3,  -3,  -1, GAP},
            /* W */ {  1,   1,  -4,  -4,  -4,  -1,  -2,  -2,  -2,  -2,  -3,  -3,  -1,  -1,  -1, GAP},
            /* R */ {  1,  -4,   1,  -4,  -2,  -2,  -1,  -4,  -2,  -2,  -3,  -1,  -3,  -1,  -1, GAP},
            /* Y */ { -4,   1,  -4,   1,  -2,  -2,  -4,  -1,  -2,  -2,  -1,  -3,  -1,  -3,  -1, GAP},
            /* K */ { -4,   1,   1,  -4,  -2,  -2,  -2,  -2,  -1,  -4,  -1,  -3,  -3,  -1,  -1, GAP},
            /* M */ {  1,  -4,  -4,   1,  -2,  -2,  -2,  -2,  -4,  -1,  -3,  -1,  -1,  -3,  -1, GAP},
            /* B */ { -4,  -1,  -1,  -1,  -1,  -3,  -3,  -1,  -1,  -3,  -1,  -2,  -2,  -2,  -1, GAP},
            /* V */ { -1,  -4,  -1,  -1,  -1,  -3,  -1,  -3,  -3,  -1,  -2,  -1,  -2,  -2,  -1, GAP},
            /* H */ { -1,  -1,  -4,  -1,  -3,  -1,  -3,  -1,  -3,  -1,  -2,  -2,  -1,  -2,  -1, GAP},
            /* D */ { -1,  -1,  -1,  -4,  -3,  -1,  -1,  -3,  -1,  -3,  -2,  -2,  -2,  -1,  -1, GAP},
            /* N */ { -2,  -2,  -2,  -2,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1, GAP},
            /* _ */ {GAP, GAP, GAP, GAP, GAP, GAP, GAP, GAP, GAP, GAP, GAP, GAP, GAP, GAP, GAP, GAP}
    };
    public DNAFull(){
        fillMap();
    };

    public DNAFull(Integer gap){

        fillMap();
        if (gap != null) {
            this.GAP = gap;
            for (int i = 0; i < DNAFullMatrix.length; i++) {
                DNAFullMatrix[15][i] = DNAFullMatrix[i][15] = gap;
            }
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
    public int getGAP() {
        return GAP;
    }
}
