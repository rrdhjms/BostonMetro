
public class Edge {
/**
 * <pre>
 *           0..*     1..1
 * Edge ------------------------- MultigraphADT
 *           line        &lt;       multigraphADT
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
