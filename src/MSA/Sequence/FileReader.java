package MSA.Sequence;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class FileReader implements Iterator<Sequence> {

    private File file;
    private List<String> lines;
    private ArrayList<Sequence> listOfSequence = new ArrayList<Sequence>();
    private int currentLine;
    private Sequence next;


    public FileReader(String filePath) {
        this.file = new File(filePath);
        this.currentLine = 0;

        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            listOfSequence.add(next());
            if (listOfSequence.get(i) == null) {
                listOfSequence.remove(i);
                break;
            }
        }

    }

    @Override
    public boolean hasNext() {

        try {
            if (lines == null) {
                lines = Files.readAllLines(file.toPath());
            }
        } catch (IOException e) {
            throw new IllegalStateException("Can't read from file: " + file, e);
        }

        if (next != null) {
            return true;
        } else if (currentLine + 1 < lines.size()) {
            next = getNext();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Sequence next() {
        if (!hasNext()) {
            return null;
        }

        if (next != null) {
            Sequence result = next;
            next = null;
            return result;
        } else {
            return getNext();
        }
    }

    private Sequence getNext() {
        String name = lines.get(currentLine);

        currentLine++;

        StringBuilder sequence = new StringBuilder();

        for (int i = currentLine; i < lines.size(); i++) {
            if (lines.get(i).charAt(0) == '>') {
                break;
            } else {
                sequence.append(lines.get(i));
                currentLine++;
            }
        }

        return new Sequence(name, sequence.toString());
    }

    public ArrayList<Sequence> getListOfSequence(){
        return listOfSequence;
    }

}
