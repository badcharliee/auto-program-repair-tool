import java.util.ArrayList;
import java.util.HashMap;

class Class extends Lineament {

    Class() {
        methods = new ArrayList<>();
        fields = new ArrayList<>();
        name = "";
    }

    Class(ArrayList<Method> methods, ArrayList<Field> fields, Object name, Object line) {
        super(line);
        this.methods = methods;
        this.fields = fields;
        this.name = name;
        this.methodSignatureHashMap = convertMethodSignaturesToHashMap();
    }

    private ArrayList<Method> methods;
    private ArrayList<Field> fields;
    private HashMap<MethodSignature, MethodSignature> methodSignatureHashMap;
    private Object name;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Class(name, methods, fields) -> \n");
        sb.append("\tName: " + (String)name + "\n");

        for (int i = 0; i < methods.size(); i++) {
            sb.append(String.format("\t%s", methods.get(i).toString()));
        }
        for (int i = 0; i < fields.size(); i++) {
            sb.append(String.format("\t%s", fields.get(i).toString()));
        }

        return sb.toString();
    }

    private HashMap<MethodSignature, MethodSignature> convertMethodSignaturesToHashMap() {
        HashMap<MethodSignature, MethodSignature> methodSignatureHashMap = new HashMap<>();

        for (int i = 0; i < methods.size(); i++) {
            MethodSignature signature = methods.get(i).getSignature();
            methodSignatureHashMap.put(signature, signature);
        }

        return methodSignatureHashMap;
    }

    // takes a Method as an argument.
    // returns true if Class contains an equivalent MethodSignature for Method argument, else return false
    public boolean hasMethodForSignature(MethodSignature signature) {
        return methodSignatureHashMap.containsKey(signature) ? true : false;
    }

    public ArrayList<Method> getMethods() {
        return methods;
    }

    public ArrayList<Field> getFields() {
        return fields;
    }

    public Object getName() { return name; }

    public void addMethod(Method method) {
        methods.add(method);
    }

    public void addField(Field field) {
        fields.add(field);
    }

    public void setName(Object name) {
        this.name = name;
    }

    public HashMap<MethodSignature, MethodSignature> getMethodsHashMap() {
        return methodSignatureHashMap;
    }

    public void setMethodsHashMap(HashMap<MethodSignature, MethodSignature> methodSignatureHashMap) {
        this.methodSignatureHashMap = methodSignatureHashMap;
    }

}