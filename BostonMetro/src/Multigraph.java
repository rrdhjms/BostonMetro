import java.util.ArrayList;
import java.util.HashMap;

public class Multigraph implements MultigraphADT {

	private HashMap<String, Node> nodeMap;
	private ArrayList<Edge> edgeList;
	
	public Multigraph(){
		nodeMap = new HashMap<String, Node>();
		edgeList = new ArrayList<Edge>();
	}
	
	public String getNodeName(String id) {
		
		return ((Node) nodeMap.get(id)).getName();
	}

	public boolean addNode(String nodeID, String nodeName) {
		nodeMap.put(nodeID, new Node(nodeID,nodeName));
		return true;
	}

	public boolean addEdge(String label,String successor, String predecessor) {
		edgeList.add(new Edge(label,successor, predecessor));
		return true;
	}

	public boolean isEdge(String nodeA, String nodeB) {
		
		for(int c = 0; c < edgeList.size(); c++){
			if(edgeList.get(c).getNodeA().equals(nodeA) && edgeList.get(c).getNodeB().equals(nodeB)){
				return true;
			}
			else if(edgeList.get(c).getNodeA().equals(nodeB) && edgeList.get(c).getNodeB().equals(nodeA)){
				return true;
			}
		}
		
		return false;
	}
	
	
		public ArrayList<String> searchShortestPath(String originID, String destinationID){

		  ArrayList<ArrayList<String>> routes = new ArrayList<ArrayList<String>>();
		  ArrayList<String> visited = new ArrayList<String>();
		  boolean found = false;
		  int foundIndex = 0;
		  
		  for (int i = 0; i<edgeList.size();i++){
		   if (edgeList.get(i).getNodeA().equals(originID)){
		    routes.add(new ArrayList<String>());
		    routes.get(i).add(originID);
		    routes.get(i).add(edgeList.get(i).getNodeB());
		   }
		   if (edgeList.get(i).getNodeB().equals(originID)){
		    routes.add(new ArrayList<String>());
		    routes.get(routes.size()-1).add(originID);
		    routes.get(routes.size()-1).add(edgeList.get(i).getNodeA());
		   }
		  } /*creates a new route for each neighbour node of our source*/
		  
		  for (int index = 0; index<routes.size();index++){
		   if (routes.get(index).get(routes.get(index).size()-1).equals(originID))
			   found = true;
		       foundIndex = index;
		  }
		  
		  while (!found){
			  /*add the next set of stations another step from origin to our routes*/
			  for (int k = 0; k<routes.size();k++){
				  	String currentNodeID = (routes.get(k).get((routes.get(k).size())-1));
				    if (!visited.contains(currentNodeID)){
					      if (howManyEdges(currentNodeID)=="4"){
					      routes.add(new ArrayList<String>());
					      routes.get(routes.size()-1).addAll(routes.get(k));
					      routes.get(routes.size()-1).add((currentNodeID));
					      visited.add(currentNodeID);
					     }
					     if (howManyEdges(currentNodeID)=="3"){
					      routes.add(new ArrayList<String>());
					      routes.get(routes.size()-1).addAll(routes.get(k));
					      routes.get(routes.size()-1).add((currentNodeID));
					      visited.add(currentNodeID);
					     }
					     if (howManyEdges(currentNodeID)=="2"){
					      routes.add(new ArrayList<String>());
					      routes.get(routes.size()-1).addAll(routes.get(k));
					      routes.get(routes.size()-1).add((currentNodeID));
					      visited.add(currentNodeID);
					     }
					     if (howManyEdges(currentNodeID)=="1"){
					    	 routes.get(k).add(currentNodeID);
					    	 visited.add(currentNodeID);
					     }
				    }  /*need to refactor and generalise this for any number of edges*/
				    else {
				     routes.remove(k);
				     /*our current node is in another route at an earlier stage.
				      * this might be a viable route, but it's not the shortest one */
				    }
			  }   
			for (int index = 0; index<routes.size();index++){
				if (routes.get(index).get(routes.get(index).size()-1).equals(originID))
				found = true;
				foundIndex = index;
			}
		} /*end of while*/
		return routes.get(foundIndex);
	}
		 
	public String howManyEdges(String nodeID){
		  int outDegree = 0;
		  for (int i = 0; i<edgeList.size();i++){
		   if (edgeList.get(i).getNodeA().equals(nodeID)){
		    outDegree++;
		   }
		   if (edgeList.get(i).getNodeB().equals(nodeID)){
		    outDegree++;
		   }
		  }
		  return Integer.toString(outDegree);
	 }
	


}