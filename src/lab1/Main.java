package lab1;

import lab1.Sequence.*;
import lab1.Table.*;
import lab1.Matrixes.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;


public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Sequence> sequences = new ArrayList<>();
        IMatrix imatrix = null;
        int gap = -2; // Дефолтный геп
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
                        if (sequences.size() != 2) {
                            throw new IllegalArgumentException("Unexpected count of sequences");
                        }
                    } catch (Exception e) {
                        throw new IllegalArgumentException("Expected file with sequences");
                    }
                    break;
                }
                case "-g": {
                    try {
                        gap = Integer.parseInt(args[i + 1]);
                    } catch (Exception e) {
                        throw new IllegalArgumentException("Expected gap");
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
                        case "default": {
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
                imatrix = new Blosum62(gap);
                break;
            }
            case DNAFULL: {
                imatrix = new DNAFull(gap);
                break;
            }
            default: {
                imatrix = new Default(gap);
                break;
            }
        }

        // Вычисления результатов
        Table table = new Table(sequences.get(0), sequences.get(1), imatrix);

        // Вывод результатов
        if (printToFile) {
            PrintStream printStream = new PrintStream(new File(outFileName));
            table.showResults(printStream);
        } else {
            table.showResults(System.out);
        }
    }
}
