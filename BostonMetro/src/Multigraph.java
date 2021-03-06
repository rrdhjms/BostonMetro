import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Multigraph implements MultigraphADT {

	private ArrayList<Node> nodeList;
	private ArrayList<Edge> edgeList;

	public Multigraph() {
		nodeList = new ArrayList<Node>();
		edgeList = new ArrayList<Edge>();
	}
	
	/**
	 * Takes in the String id, loops until it finds the 
	 * counterpart returns the name of the ID if the nodeName
	 * exists
	 * 
	 * @param String id
	 * @return the String name of the node i.e the station
	 * else it will return null since they do not match
	 */
	public String getNodeName(String id) {
		for (int i = 0; i < nodeList.size(); i++) {
			if (nodeList.get(i).getID().equals(id)) {
				return nodeList.get(i).getName();
			}
		}
		return null;
	}

	/**
	 * 	Takes in the name of the node and adds the id of the node to the list of all the node ids 
	 *  if the name of the node matches with one of the nodes in the list of nodes. Returns the
	 *   updated list of node ids. 
	 * 
	 * @param String nodeName
	 * @return ArrayList of IDList
	 */
	public ArrayList<String> getIDFromName(String nodeName) {
		ArrayList<String> idList = new ArrayList<String>();
		for (int i = 0; i < nodeList.size(); i++) {
			if (nodeName.equalsIgnoreCase(nodeList.get(i).getName())) {
				idList.add(nodeList.get(i).getID());
			}
		}
		
		return idList;
	}
	
	/**
	 *  Takes in a single NodeID and returns a list of the 
	 *  labels of edges connected to that Node.
	 * 
	 *  @param String nodeID
	 *  @return ArrayList<String> labelList
	 **/
	public ArrayList<String> getEdgeLabelsFromID(String nodeID){//checks the colours around a single node
		ArrayList<String> labelList = new ArrayList<String>();
		for(int i = 0; i < edgeList.size(); i++){
			if(edgeList.get(i).getNodeA().equals(nodeID) || edgeList.get(i).getNodeB().equals(nodeID)){
				if(!labelList.contains(edgeList.get(i).getLabel())){
					labelList.add(edgeList.get(i).getLabel());
					//System.out.println("Added: " + edgeList.get(i).getLabel());
				}
			}
		}
		return labelList;
	}
	
	/**
	 *  Takes in the node ids and returns all labels connecting these two nodes.
	 *  This is in case there are more than one label between two nodes.
	 *  
	 * @param String nodeA, String nodeB
	 * @return ArrayList<String> labelList
	 * 
	 **/
	public ArrayList<String> isEdge(String nodeA, String nodeB) {
		ArrayList<String> labelList = new ArrayList<String>();
		for (int i = 0; i < edgeList.size(); i++) {
			if (edgeList.get(i).getNodeA().equals(nodeA) && edgeList.get(i).getNodeB().equals(nodeB)) {
				labelList.add(edgeList.get(i).getLabel());
			} else if (edgeList.get(i).getNodeA().equals(nodeB) && edgeList.get(i).getNodeB().equals(nodeA)) {
				labelList.add(edgeList.get(i).getLabel());
			}
		}
		return labelList;
	}

	
	public boolean addNode(String nodeID, String nodeName) {
		nodeList.add(new Node(nodeID, nodeName));
		return true;
	}

	public boolean addEdge(String label, String successor, String predecessor) {
		edgeList.add(new Edge(label, successor, predecessor));
		return true;
	}
	
	/**
	 *  Takes in two nodeIDs and a label, and returns True if an Edge exists between those 
	 * 
	 * 	@param String nodeA, String nodeB
	 *  @return Boolean
	 **/
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
	/**
	 * 	Takes in the name of the node and counts how many times this node
	 *  exists in the list of nodes. Returns the number of occurences. Used
	 *   to find nodes with multiple names.
	 * 
	 *  @param String nodeName
	 *  @return int count
	 **/
	public int countNodeOccurences(String nodeName) {
		int count = 0;
		for (int i = 0; i < nodeList.size(); i++) {
			if (nodeList.get(i).getName().equalsIgnoreCase(nodeName)) {
				count++;
			}
		}
		return count;
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
			}
			if (edgeList.get(i).getNodeB().equals(originID)) {
				routes.add(new ArrayList<String>());
				routes.get(routes.size() - 1).add(originID);
				routes.get(routes.size() - 1).add(edgeList.get(i).getNodeA());
				visited.add(edgeList.get(i).getNodeA());
			}
		} /* creates a new path for each neighbour node of our source */
	  
		for (int index = 0; index < routes.size(); index++) {
			if (routes.get(index).get(routes.get(index).size() - 1).equals(destinationID)){
				found = true;
				foundIndex = index;
			}
		}

	  while (!found) {
		  /* add the next set of stations another step from origin to our routes */
		  int routeSize = routes.size();
		  for (int k = 0; k < routeSize; k++) {
			  String currentNodeID = (routes.get(k).get((routes.get(k).size()) - 1));
			  ArrayList<String> nextNodes = getNextNodeIDs(currentNodeID);
			  nextNodes.removeAll(visited); /* if the next node has been visited by another path already
			  								 * then using this node would not be the shortest path */
			  if (!nextNodes.isEmpty()) {
				  /*add the first next node to our route*/
				  ArrayList<String> copy = new ArrayList<String>();
				  copy.addAll(routes.get(k));
				  routes.get(k).add(nextNodes.get(0));
				  visited.add(nextNodes.get(0));
				  nextNodes.remove(nextNodes.get(0));
				  while (!nextNodes.isEmpty()){
					  /* make a new path for the other options when at a branching station,
					   * copy what we had so far and search each one */
					  String nextNodeID = nextNodes.get(0);
					  routes.add(new ArrayList<String>());
					  routes.get(routes.size() - 1).addAll(copy);
					  routes.get(routes.size() - 1).add((nextNodeID));
					  visited.add(nextNodeID);
					  if(nextNodeID == destinationID) {
						  found = true;
						  foundIndex = routes.size()-1;
					  }
					  nextNodes.remove(nextNodeID);
				 } /* end of inner while */
				  if(!found){
					  for (int index = 0; index < routes.size(); index++) {
						  if (routes.get(index).get(routes.get(index).size() - 1).equals(destinationID)){
							  found = true;
							  foundIndex = index;
							  break;
						  }
					  }
				  }
			  }
		  }
	  } /* end of while */
	  return routes.get(foundIndex);
	 }
	 
	 /**
	  *  Not used in interface.
	  * 
	  **/
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
		Set<String> uniqueIDs = new HashSet<>();
		uniqueIDs.addAll(nextNodeIDs);
		nextNodeIDs.clear();
		nextNodeIDs.addAll(uniqueIDs);
		return nextNodeIDs;
	}
	
}