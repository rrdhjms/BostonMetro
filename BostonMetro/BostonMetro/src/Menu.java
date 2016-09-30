import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
	
   private static Scanner scan; 
	
   public static String getInput() {
	   System.out.println("Please Enter An Input");
	   scan = new Scanner(System.in);
	   String input = scan.nextLine();
	   
	   switch(input){
	   //case null:
		   
		   
	   }
	   
	   return input;
   }
   
   public static void main(String args[]) {
		System.out.println("Welcome To The CS308 Group W07 Graph System\n");
        boolean exit = false;
		while(!exit){
			String response = getInput();
		}
		
		
      throw new UnsupportedOperationException("not implemented");
   }
   
   public void displayOutput(ArrayList <Node> list) {
      // TODO implement this operation
      throw new UnsupportedOperationException("not implemented");
   }
   
   }
