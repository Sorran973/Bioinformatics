package MSA;

import MSA.Matrixes.*;
import MSA.Sequence.FileReader;
import MSA.Sequence.Sequence;
import MSA.Table.Table;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;


public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Sequence> sequences = new ArrayList<>();
        IMatrix imatrix = null;
        int openGAP = -2;   // Дефолтный
        int extendGAP = -1; // Дефолтный
        String outFileName = "out.txt";
        boolean printToFile = false;
        Type typeMatrix = Type.DEFAULT;

        // Обработка командной строки
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-i": {
                    try {
                        String filePath = args[i + 1];
                        FileReader fileReader = new FileReader(filePath);
                        sequences = fileReader.getListOfSequence();
                    } catch (Exception e) {
                        throw new IllegalArgumentException("Expected file with sequences");
                    }
                    break;
                }
                case "-g": {
                    try {
                        openGAP = Integer.parseInt(args[i + 1]);
                        extendGAP = Integer.parseInt(args[i + 2]);
                    } catch (Exception e) {
                        throw new IllegalArgumentException("Expected two gaps");
                    }
                    break;
                }
                case "-t": {
                    String matrixTypeString;
                    try {
                        matrixTypeString = args[i + 1];
                    } catch (Exception e) {
                        throw new IllegalArgumentException("Unexpected type of fine matrix");
                    }
                    switch (matrixTypeString) {
                        case "blosum62": {
                            typeMatrix = Type.BLOSUM62;
                            break;
                        }
                        case "dnafull": {
                            typeMatrix = Type.DNAFULL;
                            break;
                        }
                        default: {
                            typeMatrix = Type.DEFAULT;
                        }
                    }
                    break;
                }
                case "-o": {
                    printToFile = true;
                    break;
                }
            }
        }

        switch (typeMatrix) {
            case BLOSUM62: {
                imatrix = new Blosum62(openGAP, extendGAP);
                break;
            }
            case DNAFULL: {
                imatrix = new DNAFull(openGAP, extendGAP);
                break;
            }
            default: {
                imatrix = new Default(openGAP, extendGAP);
                break;
            }
        }


        ArrayList<Alignment> arrayOfAlignment = new ArrayList<>();

        // Попарно находим выравнивания
        for (int i = 0; i < sequences.size(); i++){
            for (int j = i+1; j < sequences.size(); j++){
                Table table = new Table(sequences.get(i), sequences.get(j), imatrix);
                arrayOfAlignment.add(new Alignment(i, table.getFirstRes(), j, table.getSecondRes(), table.getScore()));
            }
        }

        CenterStar centerStar = new CenterStar(sequences.size(), arrayOfAlignment);

        // Вывод результатов
        if (printToFile) {
            PrintStream printStream = new PrintStream(new File(outFileName));
            centerStar.printResults(printStream);
        } else {
            centerStar.printResults(System.out);
        }
    }
}
