import java.util.ArrayList;
import java.util.HashMap;

public class MultigraphADT implements MultigraphIn {

	private HashMap<String, Node> nodeMap;
	private ArrayList<Edge> edgeList;
	
	public MultigraphADT(){
		nodeMap = new HashMap<String, Node>();
		edgeList = new ArrayList<Edge>();
	}
	
	public String getNodeName(String id) {
		
		return ((Node) nodeMap.get(id)).getName();
	}

	public boolean addNode(String nodeID, String nodeName) {
		nodeMap.put(nodeID, new Node(nodeID,nodeName));
		return true;
	}

	public boolean addEdge(String label,String successor, String predecessor) {
		edgeList.add(new Edge(label,successor, predecessor));
		return true;
	}

	public boolean isEdge(String nodeA, String nodeB) {
		
		for(int c = 0; c < edgeList.size(); c++){
			if(edgeList.get(c).getNodeA().equals(nodeA) && edgeList.get(c).getNodeB().equals(nodeB)){
				return true;
			}
			else if(edgeList.get(c).getNodeA().equals(nodeB) && edgeList.get(c).getNodeB().equals(nodeA)){
				return true;
			}
		}
		
		return false;
	}

}
