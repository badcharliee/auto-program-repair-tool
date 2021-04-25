import java.util.ArrayList;
import java.util.HashMap;

public class RepairTool {
    public static void main(String[] args) {

        // parse java files into simplified AST objects
        Parser parser = new Parser();
        Class instructorSimplifiedAST = parser.getSimplifiedASTForJavaFileLocatedAt("/Users/charliewilson/Desktop/ASTTestJavaFiles/Instructor1.java");
        Class studentSimplifiedAST = parser.getSimplifiedASTForJavaFileLocatedAt("/Users/charliewilson/Desktop/ASTTestJavaFiles/Student1.java");

        // print simple AST objects
        System.out.println("Instructor Simplified AST: \n\n" + instructorSimplifiedAST);
        System.out.println("Student Simplified AST: \n\n" + studentSimplifiedAST);

        // compare simple ASTs for student and instructor
        HashMap<String, Integer> differences= compareSubmissions(instructorSimplifiedAST, studentSimplifiedAST);
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

        // check that student has all the method signatures that instructor has
        ArrayList<Method> instructorMethods = instructorAST.getMethods();
        ArrayList<Method> studentMethods = studentAST.getMethods();
        for (int i = 0; i < instructorMethods.size(); i++) {
            Method instructorMethod = instructorMethods.get(i);
            String instructorMethodName = (String)instructorMethod.getName();
            String instructorMethodReturnType = (String)instructorMethod.getReturnType();
            ArrayList<Parameter> instructorParams = instructorMethod.getParameters();

            // search for matching student method
            boolean studentHasMethodSignature = false;
            for (int j = 0; j < studentMethods.size(); j++) {

                boolean studentHasMethodName = false;
                boolean studentHasMethodReturnType = false;
                boolean studentHasMethodParams = false;

                Method studentMethod = studentMethods.get(j);
                String studentMethodName = (String)studentMethod.getName();
                String studentMethodReturnType = (String)studentMethod.getReturnType();
                ArrayList<Parameter> studentParams = studentMethod.getParameters();

                // check for matching method name
                if (studentMethodName.equals(instructorMethodName)) {
                    studentHasMethodName = true;
                }

                // check for matching return type
                if (studentMethodReturnType.equals(instructorMethodReturnType)) {
                    studentHasMethodReturnType = true;
                }

                // TODO: check for matching params

                // TODO: add check for params to boolean evaluation of method signature
                if (studentHasMethodName && studentHasMethodReturnType) {
                    studentHasMethodSignature = true;
                    break;
                }


            }

            if (!studentHasMethodSignature) {
                String difference = String.format("Instructor defined method with signature: '%s %s(...)', but you didn't define this method.\n", ((String) instructorMethod.getReturnType()).toLowerCase(), instructorMethod.getName());
                differences.put(difference, 0);
            }

        }

        // check that student methods signatures have been defined by the instructor


        // compare method instance variables
        // compare field definitions

        System.out.println(differences);
        return differences;
    }
}
