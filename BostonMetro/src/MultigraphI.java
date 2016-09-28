import Array;

public interface MultigraphI {
private void setNodeList(Array value);

private Array getNodeList();

private void setEdgeList(Array value);

private Array getEdgeList();

public String getNode(String name);

public String getEdge(String name);

public boolean addNode(String id, String name);

public boolean addEdge(String successor, String predecessor, String label);

public boolean isEdge(String nodeA, String nodeB);

}
