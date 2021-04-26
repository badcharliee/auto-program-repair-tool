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

//        // test Parameter equality
//        Parameter param1 = new Parameter("getLine", "INT", "12");
//        Parameter param2 = new Parameter("getLine", "INT", "21");
//        Parameter param3 = new Parameter("getInteger", "INT", "312");
//        System.out.println(param2.equals(param1));
//
//        // test param hashCode
//        HashMap<Parameter, Parameter> parameters = new HashMap<>();
//        parameters.put(param1, param1);
//
//        Parameter matchingParam = parameters.get(param3);
//        System.out.println(matchingParam);

//        // test Variable equality
//        Variable var1 = new Variable("foo", "DOUBLE", "21.0", "22");
//        Variable var2 = new Variable("foo", "DOUBLE", "21.0", "412");
//        Variable var3 = new Variable("foo", "FLOAT", "21.0", "412");
//        System.out.println(var1.equals(var2)); // true
//        System.out.println(var1.equals(var3)); // false
//        System.out.println(var2.equals(var3)); // false
//        System.out.println(var2.equals(var1)); // true
//
////         test param hashCode
//        HashMap<Variable, Variable> variables = new HashMap<>();
//        variables.put(var1, var1);
//
//        Variable matchingVar = variables.get(var2);
//        Variable nonMatchingVar = variables.get(var3);
//        System.out.println(matchingVar); // non-null
//        System.out.println(nonMatchingVar); // null

//        Parameter method1Param1 = new Parameter("x", "DOUBLE", "11");
//        Parameter method1Param2 = new Parameter("y", "DOUBLE", "12");
//        ArrayList<Parameter> method1Params = new ArrayList<>();
//        method1Params.add(method1Param1);
//        method1Params.add(method1Param2);
//        Method method1 = new Method(new ArrayList<Variable>(), method1Params, "foo", "variableName", "INT", "12");
//
//        Parameter method2Param1 = new Parameter("x", "DOUBLE", "1202");
//        Parameter method2Param2 = new Parameter("y", "DOUBLE", "1222");
//        ArrayList<Parameter> method2Params = new ArrayList<>();
//        method2Params.add(method2Param1);
//        method2Params.add(method2Param2);
//        Method method2 = new Method(new ArrayList<Variable>(), method2Params, "foo", "variableName", "INT", "1222");
//
//        Parameter method3Param1 = new Parameter("X", "INT", "202");
//        Parameter method3Param2 = new Parameter("y", "INT", "3222");
//        ArrayList<Parameter> method3Params = new ArrayList<>();
//        method3Params.add(method3Param2);
//        method3Params.add(method3Param1);
//        Method method3 = new Method(new ArrayList<Variable>(), method3Params, "foo", "variableName", "INT", "1222");
//
//        System.out.println(method1.getSignature().equals(method2.getSignature())); // true
//        System.out.println(method2.getSignature().equals(method1.getSignature())); // true
//        System.out.println(method2.getSignature().equals(method2.getSignature())); // true
//        System.out.println(method2.getSignature().equals(method3.getSignature())); // false
//
//        // print hash codes for each method signature
//        System.out.println(method1.getSignature().hashCode());
//        System.out.println(method2.getSignature().hashCode());
//        System.out.println(method3.getSignature().hashCode());
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

        for (int i = 0; i < instructorAST.getMethods().size(); i++) {
            MethodSignature instructorMethodSignature = instructorAST.getMethods().get(i).getSignature();
            if (!studentAST.hasMethodForSignature(instructorMethodSignature)) {
                String differenceString = String.format("Instructor defined method with signature: %s, but you didn't define this method.\n", instructorMethodSignature);
                differences.add(new Difference(differenceString, "0"));
            }
        }

        return differences;
    }

    static ArrayList<Difference> checkForExtraStudentMethodSignatures(Class instructorAST, Class studentAST) {
        ArrayList<Difference> differences = new ArrayList<>();

        for (int i = 0; i < studentAST.getMethods().size(); i++) {
            MethodSignature studentMethodSignature = studentAST.getMethods().get(i).getSignature();
            Method studentMethod = studentAST.getMethods().get(i);
            if (!instructorAST.hasMethodForSignature(studentMethodSignature)) {
                String differenceString = String.format("You defined a method with signature: %s, but the instructor has no method with that signature.\n", studentMethodSignature.toString());
                Difference difference = new Difference(differenceString, (String)studentMethod.getLine());
                differences.add(difference);
            }
        }

        return differences;
    }

    static ArrayList<Difference> checkForMissingStudentFieldDefinitions(Class instructorAST, Class studentAST) {
        ArrayList<Difference> differences = new ArrayList<>();

        for (int i = 0; i < instructorAST.getFields().size(); i++) {

            Field instructorField = instructorAST.getFields().get(i);
            if (!studentAST.hasField(instructorField)) {
                String differenceString = String.format("Instructor defined field '%s %s = %s;', but you didn't define this field.\n", instructorField.getVariable().getType(), instructorField.getVariable().getName(), instructorField.getVariable().getValue());
                differences.add(new Difference(differenceString, "0"));
            }

        }

        return differences;
    }

    static ArrayList<Difference> checkForExtraStudentFieldDefinitions(Class instructorAST, Class studentAST) {
        ArrayList<Difference> differences = new ArrayList<>();

        for (int i = 0; i < studentAST.getFields().size(); i++) {

            Field studentField = studentAST.getFields().get(i);
            if (!instructorAST.hasField(studentField)) {
                String differenceString = String.format("You defined a field: '%s %s = %s;', but the instructor has no field with that definition.\n", studentField.getVariable().getType(), studentField.getVariable().getName(), studentField.getVariable().getValue());
                differences.add(new Difference(differenceString, (String)studentField.getLine()));
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

        // check that student has all the field definitions that instructor has
        ArrayList<Difference> missingStudentFields = checkForMissingStudentFieldDefinitions(instructorAST, studentAST);
        if (missingStudentFields.size() > 0)
            differences.addAll(missingStudentFields);

        // check that student field definitions have also been defined by the instructor
        ArrayList<Difference> extraStudentFields = checkForExtraStudentFieldDefinitions(instructorAST, studentAST);
        if (extraStudentFields.size() > 0)
            differences.addAll(extraStudentFields);

        // compare method instance variables

        return differences;
    }
}
