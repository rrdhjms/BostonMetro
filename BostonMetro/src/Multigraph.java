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

	public boolean addNode(String nodeID, String nodeName) {
		nodeList.add(new Node(nodeID, nodeName));
		return true;
	}

	public boolean addEdge(String label, String successor, String predecessor) {
		edgeList.add(new Edge(label, successor, predecessor));
		return true;
	}

	public String isEdge(String nodeA, String nodeB) {

		for (int c = 0; c < edgeList.size(); c++) {
			if (edgeList.get(c).getNodeA().equals(nodeA) && edgeList.get(c).getNodeB().equals(nodeB)) {
				return edgeList.get(c).getLabel();
			} else if (edgeList.get(c).getNodeA().equals(nodeB) && edgeList.get(c).getNodeB().equals(nodeA)) {
				return edgeList.get(c).getLabel();
			}
		}
		return null;
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

	// -------------------------------Searching Happens below-----------------------------------------------------

	public ArrayList<String> searchShortestPath(String originID, String destinationID) {

		ArrayList<ArrayList<String>> routes = new ArrayList<ArrayList<String>>();
		ArrayList<String> visited = new ArrayList<String>();
		boolean found = false;
		int foundIndex = 0;

		for (int i = 0; i < edgeList.size(); i++) {
			if (edgeList.get(i).getNodeA().equals(originID)) {
				routes.add(new ArrayList<String>());
				routes.get(i).add(originID);
				routes.get(i).add(edgeList.get(i).getNodeB());
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
			if (routes.get(index).get(routes.get(index).size() - 1).equals(destinationID))
				found = true;
			foundIndex = index;
		}

		while (!found) {
			/*
			 * add the next set of stations another step from origin to our
			 * routes
			 */
			for (int k = 0; k < routes.size(); k++) {
				String currentNodeID = (routes.get(k).get((routes.get(k).size()) - 1));
				int numEdges = Integer.parseInt(howManyEdges(currentNodeID));
				ArrayList<String> nextNodes = getNextNodeIDs(currentNodeID);
				if (!visited.contains(nextNodes.get(0))) {
					routes.get(k).add(nextNodes.get(0));
					visited.add(nextNodes.get(0));
				}
				for (int i = 1; i < numEdges; i++) {
					/*
					 * If the current node we're searching has more than 1 edge,
					 * make a new path to search each one
					 */
					String nextNodeID = nextNodes.get(i);
					if (!visited.contains(nextNodeID)) {
						routes.add(new ArrayList<String>());
						routes.get(routes.size() - 1).addAll(routes.get(k));
						routes.get(routes.size() - 1).add((nextNodeID));
						visited.add(nextNodeID);
					} else {
						routes.remove(k);
						/*
						 * else the next node is in another path at an earlier
						 * stage, or we've reached the end of the line and the
						 * only option is to go backwards.
						 */
					}
				} /* end of for */
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

	private ArrayList<String> getNextNodeIDs(String originID) {
		ArrayList<String> nextNodeIDs = new ArrayList<String>();
		for (int i = 0; i < edgeList.size(); i++) {
			if (edgeList.get(i).getNodeA().equals(originID)) {
				nextNodeIDs.add(edgeList.get(i).getNodeB());
			}
			if (edgeList.get(i).getNodeB().equals(originID)) {
				nextNodeIDs.add(edgeList.get(i).getNodeB());
			}
		}
		return nextNodeIDs;
	}

}
