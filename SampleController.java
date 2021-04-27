import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class SampleController {

    //Data Members---------------------------------
    @FXML
    private BorderPane boarderPane;
    @FXML
    private AnchorPane anchorPane;
    private int numSelect;
    public String profJava;
    public String studJava;
    public String currentSel;
    Alert a = new Alert(AlertType.NONE);
    Alert b = new Alert(AlertType.NONE);
    Alert c = new Alert(AlertType.NONE);

    @FXML
    private MenuBar menuBar;
    //----------------------------------
    @FXML
    private Menu menuHelp;
    @FXML
    private MenuItem menuItemReadMe;
    //-------
    @FXML
    private Menu menuFile;
    @FXML
    private MenuItem menuItemReset;
    @FXML
    private MenuItem menuItemExit;
    //----------------------------------

    @FXML
    private SplitPane splitPane;
    //----------------------------------
    @FXML
    private AnchorPane AnchorPaneTop;
    @FXML
    private TextField javaCodeInp;
    @FXML
    private ToggleGroup toggleBtnGroup;
    @FXML
    private ToggleButton profBtn;
    @FXML
    private ToggleButton StudBtn;
    @FXML
    private Button compareBtn;
    @FXML
    private Button loadBtn;
    //-------
    @FXML
    private AnchorPane AnchorPaneBottom;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private TextArea outputTextArea;
    //----------------------------------
//----------------------------------------------

    //Methods---------------------------------------
    @FXML
    void CompareBtnPressed(ActionEvent event) {
        if((studJava != null && profJava != null) && (!studJava.equals(profJava))) {
            outputTextArea.appendText("--------------------------------------------------------------------------"+"\n");
//    		//Run code here from Charlie's section
//    		//---
//    		//----
//
            // parse java files into simplified AST objects
            Parser parser = new Parser();
            Class instructorSimplifiedAST = parser.getSimplifiedASTForJavaFileLocatedAt("/Users/WhiteRose/Desktop/ASTTestJavaFiles/" + profJava);
            Class studentSimplifiedAST = parser.getSimplifiedASTForJavaFileLocatedAt("/Users/WhiteRose/Desktop/ASTTestJavaFiles/" + studJava);

            // print simple AST objects
            //outputTextArea.appendText("Instructor Simplified AST: \n\n" + instructorSimplifiedAST);
            //outputTextArea.appendText("Student Simplified AST: \n\n" + studentSimplifiedAST);

            // compare simple ASTs for student and instructor
            ArrayList<Difference> differences = RepairTool.compareSubmissions(instructorSimplifiedAST, studentSimplifiedAST);

            // For Each Loop for iterating ArrayList
            for (Difference i : differences)
            	outputTextArea.appendText(i + "\n");

        }else {
            // set alert type
            a.setAlertType(AlertType.WARNING);

            // set content text
            a.setContentText("One Of The Java Files Have Not Been Submitted, Or Files Submitted Are The Same!");

            // show the dialog
            a.show();
        }
    }

    @FXML
    void exitProgram(ActionEvent event) {
        // Terminate
        //outputTextArea.appendText("Program Has Terminated!" + "\n");
//    	for(int i = 5;i >= 0; i--) {
//    			outputTextArea.appendText(i + " ");
//    	}
//    	try {
//    		Thread.sleep(5000);                 //1000 milliseconds is one second.
//            System.exit(0);
//    		} catch(InterruptedException ex) {
//    		Thread.currentThread().interrupt();
//    		}
//    	//System.out.println("Program Has Terminated!");
        System.exit(0);

    }

    @FXML	//Works for pressing both student and professor
    void setStatus(ActionEvent event) {
        if(event.getSource().equals(profBtn)) {
            if(numSelect != 1) {
                outputTextArea.appendText("Professor Code Submission Has Been Selected!" + "\n");
                //System.out.println("Professor Code Submission Has Been Selected!");
                numSelect = 1;
            }
        }else if(event.getSource().equals(StudBtn)) {
            if(numSelect != 2) {
                outputTextArea.appendText("Student Code Submission Has Been Selected!" + "\n");
                //System.out.println("Student Code Submission Has Been Selected!");
                numSelect = 2;
            }
        }
    }

    @FXML  //Load button clicked method
    void loadInput(ActionEvent event) {
        if(javaCodeInp.getText().endsWith(".java")) {	//Checks to make sure it is a java file
            if(numSelect == 1) {
                profJava = javaCodeInp.getText();
                outputTextArea.appendText("File: [" + javaCodeInp.getText() + "] has been set as the Professor code!" + "\n");
                //System.out.println("File: " + javaCodeInp.getText() + " has been set as the Professor code!");
            }else {
                studJava = javaCodeInp.getText();
                outputTextArea.appendText("File: [" + javaCodeInp.getText() + "] has been set as the Student code!" + "\n");
                //System.out.println("File: " + javaCodeInp.getText() + " has been set as the Student code!");
            }
        }else {
            // set alert type
            b.setAlertType(AlertType.WARNING);

            // set content text
            b.setContentText("Submission is not a .java file!");

            // show the dialog
            b.show();
        }

    }

    @FXML
    void readMePressed(ActionEvent event) {
        //Have the read-Me file open up in another text box
        //----
        //--
        //-
        // set alert type
        a.setAlertType(AlertType.INFORMATION);

        // set content text
        a.setContentText("This project compares a Student implementation of a .java file to an Instructor's implementation of a .java file. It should then output differences when compared."
                + "\r\n"
                + "\r\n"
                + "Input: Instructor .java file, Student .java file " + "\r\n"
                + "Output: Differences to display to Student");

        // show the dialog
        a.show();

        outputTextArea.appendText("Read me file has been opened!" + "\n");
    }

    @FXML
    void resetProgram(ActionEvent event) {
        javaCodeInp.clear();
        outputTextArea.clear();
        profJava = null;
        studJava = null;

        outputTextArea.appendText("Program has been reset!" + "\n");
        //System.out.println("Program has been reset!");
    }
//----------------------------------------------

}