import java.util.ArrayList;

public interface MultigraphIn {

   public String getNodeName(String id);
   
   public boolean addNode(String nodeID, String nodeName);
   
   public boolean addEdge( String label, String successor, String predecessor);
   
   public boolean isEdge(String nodeA, String nodeB);
   
   }
