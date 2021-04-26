import java.util.ArrayList;
import java.util.HashMap;

public class Field extends Lineament {

    Field() {
        this.variable = null;
    }

    Field(Variable variable, Object line) {
        super(line);
        this.variable = variable;
    }

    private Variable variable;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Field(line, variable) -> \n");
        sb.append("\t\tLine: " + (String)getLine() + "\n");
        sb.append(String.format("\t\t%s\n", getVariable().toString()));
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Field))
            return false;
        Field other = (Field)o;
        boolean variableEquals = (this.variable == null && other.variable == null)
                || (this.variable != null && this.variable.equals(other.variable));
        return variableEquals;
    }

    @Override
    public final int hashCode() {
        int result = 17;
        if (variable != null) {
            result = 31 * result + variable.hashCode();
        }
        return result;
    }

    public Variable getVariable() {
        return variable;
    }

    public void setVariable(Variable variable) {
        this.variable = variable;
    }

}