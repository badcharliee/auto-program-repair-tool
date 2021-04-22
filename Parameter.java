public class Parameter extends Lineament {

    Parameter(Object name, Object type, Object line) {
        super(line);
        this.name = name;
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format("Parameter(name, type, line) -> \n\t\t\tName: %s\n \t\t\tType: %s\n \t\t\tLine: %s\n", name, type, getLine());
    }

    private Object name;
    private Object type;

    public Object getName() {
        return name;
    }

    public Object getType() {
        return type;
    }

    public void setName(Object name) {
        this.name = name;
    }

    public void setType(Object type) {
        this.type = type;
    }
}
