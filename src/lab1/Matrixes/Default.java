package lab1.Matrixes;


public class Default implements IMatrix {

    private int GAP; // Дефолтный геп

    public Default(){}

    public Default(Integer gap) {
        if (gap != null) {
            this.GAP = gap;
        }
    }


    @Override
    public int get(char x, char y) {
        // Проверка
        if (x != '_' && !Character.isLetter(x)) {
            throw new IllegalStateException("Wrong character in sequence: " + x);
        } else if (y != '_' && !Character.isLetter(y)) {
            throw new IllegalStateException("Wrong character in sequence: " + y);
        }

        if (x == '_' || y == '_') {
            return GAP;
        } else if (x == y) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public int getGAP() {
        return GAP;
    }
}
