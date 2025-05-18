import java.util.*;

public class WeightedGraph<V> {
    private final boolean directed;
    private Map<V, Vertex<V>> vertices;

    public WeightedGraph(boolean directed) {
        this.directed = directed;
        this.vertices = new HashMap<>();
    }

    public void addEdge(V from, V to, double weight) {
        Vertex<V> fromVertex = vertices.computeIfAbsent(from, Vertex::new);
        Vertex<V> toVertex = vertices.computeIfAbsent(to, Vertex::new);

        fromVertex.addAdjacentVertex(toVertex, weight);
        if (!directed) {
            toVertex.addAdjacentVertex(fromVertex, weight);
        }
    }

    public Vertex<V> getVertex(V data) {
        return vertices.get(data);
    }

    public Collection<Vertex<V>> getVertices() {
        return vertices.values();
    }
}
