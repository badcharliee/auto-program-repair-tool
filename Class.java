import java.util.ArrayList;

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
    }

    private ArrayList<Method> methods;
    private ArrayList<Field> fields;
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

}