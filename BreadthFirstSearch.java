import java.util.*;

public class BreadthFirstSearch<V> implements Search<V> {
    private final Map<V, V> previous;
    private final V start;

    public BreadthFirstSearch(UnweightedGraph<V> graph, V start) {
        this.start = start;
        this.previous = new HashMap<>();
        bfs(graph);
    }

    private void bfs(UnweightedGraph<V> graph) {
        Set<V> visited = new HashSet<>();
        Queue<V> queue = new LinkedList<>();
        visited.add(start);
        queue.add(start);

        while (!queue.isEmpty()) {
            V current = queue.poll();
            for (V neighbor : graph.getAdjacentVertices(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    previous.put(neighbor, current);
                    queue.add(neighbor);
                }
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
