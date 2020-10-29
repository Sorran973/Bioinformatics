package MSA.Sequence;


public class Sequence {

    private String name;
    private String sequence;

    public Sequence(String name, String sequence) {
        this.name = name.substring(1, name.length());
        this.sequence = sequence;
    }

    public int size() {
        return sequence.length();
    }

    public char get(int i) {
        return sequence.charAt(i);
    }

    public String getSequence(){
        return sequence;
    }
    public String getName(){
        return name;
    }

}