
public class Edge implements EdgeIn {

	
	private String nodeA;
	private String nodeB;
	private String label;
	
	public Edge(String lab,String aID, String bID){
		label = lab;
		nodeA = aID;
		nodeB = bID;
		
	}

	public String getLabel() {
		return label;
	}

	public String getNodeA() {
		return nodeA;
	}

	public String getNodeB() {
		return nodeB;
	}
	
	public boolean setLabel(String edgeLabel) {
		label = edgeLabel;
		return true;
	}

	public boolean setNodesOnEdge(String nodeAID, String nodeBID) {
		nodeA = nodeAID;
		nodeB = nodeBID;
		return true;
	}
	
}
