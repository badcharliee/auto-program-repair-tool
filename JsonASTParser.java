import com.google.gson.JsonObject;

public class JsonASTParser {

    static String getStrippedNodeNameFor(JsonObject jsonObject) {
        String[] nodeNamePath = jsonObject.get("!").getAsString().split("\\.");
        String nodeName = nodeNamePath[nodeNamePath.length - 1];
        return nodeName;
    }

    static Object extractLineFor(JsonObject jsonObject) {
        Object line = jsonObject.get("range").getAsJsonObject().get("beginLine").getAsString();
        return line;
    }

}
