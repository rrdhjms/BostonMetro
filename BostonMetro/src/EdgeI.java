import Array;

public interface EdgeI {
public void setLabel(String value);

public String getLabel();

public void setNodeList(Array value);

public Array getNodeList();

public String getLabel(String nodeA, String nodeB);

public boolean setLabel(String label);

public Array getNodes(String label);

public boolean setNodesOnEdge(Array list);

}
