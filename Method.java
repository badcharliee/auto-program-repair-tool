import javassist.bytecode.SignatureAttribute;

import java.util.ArrayList;
import java.util.HashMap;

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
        this.signature = new MethodSignature(parameters, (String)name, (String)returnType);
        this.variablesHashMap = convertVariablesToHashMap();
    }

    private ArrayList<Variable> variables;
    private ArrayList<Parameter> parameters;
    private HashMap<Variable, Variable> variablesHashMap;
    private MethodSignature signature;
    private Object name;
    private Object returnValue;
    private Object returnType;

    // converts array list of Variables to a HashMap for easy lookup
    private HashMap<Variable, Variable> convertVariablesToHashMap() {
        HashMap<Variable, Variable> variablesHashMap = new HashMap<>();
        for (Variable var : variables) {
            variablesHashMap.put(var, var);
        }
        return variablesHashMap;
    }

    // takes a Variable as an argument.
    // returns true if Method contains an equivalent Variable, else return false
    public boolean hasVariable(Variable variable) {
        return variablesHashMap.containsKey(variable) ? true : false;
    }

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

    // TODO: Override equals and hashCode methods

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

    public MethodSignature getSignature() {
        return signature;
    }

    public void setSignature(MethodSignature signature) {
        this.signature = signature;
    }
}