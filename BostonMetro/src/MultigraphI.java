import java.util.*;

public interface MultigraphI {

public String getNode(String name);

public String getEdge(String name);

public boolean addNode(String id, String name);

public boolean addEdge(String successor, String predecessor, String label);

public boolean isEdge(String nodeA, String nodeB);

}
