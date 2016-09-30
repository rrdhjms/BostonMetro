
public class Edge {

	private String nodeA;
	private String nodeB;
	private String label;
	
	public Edge(String aID, String bID, String lab){
		nodeA = aID;
		nodeB = bID;
		label = lab;
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
