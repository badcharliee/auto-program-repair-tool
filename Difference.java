public class Difference extends Lineament {

    public Difference(String difference, String line) {
        super(line);
        this.difference = difference;
    }

    private String difference;

    @Override
    public String toString() {
        return String.format("%s Found at line %s\n", difference, getLine());
    }

    public String getDifference() {
        return difference;
    }

    public void setDifference(String difference) {
        this.difference = difference;
    }

}
