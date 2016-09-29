import java.util.Set;
import java.util.HashSet;

public class MultigraphADT {
/**
    * <pre>
    *           1..1     0..*
    * MultigraphADT ------------------------> NodeIn
    *           &gt;       nodeI
    * </pre>
    */
   private Set<NodeIn> nodeI;
   
   public Set<NodeIn> getNodeI() {
      if (this.nodeI == null) {
         this.nodeI = new HashSet<NodeIn>();
      }
      return this.nodeI;
   }
   
   
}
