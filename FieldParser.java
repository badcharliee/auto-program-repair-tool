import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;

public class FieldParser extends JsonASTParser {

    static ArrayList<Variable> extractVariablesFrom(JsonObject fieldDeclaration) {

        ArrayList<Variable> vars = new ArrayList<>();

        JsonArray variables = fieldDeclaration.get("variables").getAsJsonArray();

        for (int i = 0; i < variables.size(); i++) {

            JsonObject variable = variables.get(i).getAsJsonObject();
            String nodeName = getStrippedNodeNameFor(variable);

            if (nodeName.equals("VariableDeclarator")) {

                Object name = variable.get("name").getAsJsonObject().get("identifier").getAsString();
                Object type = variable.get("type").getAsJsonObject().get("type").getAsString();
                Object value = "";
                Object line = extractLineFor(variable);

                try {
                    JsonObject initializer = variable.get("initializer").getAsJsonObject();
                    nodeName = getStrippedNodeNameFor(initializer);

                    if (nodeName.equals("IntegerLiteralExpr")) {
                        value = initializer.get("value").getAsString();
                    }
                } catch (NullPointerException nullPointerException) {
                    value = "NULL";
                }

                Variable var = new Variable(name, type, value, line);
                vars.add(var);
            }

        }

        return vars;
    }

}
