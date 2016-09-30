import java.util.ArrayList;

public interface MultigraphIn {

   public String getNodeName(String id);
   
   public boolean addNode(String nodeID, String nodeName);
   
   public boolean addEdge(String successor, String predecessor, String label);
   
   public boolean isEdge(String nodeA, String nodeB);
   
   }
