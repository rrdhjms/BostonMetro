import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

	private static Scanner scan;
	private static MultigraphADT mGraph;

	public static String getInput() {
		System.out.println("Please Enter An Input Below:");
		scan = new Scanner(System.in);
		String input = scan.nextLine().replaceAll("\\s+", "");
		return input;
	}

	private static String getValidStation() {
		String stationName = getInput();
		String stationID = null;
		int count;

		do {
			count = mGraph.countNodeOccurences(stationName);
			if (count == 0) {
				System.out.println("Station entered does not Exist.");
				stationName = getInput();
			} else if (count > 1) {
				stationID = clarifyMultipleInput(stationName);
			} else {
				stationID = mGraph.getIDFromName(stationName).get(0);
			}
		} while (count == 0);

		return stationID;
	}

	private static String clarifyMultipleInput(String stationName) {
		ArrayList<String> idList = mGraph.getIDFromName(stationName);
		String stationID = "";

		System.out.println("Multiple stations have that name. Which of the following stations do you mean?");
		for (int i = 0; i < idList.size(); i++) {
			System.out.println("Station ID: " + idList.get(i) + " On line: " + mGraph.getEdgeLabelFromID(idList.get(i)));
		}
		while (!idList.contains(stationID)) {
			System.out.print("Enter the ID of the station you wish to select: ");
			stationID = getInput();
			if (!idList.contains(stationID))
				System.out.println("Not a valid ID from the list above.");
		}

		return stationID;
	}

	public static void main(String args[]) throws BadFileException,
			IOException {/* need to change this, just used for testing */
		System.out.println("Welcome To The CS308 Group W07 Graph System\n");
		System.out.print("Enter Numerical Values For Menu Interaction\n");
		MetroMapParser mmp = new MetroMapParser("");
		mGraph = mmp.generateGraphFromFile();
		
		mGraph.printEdgeList();
		
		mGraph.getEdgeLabelsFromID("5");
		
		MetroMapParser.usage();
		boolean exit = false;
		
		while (!exit) {
			System.out.println("Options Are:\n 1. Shortest Route\n 2. Search For Stations \n 3. Exit");
			
			String userChoice = getInput();
			if(userChoice.equals("1")){
				System.out.println("You have chosen: Shortest Route\n");
				System.out.println("Please enter the name of the origin station\n");
				String originID = getValidStation();
				System.out.println("Please enter the name of the destination station\n");
				String destinationID = getValidStation();
				ArrayList<String> shortest = mGraph.searchShortestPath(originID, destinationID);
				displayOutput(shortest);
				/**** rekt ass search happens here ****/
				// testing
				// System.out.println(Multigraph.searchShortestPath("26",
				// "31"));
			}
			else if(userChoice.equals("2")){
				System.out.println("You have chosen: Search For The Station:\n");
				System.out.println("Please enter the name of the station you would like searched\n");
				String stationID = getValidStation();
				System.out.println("Name: " + mGraph.getNodeName(stationID) + "\nID  : " + stationID + 
						           "\nLine: " + mGraph.getEdgeLabelFromID(stationID));
			}
			else if(userChoice.equals("3")){
				System.out.println("night xxx");
				exit = true;
			}
			else{
				System.out.print("Your input was invalid\n Please try again bby!\n");
			}
		}
	}

	public static void displayOutput(ArrayList<String> list) {
		
		String currentLine = "";
		for (int i = 0; i < (list.size() - 1); i++) {
			if(mGraph.getEdgeLabelsFromID(list.get(i+1)).size() > 1){//We Know that there is a change
				if(mGraph.isEdge(list.get(i), list.get(i+1)).size() == 1){//
					currentLine = mGraph.isEdge(list.get(i), list.get(i+1)).get(0);
				}
			}
			if(mGraph.getEdgeLabelsFromID(list.get(i)).size() > 1){
				if(mGraph.isEdge(list.get(i), list.get(i+1)).size() == 1){
					if(!currentLine.equals(mGraph.isEdge(list.get(i), list.get(i+1))) && i != 0){
						System.out.println("You need to change from ");
					}
				}
			}
		
			/**	
					
					&& !(mGraph.isEdge(list.get(i),list.get(i+1)).equals(mGraph.isEdge(list.get(i+1),list.get(i))))  ) {
				
				System.out.println("Change From " + mGraph.getNodeName(list.get(i)) +"Line "+ mGraph.isEdge(list.get(i),list.get(i+1)) 
				+ "towards " + mGraph.getNodeName(list.get(i + 1)) + "On Line" + mGraph.isEdge(list.get(i),list.get(i+1)));
				
				
			}else{
				System.out.println("You Have Arrived At " + mGraph.getNodeName(list.get(i + 1)));
			}
			
			
			
			
			

			//String nextLine = mGraph.isEdge(list.get(i + 1), list.get(i + 2));
			
			if (!currentLine.equals(nextLine)) {
				System.out.println("Change at " + mGraph.getNodeName(list.get(i + 1)) + "to " + nextLine + " towards "
						+ mGraph.getNodeName(list.get(i + 2)));
				/*should say which line?
			} else{
				System.out.println("continue from " + mGraph.getNodeName(list.get(i)) + "on the " + currentLine + " Line towards"
						+ mGraph.getNodeName(list.get(i + 1)));
			}
**/
		}
	}

}
