public class Variable extends Lineament {

    Variable(String name, String type, String value, Object line) {
        super(line);
        this.name = name;
        this.type = type;
        this.value = value;
    }

    private String name;
    private String type;
    private String value;

    @Override
    public String toString() {
        return String.format("Variable(name, type, value, line) -> \n\t\t\tName: %s\n \t\t\tType: %s\n \t\t\tValue: %s\n \t\t\tLine: %s\n", name, type, value, getLine());
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Variable))
            return false;
        Variable other = (Variable)o;
        boolean nameEquals = (this.name == null && other.name == null)
                || (this.name != null && this.name.equals(other.name));
        boolean typeEquals = (this.type == null && other.type == null)
                || (this.type != null && this.type.equals(other.type));
        boolean valueEquals = (this.value == null && other.value == null)
                || (this.value != null && this.value.equals(other.value));
        return nameEquals && typeEquals && valueEquals;
    }

    @Override
    public final int hashCode() {
        int result = 17;
        if (name != null) {
            result = 31 * result + name.hashCode();
        }
        if (type != null) {
            result = 31 * result + type.hashCode();
        }
        if (value != null) {
            result = 31 * result + value.hashCode();
        }
        return result;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setValue(String value) {
        this.value = value;
    }
}