import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Apoda {

	ArrayList<Registro> test;
	ArrayList<Registro> validation;
	ArrayList<Registro> training;

    public Apoda() {
    	
    	this.test = Main.testRegs;
    	validation = Main.foldedExamples.get(0);
    	training = Main.foldedExamples.get(1);
    }
    
	ID3 id3 = new ID3();
    Node root = id3.generateTree(Main.foldedExamples.get(1), Main.atributos);
    
    
    
    
    
    
	public double calcError() {
        double error = 0.0;

        return error;
    }


}
