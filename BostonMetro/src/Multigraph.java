import java.util.ArrayList;
import java.util.HashMap;

public class Multigraph implements MultigraphADT {

	private ArrayList<Node> nodeList;
	private ArrayList<Edge> edgeList;

	public Multigraph() {
		nodeList = new ArrayList<Node>();
		edgeList = new ArrayList<Edge>();
	}

	public ArrayList<Edge> getEdgeList() {
		ArrayList<Edge> edges = new ArrayList<Edge>();
		for (Edge e : edgeList)
			edges.add(new Edge(e.getLabel(), e.getNodeA(), e.getNodeB()));
		return edges;
	}

	public String getNodeName(String id) {
		for (int i = 0; i < nodeList.size(); i++) {
			if (nodeList.get(i).getID().equals(id)) {
				return nodeList.get(i).getName();
			}
		}
		return null;
	}

	public ArrayList<String> getIDFromName(String nodeName) {
		ArrayList<String> idList = new ArrayList<String>();
		
		for (int i = 0; i < nodeList.size(); i++) {
			if (nodeName.equalsIgnoreCase(nodeList.get(i).getName())) {
				idList.add(nodeList.get(i).getID());
			}
		}
		
		return idList;
	}
	
	//Assumes that all edges connecting to the node with id nodeID will have the same label.
	public String getEdgeLabelFromID(String nodeID){
		for(int i = 0; i < edgeList.size(); i++){
			if(edgeList.get(i).getNodeA().equals(nodeID) || edgeList.get(i).getNodeB().equals(nodeID)){
				return edgeList.get(i).getLabel();
			}
		}
		return null;
	}
	
	public ArrayList<String> getEdgeLabelsFromID(String nodeID){
		ArrayList<String> labelList = new ArrayList<String>();
		for(int i = 0; i < edgeList.size(); i++){
			if(edgeList.get(i).getNodeA().equals(nodeID) || edgeList.get(i).getNodeB().equals(nodeID)){
				if(!labelList.contains(edgeList.get(i).getLabel())){
					labelList.add(edgeList.get(i).getLabel());
					System.out.println("Added: " + edgeList.get(i).getLabel());
				}
			}
		}
		return null;
	}

	public boolean addNode(String nodeID, String nodeName) {
		nodeList.add(new Node(nodeID, nodeName));
		return true;
	}

	public boolean addEdge(String label, String successor, String predecessor) {
		edgeList.add(new Edge(label, successor, predecessor));
		return true;
	}

	public ArrayList<String> isEdge(String nodeA, String nodeB) {
		ArrayList<String> labelList = new ArrayList<String>();
		for (int c = 0; c < edgeList.size(); c++) {
			if (edgeList.get(c).getNodeA().equals(nodeA) && edgeList.get(c).getNodeB().equals(nodeB)) {
				labelList.add(edgeList.get(c).getLabel());
			} else if (edgeList.get(c).getNodeA().equals(nodeB) && edgeList.get(c).getNodeB().equals(nodeA)) {
				labelList.add(edgeList.get(c).getLabel());
			}
		}
		return labelList;
	}
	
	public Boolean edgeExists(String label, String nodeA, String nodeB){
		for (int c = 0; c < edgeList.size(); c++) {
			if (edgeList.get(c).getNodeA().equals(nodeA) && edgeList.get(c).getNodeB().equals(nodeB) && edgeList.get(c).getLabel().equals(label)) {
				return true;
			} else if (edgeList.get(c).getNodeA().equals(nodeB) && edgeList.get(c).getNodeB().equals(nodeA) && edgeList.get(c).getLabel().equals(label)) {
				return true;
			}
		}
		return false;
	}

	public int countNodeOccurences(String nodeName) {
		int count = 0;
		for (int i = 0; i < nodeList.size(); i++) {
			if (nodeList.get(i).getName().equalsIgnoreCase(nodeName)) {
				count++;
			}
		}
		return count;
	}
	
	public void printEdgeList(){
		for(int i = 0; i < edgeList.size(); i++){
			System.out.println(edgeList.get(i).getLabel() + " | " + edgeList.get(i).getNodeA() + " | " + edgeList.get(i).getNodeB());
		}
	}

	// -------------------------------Searching Happens below-----------------------------------------------------

