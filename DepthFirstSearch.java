import java.util.*;

public class DepthFirstSearch<V> implements Search<V> {
    private final Map<V, V> previous;
    private final Set<V> visited;
    private final V start;

    public DepthFirstSearch(UnweightedGraph<V> graph, V start) {
        this.start = start;
        this.previous = new HashMap<>();
        this.visited = new HashSet<>();
        dfs(graph, start);
    }

    private void dfs(UnweightedGraph<V> graph, V current) {
        visited.add(current);
        for (V neighbor : graph.getAdjacentVertices(current)) {
            if (!visited.contains(neighbor)) {
                previous.put(neighbor, current);
                dfs(graph, neighbor);
            }
        }
    }

    @Override
    public List<V> pathTo(V destination) {
        List<V> path = new ArrayList<>();
        V current = destination;
        while (current != null && !current.equals(start)) {
            path.add(0, current);
            current = previous.get(current);
        }
        if (current != null) {
            path.add(0, start);
        }
        return path;
    }
}
