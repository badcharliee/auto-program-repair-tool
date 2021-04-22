import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;

class MethodParser extends JsonASTParser {

    static Object extractReturnValueFrom(JsonObject methodDeclaration) {
        Object returnValue = "VOID";
        JsonArray statements = methodDeclaration.get("body").getAsJsonObject().get("statements").getAsJsonArray();

        for (int i = 0; i < statements.size(); i++) {
            JsonObject statement = statements.get(i).getAsJsonObject();
            String nodeName = getStrippedNodeNameFor(statement);

            if (nodeName.equals("ReturnStmt")) {
                JsonObject expression = statement.get("expression").getAsJsonObject();
                nodeName = getStrippedNodeNameFor(expression);

                if (nodeName.equals("NameExpr")) {
                    returnValue = expression.get("name").getAsJsonObject().get("identifier").getAsString();
                }
            }
        }

        return returnValue;
    }

    static Object extractReturnTypeFrom(JsonObject methodDeclaration) {

        Object returnType = "";

        try {
            returnType = methodDeclaration.get("type").getAsJsonObject().get("type").getAsString();
        } catch (NullPointerException nullPointerException) {
            returnType = "VOID";
        }

        return returnType;
    }

    static Object extractNameFrom(JsonObject methodDeclaration) {

        Object name = "null";
        name = methodDeclaration.get("name").getAsJsonObject().get("identifier").getAsString();

        return name;
    }

    static ArrayList<Variable> extractVariablesFrom(JsonObject methodDeclaration) {

        ArrayList<Variable> vars = new ArrayList<>();
        JsonArray statements;

        try {
            statements = methodDeclaration.get("body").getAsJsonObject().get("statements").getAsJsonArray();
            for (int i = 0; i < statements.size(); i++) {
                JsonObject statement = statements.get(i).getAsJsonObject();
                String nodeName = getStrippedNodeNameFor(statement);

                if (nodeName.equals("ExpressionStmt")) {
                    // parse out json variables
                    JsonObject expression = statement.get("expression").getAsJsonObject();
                    JsonArray variables = expression.get("variables").getAsJsonArray();

                    for (int j = 0; j < variables.size(); j++) {
                        JsonObject variable = variables.get(i).getAsJsonObject();

                        Object name = variable.get("name").getAsJsonObject().get("identifier").getAsString();
                        Object type = variable.get("type").getAsJsonObject().get("type").getAsString();
                        Object value = "";
                        Object line = extractLineFor(variable);

                        try {
                            JsonObject initializer = variable.get("initializer").getAsJsonObject();
                            nodeName = getStrippedNodeNameFor(initializer);

                            if (nodeName.equals("IntegerLiteralExpr")) {
                                value = initializer.getAsJsonObject().get("value").getAsString();
                            }
                        } catch (NullPointerException nullPointerException) {
                            value = "NULL";
                        }

                        Variable var = new Variable(name, type, value, line);
                        vars.add(var);
                    }
                }
            }
        } catch (NullPointerException nullPointerException) {
            return vars;
        }

        return vars;
    }

    static ArrayList<Parameter> extractParametersFrom(JsonObject methodDeclaration) {

        ArrayList<Parameter> params = new ArrayList<>();

        JsonArray parameters = methodDeclaration.get("parameters").getAsJsonArray();

        for (int i = 0; i < parameters.size(); i++) {
            JsonObject parameter = parameters.get(i).getAsJsonObject();
            Object name = parameter.get("name").getAsJsonObject().get("identifier").getAsString();
            Object type = parameter.get("type").getAsJsonObject().get("type").getAsString();
            Object line = extractLineFor(parameter);

            Parameter param = new Parameter(name, type, line);
            params.add(param);
        }

        return params;

    }

}