import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;

public class ClassParser extends JsonASTParser {

    static ArrayList<Method> extractMethodsFrom(JsonObject classOrInterfaceDeclaration) {

        ArrayList<Method> methods = new ArrayList<>();

        JsonArray members = classOrInterfaceDeclaration.get("members").getAsJsonArray();

        for (int i = 0; i < members.size(); i++) {

            JsonObject member = members.get(i).getAsJsonObject();
            String nodeName = getStrippedNodeNameFor(member);

            switch (nodeName) {
                case "MethodDeclaration":
                    // add the method to methods that will return
                    MethodParser methodParser = new MethodParser();
                    Method method = new Method(
                            methodParser.extractVariablesFrom(member),
                            methodParser.extractParametersFrom(member),
                            methodParser.extractNameFrom(member),
                            methodParser.extractReturnValueFrom(member),
                            methodParser.extractReturnTypeFrom(member),
                            methodParser.extractLineFor(member)
                    );
                    methods.add(method);
                    break;
                default:
                    // do something
            }
        }

        return methods;
    }

    static ArrayList<Field> extractFieldsFrom(JsonObject classOrInterfaceDeclaration) {

        ArrayList<Field> fields = new ArrayList<>();

        JsonArray members = classOrInterfaceDeclaration.get("members").getAsJsonArray();

        for (int i = 0; i < members.size(); i++) {

            JsonObject member = members.get(i).getAsJsonObject();
            String nodeName = getStrippedNodeNameFor(member);

            switch (nodeName) {
                case "FieldDeclaration":
                    // add the method declaration to 'methodDeclarations'
                    FieldParser fieldParser = new FieldParser();
                    Field field = new Field(
                            fieldParser.extractVariableFrom(member),
                            fieldParser.extractLineFor(member)
                    );
                    fields.add(field);
                    break;
                default:
                    // do something
            }
        }

        return fields;
    }

    static Object extractNameFrom(JsonObject classOrInterfaceDeclaration) {

        Object name = "";
        name = classOrInterfaceDeclaration.getAsJsonObject().get("name").getAsJsonObject().get("identifier").getAsString();

        return name;
    }

}
