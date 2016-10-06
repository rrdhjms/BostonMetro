import java.util.ArrayList;

public interface MultigraphADT {

   public String getNodeName(String id);
   
   public boolean addNode(String nodeID, String nodeName);
   
   public boolean addEdge( String label, String successor, String predecessor);
   
   public String isEdge(String nodeA, String nodeB);
   
   public int countNodeOccurences(String nodeName);

   public ArrayList<String> getIDFromName(String stationName);
   
   public String getEdgeLabelFromID(String nodeID);
   
   public ArrayList<String> searchShortestPath(String originID, String destinationID);

   public void printEdgeList();
   
   public Boolean edgeExists(String label, String nodeA, String nodeB);
   
   }
