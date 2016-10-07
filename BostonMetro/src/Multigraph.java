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
	
	public ArrayList<String> getEdgeLabelsFromID(String nodeID){//checks the colours around a single node
		ArrayList<String> labelList = new ArrayList<String>();
		for(int i = 0; i < edgeList.size(); i++){
			if(edgeList.get(i).getNodeA().equals(nodeID) || edgeList.get(i).getNodeB().equals(nodeID)){
				if(!labelList.contains(edgeList.get(i).getLabel())){
					labelList.add(edgeList.get(i).getLabel());
					System.out.println("Added: " + edgeList.get(i).getLabel());
				}
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

	public ArrayList<String> isEdge(String nodeA, String nodeB) {//checks the colours in between 2 nodes
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

	public ArrayList<String> searchShortestPath(String startID, String endID) {
		/*could be more efficient if routes that had reached the end were removed from the 2D array*/
	
		/*Find the given node in edgeList*/
		/*for(int ei = 0; ei < edgeList.size(); ei++){
			If it's found as node A then start a new branch on the tree
			if(edgeList.get(ei).getNodeA().equals(originID)){
				id_Paths.add(new ArrayList<String>());
				id_Paths.get(id_Paths.size()-1).add(edgeList.get(ei).getNodeA());
				id_Paths.get(id_Paths.size()-1).add(edgeList.get(ei).getNodeB());
				label_Paths.add(new ArrayList<String>());
				label_Paths.get(label_Paths.size()-1).add(edgeList.get(ei).getLabel());
				label_Paths.get(label_Paths.size()-1).add(edgeList.get(ei).getLabel());
			}
			If it's found as node B then start a new branch on the tree
			if(edgeList.get(ei).getNodeB().equals(originID)){
				id_Paths.add(new ArrayList<String>());
				id_Paths.get(id_Paths.size()-1).add(edgeList.get(ei).getNodeB());
				id_Paths.get(id_Paths.size()-1).add(edgeList.get(ei).getNodeA());
				label_Paths.add(new ArrayList<String>());
				label_Paths.get(label_Paths.size()-1).add(edgeList.get(ei).getLabel());
				label_Paths.get(label_Paths.size()-1).add(edgeList.get(ei).getLabel());
			}
		}*/
		
		/*Two ArrayLists, one for the Node IDs, one for Labels. This is to differentiate between nodes*/
		ArrayList<ArrayList<String>> idpaths = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> labelpaths = new ArrayList<ArrayList<String>>();
		ArrayList<String> visitedid = new ArrayList<String>();
		ArrayList<String> visitedlabel = new ArrayList<String>();
		
		String endLabel = getEdgeLabelsFromID(endID).get(0);
		/*Create first branch and add the node as visited*/
		String startLabel = getEdgeLabelsFromID(startID).get(0);
		idpaths.add(new ArrayList<String>());
		labelpaths.add(new ArrayList<String>());
		idpaths.get(idpaths.size()-1).add(startID);
		labelpaths.get(labelpaths.size()-1).add(startLabel);
		
		visitedid.add(startID);
		visitedlabel.add(startLabel);

		System.out.println(endLabel);
		System.out.println(startLabel);
		
		/*set to true if the destination is found*/
		boolean foundBool = false;
		
		while(!foundBool){
			/*Start searching all nodes on current level*/
			for(int br = 0; br < idpaths.size(); br++){
				int pathsize = idpaths.get(br).size()-1;
				String currID = idpaths.get(br).get(pathsize);
				String currLa = labelpaths.get(br).get(pathsize);
				
				ArrayList<String> nextIDs = new ArrayList<String>();
				ArrayList<String> nextLas = new ArrayList<String>();
				
				
				
				
				
				for(int ei = 0; ei < edgeList.size(); ei++){
					if(edgeList.get(ei).getNodeA().equals(currID) /*&& edgeList.get(ei).getLabel().equals(currLa)*/){
								nextIDs.add(edgeList.get(ei).getNodeB());
								nextLas.add(edgeList.get(ei).getLabel());
						
					}
					if(edgeList.get(ei).getNodeB().equals(currID) /*&& edgeList.get(ei).getLabel().equals(currLa)*/){
								nextIDs.add(edgeList.get(ei).getNodeA());
								nextLas.add(edgeList.get(ei).getLabel());
					}
				}
				System.out.println("current Station ID: " + currID);
				System.out.println("current Station Label: " + currLa);
				//System.out.println("nextIDs Size before: " + nextIDs.size());
				for(int i = 0; i < nextIDs.size(); i++){
					for(int j = 0; j < visitedid.size(); j++){
						if(nextIDs.get(i).equals(visitedid.get(j)) && nextLas.get(i).equals(visitedlabel.get(j))){
							nextIDs.remove(i);
							nextLas.remove(i);
							break;
						}
					}
				}
				//System.out.println("nextIDs Size after: " + nextIDs.size());
				
				
				
				if(!nextIDs.isEmpty()){
					String nextI = nextIDs.get(0);
					String nextL = nextLas.get(0);
					nextIDs.remove(0);
					nextLas.remove(0);
					idpaths.get(br).add(nextI);
					labelpaths.get(br).add(nextL);
					visitedid.add(nextI);
					visitedlabel.add(nextL);
					while(!nextIDs.isEmpty()){
						nextI = nextIDs.get(0);
						nextL = nextLas.get(0);
						nextIDs.remove(0);
						nextLas.remove(0);
						
						idpaths.add(new ArrayList<String>());
						labelpaths.add(new ArrayList<String>());
						
						int last = idpaths.size()-1;
						
						idpaths.get(last).addAll(idpaths.get(br));
						idpaths.get(last).add(nextI);
						
						labelpaths.get(last).addAll(labelpaths.get(br));
						labelpaths.get(last).add(nextL);
						
						visitedid.add(nextI);
						visitedlabel.add(nextL);
					}
				}
				
			}
			
			for(int i = 0; i < idpaths.size(); i++){
				int last = idpaths.size()-1;
				if(idpaths.get(last).equals(endID) && labelpaths.get(last).equals(endLabel)){
					foundBool = true;
				}
			}
			
		}
		
		System.out.println("It works O.o");
		return null;
	}

	private String howManyEdges(String nodeID) {
		return null;
	}

	private ArrayList<String> getAdjNodes(String currNodeID, String currNodeLabel) {
		/*helper method for search to return all possible IDs reachable from the current node*/
		return null;
		
	}
	
	public void printArrays(ArrayList<ArrayList<String>> routes, ArrayList<String> visited){
		
			
	}
}
