import java.util.*;

public class DijkstraSearch<V> implements Search<V> {
    private final Map<V, V> previousVertices;
    private final Map<V, Double> distances;
    private final V start;

    public DijkstraSearch(WeightedGraph<V> graph, V start) {
        this.start = start;
        previousVertices = new HashMap<>();
        distances = new HashMap<>();
        dijkstra(graph);
    }

    private void dijkstra(WeightedGraph<V> graph) {
        PriorityQueue<Vertex<V>> pq = new PriorityQueue<>(Comparator.comparingDouble(v -> distances.getOrDefault(v.getData(), Double.MAX_VALUE)));

        for (Vertex<V> vertex : graph.getVertices()) {
            distances.put(vertex.getData(), Double.MAX_VALUE);
        }

        distances.put(start, 0.0);
        pq.add(graph.getVertex(start));

        while (!pq.isEmpty()) {
            Vertex<V> current = pq.poll();
            for (Map.Entry<Vertex<V>, Double> entry : current.getAdjacentVertices().entrySet()) {
                Vertex<V> neighbor = entry.getKey();
                double weight = entry.getValue();
                double newDist = distances.get(current.getData()) + weight;

                if (newDist < distances.get(neighbor.getData())) {
                    distances.put(neighbor.getData(), newDist);
                    previousVertices.put(neighbor.getData(), current.getData());
                    pq.add(neighbor);
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
            current = previousVertices.get(current);
        }
        if (current != null) {
            path.add(0, start);
        }
        return path;
    }
}
