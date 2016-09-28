
public class Node {
/**
 * <pre>
 *           0..*     1..1
 * Node ------------------------- MultigraphADT
 *           station        &lt;       multigraphADT
 * </pre>
 */
private MultigraphADT multigraphADT;

public void setMultigraphADT(MultigraphADT value) {
   this.multigraphADT = value;
}

public MultigraphADT getMultigraphADT() {
   return this.multigraphADT;
}

}
