public class Difference extends Lineament {

    public Difference(String difference, String line) {
        super(line);
        this.difference = difference;
    }

    private String difference;

    @Override
    public String toString() {
        String line = (String)getLine();
        if(!line.equals("0")) {
            return String.format("%s Found at line %s\n", difference, getLine());
        }
        return String.format("%s\n", difference);
    }

    public String getDifference() {
        return difference;
    }

    public void setDifference(String difference) {
        this.difference = difference;
    }

}
