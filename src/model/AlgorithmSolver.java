package model;

import model.edges.Edge;
import model.graphs.Graph;
import model.representations.WeightMatrix;
import model.vertecies.Vertex;

import java.util.Arrays;

import static java.util.Arrays.fill;

public class AlgorithmSolver {
    private final Graph<Vertex, Edge> graph;
    private final WeightMatrix matrix;
    private static final double INFINITY = Double.MAX_VALUE; // "Бесконечность"

    public AlgorithmSolver(Graph graph) {
        this.graph = graph;
        matrix = new WeightMatrix(graph);
    }

    /* Алгоритм Дейкстры за O(V^2) */
    public void dijkstra(int start) {
        boolean[] used = new boolean[graph.getVerticesCount()]; // массив пометок
        double[] dist = new double[graph.getVerticesCount()]; // массив расстояния. dist[v] = минимальное_расстояние(start, v)

        fill(dist, INFINITY); // устанаавливаем расстояние до всех вершин INF
        dist[start] = 0; // для начальной вершины положим 0

        for (; ; ) {
            int v = -1;
            for (int nv = 0; nv < graph.getVerticesCount(); nv++) // перебираем вершины
                if (!used[nv] && dist[nv] < INFINITY && (v == -1 || dist[v] > dist[nv])) // выбираем самую близкую непомеченную вершину
                    v = nv;
            if (v == -1) break; // ближайшая вершина не найдена
            used[v] = true; // помечаем ее
            for (int nv = 0; nv < graph.getVerticesCount(); nv++)
                if (!used[nv] && matrix.getMatrix().get(graph.getVertices().get(v)).get(graph.getVertices().get(nv)) < INFINITY) // для всех непомеченных смежных
                    dist[nv] = Double.min(dist[nv], (dist[v] + matrix.getMatrix().get(graph.getVertices().get(v)).get(graph.getVertices().get(nv)))); // улучшаем оценку расстояния (релаксация)
        }

        System.out.println(Arrays.toString(dist));
    }

}