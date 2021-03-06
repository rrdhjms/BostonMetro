import java.util.ArrayList;

public interface MultigraphADT {

   public String getNodeName(String id);
   
   public boolean addNode(String nodeID, String nodeName);
   
   public boolean addEdge(String label, String successor, String predecessor);
   
   public ArrayList<String> isEdge(String nodeA, String nodeB);
   
   public int countNodeOccurences(String nodeName);

   public ArrayList<String> getIDFromName(String stationName);
   
   public ArrayList<String> getEdgeLabelsFromID(String nodeID);
   
   public ArrayList<String> searchShortestPath(String originID, String destinationID);
   
   public Boolean edgeExists(String label, String nodeA, String nodeB);

}