	public ArrayList<String> searchShortestPath(String originID, String destinationID) {
		/*could be more efficient if routes that had reached the end were removed from the 2D array*/

		ArrayList<ArrayList<String>> routes = new ArrayList<ArrayList<String>>();
		ArrayList<String> visited = new ArrayList<String>();
		boolean found = false;
		int foundIndex = 0;
		
		visited.add(originID);
		for (int i = 0; i < edgeList.size(); i++) {
			if (edgeList.get(i).getNodeA().equals(originID)) {
				routes.add(new ArrayList<String>());
				routes.get(routes.size() - 1).add(originID);
				routes.get(routes.size() - 1).add(edgeList.get(i).getNodeB());
				visited.add(edgeList.get(i).getNodeB());
				printArrays(routes, visited);	
			}
			if (edgeList.get(i).getNodeB().equals(originID)) {
				routes.add(new ArrayList<String>());
				routes.get(routes.size() - 1).add(originID);
				routes.get(routes.size() - 1).add(edgeList.get(i).getNodeA());
				visited.add(edgeList.get(i).getNodeA());
				printArrays(routes, visited);
			}
		} /* creates a new path for each neighbour node of our source */
		
		for (int index = 0; index < routes.size(); index++) {
			if (routes.get(index).get(routes.get(index).size() - 1).equals(destinationID))
				found = true;
				foundIndex = index;
		}

		while (!found) {
			/* add the next set of stations another step from origin to our routes */
			for (int k = 0; k < routes.size(); k++) {
				String currentNodeID = (routes.get(k).get((routes.get(k).size()) - 1));
				String previousNodeID = null;
				previousNodeID = routes.get(k).get((routes.get(k).size() - 2));
				ArrayList<String> nextNodes = getNextNodeIDs(currentNodeID);
				if (previousNodeID != null) {
					nextNodes.remove(previousNodeID);
				}
				System.out.println("nextNodes : " + nextNodes);
				if (!nextNodes.isEmpty()) {
					/*add the first next node to our route*/
					routes.get(k).add(nextNodes.get(0));
					visited.add(nextNodes.get(0));
					nextNodes.remove(0);
					printArrays(routes, visited);
					while (!nextNodes.isEmpty()){
						 /* make a new path for the other options, copy what we had so far and search each one */
							String nextNodeID = nextNodes.get(0);
							routes.add(new ArrayList<String>());
							routes.get(routes.size() - 1).addAll(routes.get(k));
							routes.get(routes.size() - 1).add((nextNodeID));
							visited.add(nextNodeID);
							nextNodes.remove(0);
					} /* end of inner while */
				}
			}
			for (int index = 0; index < routes.size(); index++) {
				if (routes.get(index).get(routes.get(index).size() - 1).equals(destinationID))
					found = true;
					foundIndex = index;
			}
		} /* end of while */
		return routes.get(foundIndex);
	}

	private String howManyEdges(String nodeID) {
		/*helper method for search to return the number of nodes reachable from the current node*/
		int outDegree = 0;
		for (int i = 0; i < edgeList.size(); i++) {
			if (edgeList.get(i).getNodeA().equals(nodeID)) {
				outDegree++;
			}
			if (edgeList.get(i).getNodeB().equals(nodeID)) {
				outDegree++;
			}
		}
		return Integer.toString(outDegree);
	}

	private ArrayList<String> getNextNodeIDs(String currentNodeID) {
		/*helper method for search to return all possible IDs reachable from the current node*/
		ArrayList<String> nextNodeIDs = new ArrayList<String>();
		for (int i = 0; i < edgeList.size(); i++) {
			if (edgeList.get(i).getNodeA().equals(currentNodeID)) {
				nextNodeIDs.add(edgeList.get(i).getNodeB());
			}
			if (edgeList.get(i).getNodeB().equals(currentNodeID)) {
				nextNodeIDs.add(edgeList.get(i).getNodeA());
			}
		}
		return nextNodeIDs;
	}
	
	public void printArrays(ArrayList<ArrayList<String>> routes, ArrayList<String> visited){
		for (int i = 0; i<routes.size(); i++){
			System.out.println("Route " + i + " ");
			for (int j = 0; j<routes.get(i).size();j++){
				System.out.println(" : " + getNodeName(routes.get(i).get(j)));
			}
			System.out.println("visited : " + visited);
		}
			
	}
}
