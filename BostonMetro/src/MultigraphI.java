import Array;

public interface MultigraphI {
public void setNodeList(Array value);

public Array getNodeList();

public void setEdgeList(Array value);

public Array getEdgeList();

public String getNode(String name);

public String getEdge(String name);

public boolean addNode(String id, String name);

public boolean addEdge(String successor, String predecessor, String label);

public boolean isEdge(String nodeA, String nodeB);

}
