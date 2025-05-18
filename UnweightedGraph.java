import java.util.*;

public class UnweightedGraph<V> {
    private final boolean directed;
    private Map<V, List<V>> adjacencyList;

    public UnweightedGraph(boolean directed) {
        this.directed = directed;
        this.adjacencyList = new HashMap<>();
    }

    public void addEdge(V from, V to) {
        adjacencyList.computeIfAbsent(from, k -> new ArrayList<>()).add(to);
        if (!directed) {
            adjacencyList.computeIfAbsent(to, k -> new ArrayList<>()).add(from);
        }
    }

    public List<V> getAdjacentVertices(V vertex) {
        return adjacencyList.getOrDefault(vertex, new ArrayList<>());
    }

    public Set<V> getVertices() {
        return adjacencyList.keySet();
    }
}
