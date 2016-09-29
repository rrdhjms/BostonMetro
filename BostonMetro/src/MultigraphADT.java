import java.util.Set;
import java.util.HashSet;

public class MultigraphADT {
/**
    * <pre>
    *           1..1     0..*
    * MultigraphADT ------------------------- EdgeI
    *           multigraphADT        &gt;       edgeI
    * </pre>
    */
   private Set<EdgeI> edgeI;
   
   public Set<EdgeI> getEdgeI() {
      if (this.edgeI == null) {
         this.edgeI = new HashSet<EdgeI>();
      }
      return this.edgeI;
   }
   
   /**
    * <pre>
    *           1..1     0..*
    * MultigraphADT ------------------------> NodeI
    *           &gt;       nodeI
    * </pre>
    */
   private Set<NodeI> nodeI;
   
   public Set<NodeI> getNodeI() {
      if (this.nodeI == null) {
         this.nodeI = new HashSet<NodeI>();
      }
      return this.nodeI;
   }
   
   
}
