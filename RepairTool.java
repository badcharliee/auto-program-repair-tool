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
        ArrayList<Difference> differences = compareSubmissions(instructorSimplifiedAST, studentSimplifiedAST);
        System.out.println(differences);
    }

    static Difference compareClassNames(Class instructorAST, Class studentAST) {
        String instructorClassName = (String)instructorAST.getName();
        String studentClassName = (String)studentAST.getName();
        if (!instructorClassName.equals(studentClassName)) {
            Difference difference = new Difference("Class names do not match", (String)studentAST.getLine());
            return difference;
        }

        return null;
    }

    static Difference compareNumMethods(Class instructorAST, Class studentAST) {
        Integer numInstructorMethods = instructorAST.getMethods().size();
        Integer numStudentMethods = studentAST.getMethods().size();
        if (numInstructorMethods != numStudentMethods) {
            Difference difference = new Difference("Number of method declarations does not match", "0");
            return difference;
        }

        return null;
    }

    static Difference compareNumFields(Class instructorAST, Class studentAST) {
        Integer numInstructorFields = instructorAST.getFields().size();
        Integer numStudentFields = studentAST.getFields().size();
        if (numInstructorFields != numStudentFields) {
            Difference difference = new Difference("Number of field declarations does not match", "0");
            return difference;
        }

        return null;
    }

    static ArrayList<Difference> checkForMissingStudentMethodSignatures(Class instructorAST, Class studentAST) {

        ArrayList<Difference> differences = new ArrayList<>();

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
                String differenceString = String.format("Instructor defined method with signature: '%s %s(...)', but you didn't define this method.\n", ((String) instructorMethod.getReturnType()).toLowerCase(), instructorMethod.getName());
                Difference difference = new Difference(differenceString, "0");
                differences.add(difference);
            }

        }

        return differences;
    }

    static ArrayList<Difference> checkForExtraStudentMethodSignatures(Class instructorAST, Class studentAST) {

        ArrayList<Difference> differences = new ArrayList<>();

        ArrayList<Method> studentMethods = studentAST.getMethods();
        ArrayList<Method> instructorMethods = instructorAST.getMethods();
        for (int i = 0; i < studentMethods.size(); i++) {
            Method studentMethod = studentMethods.get(i);
            String studentMethodName = (String)studentMethod.getName();
            String studentMethodReturnType = (String)studentMethod.getReturnType();
            ArrayList<Parameter> studentParams = studentMethod.getParameters();

            // search for matching instructor method
            boolean instructorHasMethodSignature = false;
            for (int j = 0; j < instructorMethods.size(); j++) {

                boolean instructorHasMethodName = false;
                boolean instructorHasMethodReturnType = false;
                boolean instructorHasMethodParams = false;

                Method instructorMethod = instructorMethods.get(i);
                String instructorMethodName = (String)instructorMethod.getName();
                String instructorMethodReturnType = (String)instructorMethod.getReturnType();
                ArrayList<Parameter> instructorParams = instructorMethod.getParameters();

                // check for matching method name
                if (instructorMethodName.equals(studentMethodName)) {
                    instructorHasMethodName = true;
                }

                // check for matching return type
                if (instructorMethodReturnType.equals(studentMethodReturnType)) {
                    instructorHasMethodReturnType = true;
                }

                // TODO: check for matching params

                // TODO: add check for params to boolean evaluation of method signature
                if (instructorHasMethodName && instructorHasMethodReturnType) {
                    instructorHasMethodSignature = true;
                    break;
                }


            }

            if (!instructorHasMethodSignature) {
                String differenceString = String.format("You defined a method with signature: '%s %s(...)', but the instructor has no method with that signature.\n", ((String) studentMethod.getReturnType()).toLowerCase(), studentMethod.getName());
                Difference difference = new Difference(differenceString, (String)studentMethod.getLine());
                differences.add(difference);
            }

        }

        return differences;

    }

    static ArrayList<Difference> compareSubmissions(Class instructorAST, Class studentAST) {
        ArrayList<Difference> differences = new ArrayList<Difference>();

        // compare class names
        Difference classNameDifference = compareClassNames(instructorAST, studentAST);
        if (classNameDifference != null)
            differences.add(classNameDifference);

        // compare number of methods
        Difference numMethodsDifference = compareNumMethods(instructorAST, studentAST);
        if (numMethodsDifference != null)
            differences.add(numMethodsDifference);

        // compare number of fields
        Difference numFieldsDifference = compareNumFields(instructorAST, studentAST);
        if (numFieldsDifference != null)
            differences.add(numFieldsDifference);

        // check that student has all the method signatures that instructor has
        ArrayList<Difference> missingStudentMethodSignatures = checkForMissingStudentMethodSignatures(instructorAST, studentAST);
        if (missingStudentMethodSignatures.size() > 0)
            differences.addAll(missingStudentMethodSignatures);

        // check that student methods signatures have been defined by the instructor
        ArrayList<Difference> extraStudentMethodSignatures = checkForExtraStudentMethodSignatures(instructorAST, studentAST);
        if (extraStudentMethodSignatures.size() > 0)
            differences.addAll(extraStudentMethodSignatures);



        // compare method instance variables
        // compare field definitions

        return differences;
    }
}
