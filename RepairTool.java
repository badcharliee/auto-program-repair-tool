import java.util.ArrayList;
import java.util.HashMap;

public class RepairTool {
    public static void main(String[] args) {
        Parser parser = new Parser();
        Class instructorSimplifiedAST = parser.getSimplifiedASTForJavaFileLocatedAt("/Users/charliewilson/Desktop/ASTTestJavaFiles/Instructor1.java");
        System.out.println(instructorSimplifiedAST);
        Class studentSimplifiedAST = parser.getSimplifiedASTForJavaFileLocatedAt("/Users/charliewilson/Desktop/ASTTestJavaFiles/Student1.java");
        System.out.println(studentSimplifiedAST);
        compareSubmissions(instructorSimplifiedAST, studentSimplifiedAST);
    }

    static HashMap<String, Integer> compareSubmissions(Class instructorAST, Class studentAST) {
        HashMap<String, Integer> differences = new HashMap<>();

        // compare class names
        String instructorClassName = (String)instructorAST.getName();
        String studentClassName = (String)studentAST.getName();
        if (!instructorClassName.equals(studentClassName)) {
            differences.put("Class names do not match", Integer.parseInt((String)studentAST.getLine()));
        }

        // compare number of methods
        Integer numInstructorMethods = instructorAST.getMethods().size();
        Integer numStudentMethods = studentAST.getMethods().size();
        if (numInstructorMethods != numStudentMethods) {
            differences.put("Number of method declarations does not match", 0);
        }

        // compare number of fields
        Integer numInstructorFields = instructorAST.getFields().size();
        Integer numStudentFields = studentAST.getFields().size();
        if (numInstructorFields != numStudentFields) {
            differences.put("Number of field declarations does not match", 0);
        }

        // compare method signatures
        // compare method instance variables
        // compare field definitions

        System.out.println(differences);
        return differences;
    }
}
