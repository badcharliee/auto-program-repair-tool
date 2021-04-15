import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTMatcher;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;
import org.eclipse.jdt.astview.*;

public class parsingASTS {
	static ArrayList<String> x = new ArrayList<>();
	//use ASTParse to parse string
	public static void parse(String str) {
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		parser.setSource(str.toCharArray());
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
	    CompilationUnit cu = (CompilationUnit) parser.createAST(null);
	   
 
		cu.accept(new ASTVisitor() {
			//This set contains the names of each node in the ast
			Set<String> names = new HashSet<String>();
 
			public boolean visit(VariableDeclarationFragment node) {
				SimpleName name = node.getName();
				x.add(name.getIdentifier());
				System.out.println(node.getRoot());
				// adds the names of each node in the tree to the set,
				//this.names.add(name.getIdentifier());
				//System.out.println("Declaration of '" + name + "' at line"+ cu.getLineNumber(name.getStartPosition()));
				return false; // do not continue 
			}
 
			public boolean visit(SimpleName node) {
				// check to see if the set has a specific variable, if so state where it was used
				if (this.names.contains(node.getIdentifier())) {
					System.out.println("Usage of '" + node + "' at line "+ cu.getLineNumber(node.getStartPosition()));
				}
				return true;
			}
		});
		System.out.println(x.toString());
 
	}
 
	//read file content into a string
	public static String readFileToString(String filePath) throws IOException {
		StringBuilder fileData = new StringBuilder();
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		String line = reader.readLine();
		
		while (line != null) {
			fileData.append(line);
			fileData.append(System.lineSeparator());
			line = reader.readLine();
		}
		reader.close();
		return  fileData.toString();	
	}
 
	public static void ParseFile() throws IOException, FileNotFoundException{
		// Specify a directory and filename if you want to parse a file from a different directory
		File testFile = new File("/Users/WhiteRose/Downloads/Graph.Java");
		String filePath = "";
		// get the true path of the file testFile
		filePath = testFile.getAbsolutePath();
		if(testFile.isFile()){
			parse(readFileToString(filePath));
		}
	}
 
	public static void main(String[] args) throws IOException {
		ParseFile();
	}
}
