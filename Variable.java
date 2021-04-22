public class Variable extends Lineament {

    Variable(Object name, Object type, Object value, Object line) {
        super(line);
        this.name = name;
        this.type = type;
        this.value = value;
    }

    private Object name;
    private Object type;
    private Object value;

    @Override
    public String toString() {
        return String.format("Variable(name, type, value, line) -> \n\t\t\tName: %s\n \t\t\tType: %s\n \t\t\tValue: %s\n \t\t\tLine: %s\n", name, type, value, getLine());
    }

    public Object getName() {
        return name;
    }

    public Object getType() {
        return type;
    }

    public Object getValue() {
        return value;
    }

    public void setName(Object name) {
        this.name = name;
    }

    public void setType(Object type) {
        this.type = type;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}