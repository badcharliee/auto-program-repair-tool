import java.util.ArrayList;

class Method extends Lineament {

    Method() {
        variables = new ArrayList<>();
        parameters = new ArrayList<>();
        name = "";
        returnValue = "";
        returnType = "";
    }

    Method(ArrayList<Variable> variables, ArrayList<Parameter> parameters, Object name, Object returnValue, Object returnType, Object line) {
        super(line);
        this.variables = variables;
        this.parameters = parameters;
        this.name = name;
        this.returnValue = returnValue;
        this.returnType = returnType;
    }

    private ArrayList<Variable> variables;
    private ArrayList<Parameter> parameters;
    private Object name;
    private Object returnValue;
    private Object returnType;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Method(name, returnValue, returnType, line, variables, parameters) -> \n");

        sb.append("\t\tName: " + (String)name + "\n");
        sb.append("\t\tReturn Value: " + (String)returnValue + "\n");
        sb.append("\t\tReturn Type: " + (String)returnType + "\n");
        sb.append("\t\tLine: " + (String)getLine() + "\n");

        for (int i = 0; i < variables.size(); i++) {
            sb.append(String.format("\t\t%s", variables.get(i).toString()));
        }

        for (int i = 0; i < parameters.size(); i++) {
            sb.append(String.format("\t\t%s", parameters.get(i).toString()));
        }

        return sb.toString();
    }

    public ArrayList<Variable> getVariables() {
        return variables;
    }

    public ArrayList<Parameter> getParameters() {
        return parameters;
    }

    public Object getName() { return name; }

    public Object getReturnValue() {
        return returnValue;
    }

    public Object getReturnType() { return returnType; }

    public void addVariable(Variable variable) {
        variables.add(variable);
    }

    public void addParameter(Parameter parameter) {
        parameters.add(parameter);
    }

    public void setName(Object name) { this.name = name; }

    public void setReturnValue(Object returnValue) {
        this.returnValue = returnValue;
    }

    public void setReturnType(Object returnType) { this.returnType = returnType; }
}