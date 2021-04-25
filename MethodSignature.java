import java.util.ArrayList;
import java.util.HashMap;

public class MethodSignature {

    MethodSignature(ArrayList<Parameter> parameters, String name, String returnType) {
        this.parameters = parameters;
        this.name = name;
        this.returnType = returnType;
        this.parametersHashMap = convertParametersToHashMap();
    }

    private ArrayList<Parameter> parameters;
    private String name;
    private String returnType;
    private HashMap<Parameter, Parameter> parametersHashMap;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("'%s %s(", returnType, name));
        for (int i = 0; i < parameters.size(); i++) {
            if (i < parameters.size() - 1) {
                sb.append(String.format(" %s %s, ", parameters.get(i).getType(), parameters.get(i).getName()));
            } else {
                sb.append(String.format(" %s %s )'", parameters.get(i).getType(), parameters.get(i).getName()));
            }
        }

        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof MethodSignature))
            return false;
        MethodSignature other = (MethodSignature)o;
        boolean nameEquals = (this.name == null && other.name == null)
                || (this.name != null && this.name.equals(other.name));
        boolean returnTypeEquals = (this.returnType == null && other.returnType == null)
                || (this.returnType != null && this.returnType.equals(other.returnType));
        boolean parametersEquals = true;

        if (!(this.parameters.size() == other.parameters.size())) {
            parametersEquals = false;
        } else {
            parametersEquals = this.getParametersHashMap().equals(other.getParametersHashMap()) ? true : false;
        }

        return nameEquals && returnTypeEquals && parametersEquals;
    }

    @Override
    public final int hashCode() {
        int result = 17;
        if (name != null) {
            result = 31 * result + name.hashCode();
        }
        if (returnType != null) {
            result = 31 * result + returnType.hashCode();
        }
        for (HashMap.Entry<Parameter, Parameter> entry : this.parametersHashMap.entrySet()) {
            result = 31 * result + entry.getKey().hashCode();
        }
        return result;
    }

    // converts array list of Parameters to a HashMap for easy lookup
    private HashMap<Parameter, Parameter> convertParametersToHashMap() {
        HashMap<Parameter, Parameter> parametersHashMap = new HashMap<>();
        for (Parameter param : parameters) {
            parametersHashMap.put(param, param);
        }
        return parametersHashMap;
    }

    // takes a Parameter as an argument.
    // returns true if Method contains an equivalent Parameter, else return false
    public boolean hasParameter(Parameter parameter) {
        return parametersHashMap.containsKey(parameter) ? true : false;
    }

    public ArrayList<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(ArrayList<Parameter> parameters) {
        this.parameters = parameters;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public HashMap<Parameter, Parameter> getParametersHashMap() {
        return parametersHashMap;
    }

    public void setParametersHashMap(HashMap<Parameter, Parameter> parametersHashMap) {
        this.parametersHashMap = parametersHashMap;
    }
}
