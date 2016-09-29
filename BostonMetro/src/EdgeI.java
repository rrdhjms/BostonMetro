import java.util.*;

public interface EdgeI {
/**
    * <pre>
    *           0..*     1..1
    * EdgeI ------------------------- MultigraphADT
    *           edgeI        &lt;       multigraphADT
    * </pre>
    */
   public void setMultigraphADT(MultigraphADT value);
   
   public MultigraphADT getMultigraphADT();
   

public String getLabel(String nodeA, String nodeB);

public boolean setLabel(String label);

public ArrayList<Node> getNodes(String label);

public boolean setNodesOnEdge(Node[] list);

}
