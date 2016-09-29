import java.util.*;

public interface EdgeI {
   

public String getLabel(String nodeA, String nodeB);

public boolean setLabel(String label);

public ArrayList<Node> getNodes(String label);

public boolean setNodesOnEdge(Node[] list);

}
