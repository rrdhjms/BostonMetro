
public class Node implements NodeIn{
	
	private String id;
	private String name;
	
	public Node(String nodeID, String nodeName){
		id = nodeID;
		name = nodeName;
	}
	
	public String getID() {
		return id;
	}

	public String getName() {
		return name;
	}

	public boolean setId(String nodeID) {
		id = nodeID;
		return true;
	}

	public boolean setName(String nodeName) {
		name = nodeName;
		return true;
	}
}
