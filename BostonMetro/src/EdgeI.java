import Array;

public interface EdgeI {
private void setLabel(String value);

private String getLabel();

private void setNodeList(Array value);

private Array getNodeList();

public String getLabel(String nodeA, String nodeB);

public boolean setLabel(String label);

public Array getNodes(String label);

public boolean setNodesOnEdge(Array list);

}
