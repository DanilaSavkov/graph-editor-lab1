package model;

import model.edges.Edge;
import model.graphs.Graph;
import model.representations.WeightMatrix;
import model.vertecies.Vertex;

import static java.util.Arrays.fill;

public class AlgorithmSolver {
    private final Graph<Vertex, Edge> graph;
    private final WeightMatrix matrix;

    public AlgorithmSolver(Graph graph) {
        this.graph = graph;
        matrix = new WeightMatrix(graph);
    }

    public Graph<Vertex, Edge> getGraph() {
        return graph;
    }

    public int[] dijkstra(Vertex source) {
        boolean[] used = new boolean[graph.getVerticesCount()]; // массив пометок
        int[] distance = new int[graph.getVerticesCount()]; // массив расстояния. distance[v] = минимальное_расстояние(source, v)

        fill(distance, WeightMatrix.getINFINITY()); // устанаавливаем расстояние до всех вершин INF
        distance[graph.getVertexIndex(source)] = 0; // для начальной вершины положим 0

        for (; ; ) {
            int vertexIndex = findNearestVertexIndex(used, distance);
            if (vertexIndex == -1) break; // ближайшая вершина не найдена
            used[vertexIndex] = true; // помечаем ее
            for (Vertex nextVertex : graph.getVertices()) {
                if (!used[graph.getVertexIndex(nextVertex)] && matrix.getMatrix().get(graph.getVertices().get(vertexIndex)).get(graph.getVertices().get(graph.getVertexIndex(nextVertex))) < WeightMatrix.getINFINITY()) {// для всех непомеченных смежных
                    distance[graph.getVertexIndex(nextVertex)] = Integer.min(distance[graph.getVertexIndex(nextVertex)], (distance[vertexIndex] + matrix.getMatrix().get(graph.getVertices().get(vertexIndex)).get(graph.getVertices().get(graph.getVertexIndex(nextVertex))))); // улучшаем оценку расстояния (релаксация)
                }
            }
        }

        return distance;
    }

    private int findNearestVertexIndex(boolean[] used, int[] distance) {
        int vertexIndex = -1;
        for (Vertex nextVertex : graph.getVertices()) { // перебираем вершины
            if (!used[graph.getVertexIndex(nextVertex)]
                    && distance[graph.getVertexIndex(nextVertex)] < WeightMatrix.getINFINITY()
                    && (vertexIndex == -1 || distance[vertexIndex] > distance[graph.getVertexIndex(nextVertex)])) { // выбираем самую близкую непомеченную вершину
                vertexIndex = graph.getVertexIndex(nextVertex);
            }
        }
        return vertexIndex;
    }
}