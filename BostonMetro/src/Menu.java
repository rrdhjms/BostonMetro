import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
	
   private static Scanner scan; 
	
   public static String getInput() {
	   System.out.println("Please Enter An Input Below:");
	   scan = new Scanner(System.in);
	   String input = scan.nextLine().toLowerCase().replaceAll("\\s+", "");
	   return input;
   }
   
   public static void main(String args[]) throws BadFileException, IOException {/*need to change this, just used for testing*/
		System.out.println("Welcome To The CS308 Group W07 Graph System\n");
		System.out.print("Enter Numerical Values For Menu Interaction\n");
		    MetroMapParser mmp = new MetroMapParser("");
		    mmp.generateGraphFromFile();
		    mmp.usage();
        boolean exit = false;
		while(!exit){
			System.out.println("Options Are:\n 1. Shortest Route\n 2. Search For Stations \n 3. Exit");
			String userChoice = getInput();
			switch(userChoice){
			case "1":
				System.out.println("You have chosen: Shortest Route\n");
				System.out.println("Please enter the name of the origin station\n");
				String origin = getInput();
				System.out.println("Please enter the name of the destination station\n");
				String destination = getInput();
				/****rekt ass search happens here****/
				// testing System.out.println(Multigraph.searchShortestPath("26", "31"));
				
			case "2":
				System.out.println("You have chosen: Search For The Station:\n");
				System.out.println("Please enter the name of the station you would like searched\n");
				String searchStation = getInput();
			case "3": 
				System.out.println("night xxx");
				System.exit(0);
				
			default:
					System.out.print("Your input was invalid\n Please try again bby!\n");
			}
		}
   }
   
   public void displayOutput(ArrayList <Node> list) {
      // TODO implement this operation
      throw new UnsupportedOperationException("not implemented");
   }
   
   }
