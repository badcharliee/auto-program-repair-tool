import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;

public class FieldParser extends JsonASTParser {

    static Variable extractVariableFrom(JsonObject fieldDeclaration) {

        Variable var = null;

        JsonArray variables = fieldDeclaration.get("variables").getAsJsonArray();

        for (int i = 0; i < variables.size(); i++) {

            JsonObject variable = variables.get(i).getAsJsonObject();
            String nodeName = getStrippedNodeNameFor(variable);

            if (nodeName.equals("VariableDeclarator")) {

                String name = variable.get("name").getAsJsonObject().get("identifier").getAsString();
                String type = variable.get("type").getAsJsonObject().get("type").getAsString();
                String value = "";
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

                var = new Variable(name, type, value, line);
            }

        }

        return var;
    }

}
