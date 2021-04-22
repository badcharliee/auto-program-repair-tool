public class Lineament {

    Lineament() {
        this.line = "-1";
    }

    Lineament(Object line) {
        this.line = line;
    }

    private Object line;

    public Object getLine() {
        return line;
    }

    public void setLine(Object line) {
        this.line = line;
    }

}
