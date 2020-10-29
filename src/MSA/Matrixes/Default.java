package MSA.Matrixes;


public class Default implements IMatrix {

    private int openGAP;
    private int extendGAP;

    public Default(){}

    public Default(Integer openGAP, Integer extendGAP) {
        if (openGAP != null) {
            this.openGAP = openGAP;
        }

        if (extendGAP != null) {
            this.extendGAP = extendGAP;
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
            return openGAP;
        } else if (x == y) {
            return 1;
        } else {
            return -1;
        }
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
