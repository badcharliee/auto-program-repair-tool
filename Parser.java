import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.serialization.JavaParserJsonSerializer;
import com.github.javaparser.ast.Node;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.json.Json;
import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonGeneratorFactory;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static com.github.javaparser.StaticJavaParser.parse;

public class Parser extends JsonASTParser {

    static Class extractClassFrom(JsonObject compilationUnit) {

        Class aClass = null;
        JsonArray types = compilationUnit.get("types").getAsJsonArray();

        if (types.size() > 0) {
            JsonObject currentObject = types.get(0).getAsJsonObject();
            String nodeName = getStrippedNodeNameFor(currentObject);

            if (nodeName.equals("ClassOrInterfaceDeclaration")) {
                // get class from json ast
                ClassParser classParser = new ClassParser();
                aClass = new Class(
                        classParser.extractMethodsFrom(currentObject),
                        classParser.extractFieldsFrom(currentObject),
                        classParser.extractNameFrom(currentObject),
                        classParser.extractLineFor(currentObject)
                );
            }
        }

        return aClass;
    }

    static String serialize(Node node, boolean prettyPrint) {
        Map<String, ?> config = new HashMap<>();
        if (prettyPrint) {
            config.put(JsonGenerator.PRETTY_PRINTING, null);
        }
        JsonGeneratorFactory generatorFactory = Json.createGeneratorFactory(config);
        JavaParserJsonSerializer serializer = new JavaParserJsonSerializer();
        StringWriter jsonWriter = new StringWriter();
        try {
            JsonGenerator generator = generatorFactory.createGenerator(jsonWriter);
            serializer.serialize(node, generator);
        } catch (Exception err) {
            System.out.println("EXCEPTION: " + err);
        }
        return jsonWriter.toString();
    }

    public Class getSimplifiedASTForJavaFileLocatedAt(String filePathString) {

        String javaFileString = "";

        try {
            // get contents of java file as a String
            javaFileString = new String(Files.readAllBytes( Paths.get( filePathString ) ));

            CompilationUnit compilationUnit = parse(javaFileString);
            String astJsonString = serialize(compilationUnit, true);

            // print AST json string
//            System.out.println(astJsonString);

            // convert AST json string into JsonObject
            JsonParser parser = new JsonParser();
            JsonObject ast = parser.parse(astJsonString).getAsJsonObject();

            // convert AST JsonObject into Class
            Class aClass = extractClassFrom(ast);
            return aClass;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
