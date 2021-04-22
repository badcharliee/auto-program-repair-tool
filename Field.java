import java.util.ArrayList;

public class Field extends Lineament {

    Field() {
        this.variables = new ArrayList<>();
    }

    Field(ArrayList<Variable> variables, Object line) {
        super(line);
        this.variables = variables;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Field(line, variables) -> \n");
        sb.append("\t\tLine: " + (String)getLine() + "\n");
        for (int i = 0; i < variables.size(); i++) {
            sb.append(String.format("\t\t%s\n", variables.get(i).toString()));
        }

        return sb.toString();
    }

    private ArrayList<Variable> variables;

    public ArrayList<Variable> getVariables() {
        return variables;
    }

    public void addVariable(Variable variable) {
        variables.add(variable);
    }

}