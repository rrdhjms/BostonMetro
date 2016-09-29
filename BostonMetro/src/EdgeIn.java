import java.util.*;

public interface EdgeIn {
/**
    * <pre>
    *           0..*     1..1
    * EdgeIn ------------------------- MultigraphADT
    *           EdgeIn        &lt;       multigraphADT
    * </pre>
    */
   public void setMultigraphADT(MultigraphADT value);
   
   public MultigraphADT getMultigraphADT();
   
   
   public String getNodeA();
   
   public String getNodeB();
   

public String getLabel(String nodeA, String nodeB);

public boolean setLabel(String label);

public boolean setNodesOnEdge(String node2ID);

}
