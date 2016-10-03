
public interface EdgeIn {
	
	
	public String getLabel();
	
	public String getNodeA();
	
	public String getNodeB();
	
	public boolean setLabel(String edgeLabel);
	
	public boolean setNodesOnEdge(String nodeAID, String nodeBID);

}
