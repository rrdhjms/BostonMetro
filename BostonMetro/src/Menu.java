import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

	private static Scanner scan;
	private static MultigraphADT mGraph;

	/**
	 * Getting the input from the user 
	 **/
	private static String getInput() {
		System.out.print("Please Enter An Input: ");
		scan = new Scanner(System.in);
		String input = scan.nextLine().replaceAll("\\s+", "");
		return input;
	}

	/**
	 *  Checking if the input given is acceptable. Returns the stationID so
	 *   that it can continue with the search
	 *  
	 *  @return String stationID 
	 **/
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

	/**
	 *  Used in the case of having two or more stations with the same name. Gets as
	 *  an input the name of the station, asks the user for which station is referring to
	 *  and returns the correct station ID to be used for the search.                        
	 * 
	 * @param  String stationName
	 * @return String stationID
	 **/
	private static String clarifyMultipleInput(String stationName) {
		ArrayList<String> idList = mGraph.getIDFromName(stationName);
		String stationID = "";
		System.out.println("Multiple stations have that name. Which of the following stations do you mean?");
		
		for (int i = 0; i < idList.size(); i++) {
			System.out
					.println("Station ID: " + idList.get(i) + " On line: " + mGraph.getEdgeLabelsFromID(idList.get(i)));
		}
		
		while (!idList.contains(stationID)) {
			System.out.print("Enter the ID of the station you wish to select: ");
			stationID = getInput();
			if (!idList.contains(stationID))
				System.out.println("Not a valid ID from the list above.");
		}

		return stationID;
	}

	/**
	 *   It creates an instance of MapParser which then generates the multigraph. It prints out the menu
	 *   and gets and checks the input by the user with the help of getInput and getValidStation methods.
	 *  
	 **/
	public static void main(String args[]) throws BadFileException,
			IOException {
		System.out.println("Welcome To The CS308 Group W07 Graph System\n");
		System.out.print("Enter Numerical Values For Menu Interaction\n");
		MetroMapParser mmp = new MetroMapParser("");
		mGraph = mmp.generateGraphFromFile();
		boolean exit = false;

		while (!exit) {
			System.out.println("Options Are:\n 1. Shortest Route\n 2. Search For Stations \n 3. Exit");
			String userChoice = getInput();
			
			if (userChoice.equals("1")) {
				System.out.println("You have chosen: Shortest Route.\n");
				System.out.println("Please enter the name of the origin station");
				String originID = getValidStation();
				System.out.println("Please enter the name of the destination station");
				String destinationID = getValidStation();
				while (originID.equals(destinationID)) {
					System.out.println("Dsetination is the same as Origin, enter new destination.");
					destinationID = getValidStation();
				}
				ArrayList<String> shortest = mGraph.searchShortestPath(originID, destinationID);
				displayOutput(shortest);
			} else if (userChoice.equals("2")) {
				System.out.println("You have chosen: Search For The Station:\n");
				System.out.println("Please enter the name of the station you would like searched\n");
				String stationID = getValidStation();
				System.out.println("Name: " + mGraph.getNodeName(stationID) + "\nID  : " + stationID + "\nLines: " + mGraph.getEdgeLabelsFromID(stationID));
			} else if (userChoice.equals("3")) {
				System.out.println("Thank You For Using Our System! Exiting...");
				MetroMapParser.usage();
				exit = true;
			} else {
				System.out.print("Your input was invalid\n Please try again.\n");
			}
		}
	}

	
	/**
	 * Gets the list of the stations of the shortest path given by the search. Checks if 
	 * there are changes of lines in this path and outputs steps on how to go from one station to
	 * the other in the shortest way.                           
	 * 
	 * @param  ArrayList<String> stationList
	 * 
	 **/
	private static void displayOutput(ArrayList<String> stationList) {
		String prevColour = "";
		String nextColour = "";
		String lastColour = "";
		ArrayList<String> lineList = new ArrayList<String>();
		ArrayList<String> nextLineList = new ArrayList<String>();
		lastColour = mGraph.isEdge(stationList.get(stationList.size() - 2), stationList.get(stationList.size() - 1)).get(0);

		if (stationList.size() > 2) {
			lineList = mGraph.isEdge(stationList.get(0), stationList.get(1));
			nextLineList = mGraph.isEdge(stationList.get(1), stationList.get(2));

			for (int h = 0; h < lineList.size(); h++) {
				if (nextLineList.contains(lineList.get(h))) {
					prevColour = lineList.get(h);
					break;
				}
				if (h == lineList.size() - 1 && !nextLineList.contains(lineList.get(h))) {
					prevColour = lineList.get(0);
				}
			}
		} else {
			prevColour = mGraph.isEdge(stationList.get(0), stationList.get(1)).get(0);
		}
		System.out.println("\nGet on the " + prevColour + " line, towards " + mGraph.getNodeName(stationList.get(1)));

		for (int i = 0; i < (stationList.size() - 1); i++) {

			lineList = mGraph.isEdge(stationList.get(i), stationList.get(i + 1));
			if (i < stationList.size() - 2) {
				nextLineList = mGraph.isEdge(stationList.get(i + 1), stationList.get(i + 2));
				if (lineList.contains(lastColour)) {
					nextColour = lastColour;
				} else if (lineList.size() > 1) {
					if (!lineList.contains(prevColour)) {
						for (int j = 0; j < lineList.size(); j++) {
							if (nextLineList.contains(lineList.get(j))) {
								nextColour = lineList.get(j);
								break;
							}
							if (j == lineList.size() - 1 && !nextLineList.contains(lineList.get(j))) {
								nextColour = lineList.get(0);
							}
						}
					} else {
						nextColour = prevColour;
					}
				} else {
					nextColour = lineList.get(0);
				}
			} else {
				nextColour = lineList.get(0);
			}

			if (!(nextColour).equals(prevColour)) {
				System.out.println("Change at " + mGraph.getNodeName(stationList.get(i)) + " to the " + nextColour + " line, towards " + mGraph.getNodeName(stationList.get(i + 1)));
			}
			prevColour = nextColour;
		}
		System.out.println("You Have Arrived At " + mGraph.getNodeName(stationList.get(stationList.size() - 1)) + "\n");
	}
}