import java.util.Set;
import java.util.HashSet;

public class MultigraphADT {
/**
 * <pre>
 *           0..1     0..*
 * MultigraphADT ------------------------- MapParser
 *           multigraphADT        &gt;       mapParser
 * </pre>
 */
private Set<MapParser> mapParser;

public Set<MapParser> getMapParser() {
   if (this.mapParser == null) {
this.mapParser = new HashSet<MapParser>();
   }
   return this.mapParser;
}

/**
 * <pre>
 *           1..1     0..*
 * MultigraphADT ------------------------- Search
 *           multigraphADT        &gt;       search
 * </pre>
 */
private Set<Search> search;

public Set<Search> getSearch() {
   if (this.search == null) {
this.search = new HashSet<Search>();
   }
   return this.search;
}

/**
 * <pre>
 *           1..1     0..*
 * MultigraphADT ------------------------- Node
 *           multigraphADT        &gt;       station
 * </pre>
 */
private Set<Node> station;

public Set<Node> getStation() {
   if (this.station == null) {
this.station = new HashSet<Node>();
   }
   return this.station;
}

/**
 * <pre>
 *           1..1     0..*
 * MultigraphADT ------------------------- Edge
 *           multigraphADT        &gt;       line
 * </pre>
 */
private Set<Edge> line;

public Set<Edge> getLine() {
   if (this.line == null) {
this.line = new HashSet<Edge>();
   }
   return this.line;
}

}
