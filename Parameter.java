public class Parameter extends Lineament {

    Parameter(String name, String type, Object line) {
        super(line);
        this.name = name;
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format("Parameter(name, type, line) -> \n\t\t\tName: %s\n \t\t\tType: %s\n \t\t\tLine: %s\n", name, type, getLine());
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Parameter))
            return false;
        Parameter other = (Parameter)o;
        boolean nameEquals = (this.name == null && other.name == null)
                || (this.name != null && this.name.equals(other.name));
        boolean typeEquals = (this.type == null && other.type == null)
                || (this.type != null && this.type.equals(other.type));
        return nameEquals && typeEquals;
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
        return result;
    }

    private String name;
    private String type;

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }
}
