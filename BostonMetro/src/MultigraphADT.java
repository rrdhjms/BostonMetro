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
   private Set<NodeIn> nodeIn;
   
   public Set<NodeIn> getNodeIn() {
      if (this.nodeIn == null) {
         this.nodeIn = new HashSet<NodeIn>();
      }
      return this.nodeIn;
   }
   
   
}
